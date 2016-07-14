package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.activity.CommodityDecActivity;
import com.yuen.xemyhd.activity.MainActivity;
import com.yuen.xemyhd.activity.OrderActivity;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.OrderListBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class YifuKuanPager extends BasePager {

    private static int typepos = 0;
    private static List<Integer> typeposList = new ArrayList<>();
    private ListView mLvOftenGet;
    private MyAdapter myAdapter;
    private List<OrderListBean.DataBean.ProBean> orderList = new ArrayList<>();
    private List<OrderListBean.DataBean> orderListBeanData;
    private String[] stringArray;
    private Context context;
    private WoOftenGetHolder woOftenGetHolder;
    public YifuKuanPager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_order_list, null);
        mLvOftenGet = (ListView) view.findViewById(R.id.lv_often_get);
        Log.d("mafuhua", "代付款:");
        return view;
    }

    @Override
    public void initData() {
        stringArray = context.getResources().getStringArray(R.array.types);
        getOrderList();
    }

    private void getOrderList() {
        XUtils.xUtilsGet(ContactURL.OrderList_URL + MainActivity.useruid + "/type/1", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "------OrderList_URL-----" + result);
                orderList.clear();
                Gson gson = new Gson();
                OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
                orderListBeanData = orderListBean.getData();
                typepos = 0;
                typeposList.add(typepos);
                for (int i = 0; i < orderListBeanData.size(); i++) {
                    OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                    List<OrderListBean.DataBean.ProBean> proBeanList = dataBean.getPro();
                    typepos = typepos + proBeanList.size();
                    typeposList.add(typepos);
                    for (int j = 0; j < proBeanList.size(); j++) {
                        OrderListBean.DataBean.ProBean proBean = proBeanList.get(j);
                        orderList.add(proBean);
                    }

                }
                myAdapter = new MyAdapter(orderList);
                mLvOftenGet.setAdapter(myAdapter);
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

    class MyAdapter extends DefaultAdapter {

        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            woOftenGetHolder = new WoOftenGetHolder();
            return woOftenGetHolder;
        }
    }

    class WoOftenGetHolder extends BaseHolder<OrderListBean.DataBean.ProBean> {
        public ImageView ivoftenimgtype;
        public TextView tvorderlisttype;
        public ImageView ivordershopimage;
        public TextView tvoftenlistshopname;
        public TextView tvoftenlistprice;
        public TextView tv_order_type;
        public TextView tv_often_list_count;
        private RelativeLayout rl_titile;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_often_get_item, null);
            ivoftenimgtype = (ImageView) root.findViewById(R.id.iv_often_img_type);
            rl_titile = (RelativeLayout) root.findViewById(R.id.rl_titile);
            tvorderlisttype = (TextView) root.findViewById(R.id.tv_order_list_type);
            tv_order_type = (TextView) root.findViewById(R.id.tv_order_type);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvoftenlistshopname = (TextView) root.findViewById(R.id.tv_often_list_shopname);
            tvoftenlistprice = (TextView) root.findViewById(R.id.tv_often_list_price);
            tv_often_list_count = (TextView) root.findViewById(R.id.tv_often_list_count);
            return root;
        }

        @Override
        public void refreshView(final OrderListBean.DataBean.ProBean data, int position) {
            tvoftenlistshopname.setText(data.getName());
            tvoftenlistprice.setText(data.getPrice());
            tv_often_list_count.setText("X" + data.getNum());
            x.image().bind(ivordershopimage, data.getImage());
            Log.d("mafuhua", "typeposListoo:" + typeposList);
            Log.d("mafuhua", "typeposList:" + position);
            if (typeposList.contains(position)) {

                int i = typeposList.indexOf(position);
                final OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                String type = dataBean.getType();
                if (type.equals("1")) {
                    tv_order_type.setText(stringArray[0]);
                    tv_order_type.setTextColor(Color.parseColor("#FEBB24"));
                    tv_order_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, OrderActivity.class);
                            intent.putExtra("orderid",dataBean.getOrder_id());
                            context.startActivity(intent);
                        }
                    });
                } else if (type.equals("2")) {
                    tv_order_type.setText(stringArray[1]);
                } else {
                    tv_order_type.setText(stringArray[2]);
                }
                tvorderlisttype.setText(dataBean.getShop_title());
                rl_titile.setVisibility(View.VISIBLE);
            } else {
                rl_titile.setVisibility(View.GONE);
            }
            ivordershopimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommodityDecActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("id", data.getPro_id());
                    context.startActivity(intent);
                }
            });

        }
    }

}
