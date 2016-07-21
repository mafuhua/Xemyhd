package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuen.xemyhd.Constants;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.OrderBean;
import com.yuen.xemyhd.bean.OrderBean2;
import com.yuen.xemyhd.bean.WXBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    public static String order_id;
    public String orderid;
    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private LinearLayout layout_title_bar;
    private ListView lv_order;
    private Context context;
    private TextView tv_order_list_id;
    private TextView tv_order_list_idcontent;
    private TextView tv_order_list_peoplename;
    private TextView tv_order_list_phone;
    private TextView tv_order_list_peopleaddress;
    private Button btn_send;
    private MyAdapter myAdapter;
    private List<OrderBean.DataBean> orderBeanData;
    private TextView tv_order_list_price;
    private String order_listPrice;
    private List<OrderBean2.DataBean> orderBeanData2;
    private IWXAPI wxapi;
    private MyAdapter2 myAdapter2;
    private String adds_id;
    private String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderid = getIntent().getStringExtra("orderid");
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrder();

    }

    private void initView() {
        context = this;
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        tv_title_dec.setText("订单详情");
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        lv_order = (ListView) findViewById(R.id.lv_order);
        LinearLayout headerview = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_order_header, null);
        tv_order_list_id = (TextView) headerview.findViewById(R.id.tv_order_list_id);
        tv_order_list_idcontent = (TextView) headerview.findViewById(R.id.tv_order_list_idcontent);
        lv_order.addHeaderView(headerview);
        LinearLayout footview = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_order_foot, null);
        tv_order_list_peoplename = (TextView) footview.findViewById(R.id.tv_order_list_peoplename);
        tv_order_list_phone = (TextView) footview.findViewById(R.id.tv_order_list_phone);
        tv_order_list_price = (TextView) footview.findViewById(R.id.tv_order_list_price);
        tv_order_list_peopleaddress = (TextView) footview.findViewById(R.id.tv_order_list_peopleaddress);
        btn_send = (Button) footview.findViewById(R.id.btn_send);
        lv_order.addFooterView(footview);
        iv_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void postOrder() {
        HashMap<String, String> paymap = new HashMap<>();
        paymap.put("order_id", order_id);
      /*  if (adds_id == null || adds_id.isEmpty()) {
            adds_id = "0";
        } else {
            paymap.put("adds_id", adds_id);
        }*/
        paymap.put("user_id", MainActivity.useruid);
        paymap.put("price", order_listPrice);
        XUtils.xUtilsPost(ContactURL.DOPAY_URL, paymap, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                //    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                Log.d("mafuhua", "result" + result);
                Gson gson = new Gson();
                WXBean wxBean = gson.fromJson(result, WXBean.class);
                WXBean.DataBean data = wxBean.getData();
                wxapi = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
                boolean registerApp = wxapi.registerApp(Constants.APP_ID);
                Log.d("mafuhua", "registerApp:" + registerApp);
                PayReq payReq = new PayReq();
                payReq.appId = data.getAppid();
                payReq.partnerId = data.getPartnerid();
                payReq.prepayId = data.getPrepayid();
                payReq.nonceStr = data.getNoncestr();
                payReq.packageValue = "Sign=WXPay";
                payReq.timeStamp = data.getTimestamp() + "";
                payReq.sign = data.getSign();


                Log.d("GouWuCheFragment2", payReq.appId);
                Log.d("GouWuCheFragment2", payReq.partnerId);
                Log.d("GouWuCheFragment2", payReq.prepayId);
                Log.d("GouWuCheFragment2", payReq.nonceStr);
                Log.d("GouWuCheFragment2", payReq.packageValue);
                Log.d("GouWuCheFragment2", payReq.timeStamp);
                Log.d("GouWuCheFragment2", payReq.sign);
                wxapi.sendReq(payReq);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getOrder() {
        HashMap<String, String> map = new HashMap<>();
        map.put("order_id", orderid);
        XUtils.xUtilsPost(ContactURL.ORDER_READ_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result);
                Gson gson = new Gson();
                if (result.contains("add_data")) {
                    OrderBean orderBean = gson.fromJson(result, OrderBean.class);
                    orderBeanData = orderBean.getData();
                    OrderBean.AddDataBean add_data = orderBean.getAdd_data();
                    tv_order_list_peoplename.setText(add_data.getName());
                    tv_order_list_phone.setText(add_data.getTel());

                    tv_order_list_peopleaddress.setText(add_data.getSheng() + add_data.getShi() + add_data.getQu());
                    OrderBean.OrderListBean order_list = orderBean.getOrder_list();
                    order_id = order_list.getOrder_id();
                    tv_order_list_idcontent.setText(order_id);
                    order_listPrice = order_list.getPrice();
                    tv_order_list_price.setText("总价:" + order_listPrice);
                    myAdapter = new MyAdapter(orderBeanData);
                    lv_order.setAdapter(myAdapter);
                    btn_send.setBackgroundColor(Color.parseColor("#FEBB24"));
                    btn_send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            postOrder();
                        }
                    });
                    tv_order_list_peopleaddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, AddressManagerActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            //    Toast.makeText(context, order_id, Toast.LENGTH_SHORT).show();
                            intent.putExtra("orderid", order_id);
                            startActivity(intent);
                        }
                    });
                } else {
                    OrderBean2 orderBean = gson.fromJson(result, OrderBean2.class);
                    orderBeanData2 = orderBean.getData();
                   /* OrderBean2.AddDataBean add_data = orderBean.getAdd_data();
                    tv_order_list_peoplename.setText(add_data.getName());
                    tv_order_list_phone.setText(add_data.getTel());

                    tv_order_list_peopleaddress.setText(add_data.getSheng()+add_data.getShi()+add_data.getQu());*/
                    OrderBean2.OrderListBean order_list = orderBean.getOrder_list();
                    order_id = order_list.getOrder_id();
                    tv_order_list_idcontent.setText(order_id);
                    order_listPrice = order_list.getPrice();
                    tv_order_list_price.setText("总价:" + order_listPrice);
                    myAdapter2 = new MyAdapter2(orderBeanData2);
                    lv_order.setAdapter(myAdapter2);
                    btn_send.setBackgroundColor(Color.GRAY);
                    tv_order_list_peopleaddress.setText("请选择收货地址");
                    tv_order_list_peopleaddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, AddressManagerDecActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            intent.putExtra("orderid", order_id);
                            startActivity(intent);
                        }
                    });
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
          /*  case 100:
                adds_id = data.getStringExtra("orderid");
                add = data.getStringExtra("add");

                break;*/
        }

    }

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new ViewHolder();
        }
    }

    class MyAdapter2 extends DefaultAdapter {
        public MyAdapter2(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new ViewHolder2();
        }
    }


    public class ViewHolder extends BaseHolder<OrderBean.DataBean> {
        public ImageView ivordershopimage;
        public TextView tvorderlistshopname;
        public TextView tvorderlistclass;
        public TextView tvorderlistclassname;
        public TextView tvorderlistpresentprice;
        public TextView tvorderlistcurrentprice;
        public TextView tvorderlistcount;
        public RelativeLayout add;


        @Override
        public View initView() {
            View root = View.inflate(OrderActivity.this, R.layout.layout_order_list_item, null);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvorderlistshopname = (TextView) root.findViewById(R.id.tv_order_list_shopname);
            tvorderlistclass = (TextView) root.findViewById(R.id.tv_order_list_class);
            tvorderlistclassname = (TextView) root.findViewById(R.id.tv_order_list_classname);
            tvorderlistpresentprice = (TextView) root.findViewById(R.id.tv_order_list_presentprice);
            tvorderlistcurrentprice = (TextView) root.findViewById(R.id.tv_order_list_currentprice);
            tvorderlistcount = (TextView) root.findViewById(R.id.tv_order_list_count);
            return root;
        }

        @Override
        public void refreshView(OrderBean.DataBean data, int position) {
            tvorderlistshopname.setText(data.getName());
            tvorderlistpresentprice.setText(data.getPrice());
            tvorderlistcount.setText(data.getNum());
            x.image().bind(ivordershopimage,data.getImage(), MyUtils.options);
        }
    }


    public class ViewHolder2 extends BaseHolder<OrderBean2.DataBean> {
        public ImageView ivordershopimage;
        public TextView tvorderlistshopname;
        public TextView tvorderlistclass;
        public TextView tvorderlistclassname;
        public TextView tvorderlistpresentprice;
        public TextView tvorderlistcurrentprice;
        public TextView tvorderlistcount;
        public RelativeLayout add;


        @Override
        public View initView() {
            View root = View.inflate(OrderActivity.this, R.layout.layout_order_list_item, null);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvorderlistshopname = (TextView) root.findViewById(R.id.tv_order_list_shopname);
            tvorderlistclass = (TextView) root.findViewById(R.id.tv_order_list_class);
            tvorderlistclassname = (TextView) root.findViewById(R.id.tv_order_list_classname);
            tvorderlistpresentprice = (TextView) root.findViewById(R.id.tv_order_list_presentprice);
            tvorderlistcurrentprice = (TextView) root.findViewById(R.id.tv_order_list_currentprice);
            tvorderlistcount = (TextView) root.findViewById(R.id.tv_order_list_count);
            return root;
        }

        @Override
        public void refreshView(OrderBean2.DataBean data, int position) {
            tvorderlistshopname.setText(data.getName());
            tvorderlistpresentprice.setText(data.getPrice());
            tvorderlistcount.setText(data.getNum());
            x.image().bind(ivordershopimage, data.getImage(), MyUtils.options);
        }
    }


}
