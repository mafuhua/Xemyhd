package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.activity.CommodityDecActivity;
import com.yuen.xemyhd.activity.MainActivity;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.OrderListBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class YiFaHuoPager extends BasePager {

    private ListView mLvOftenGet;
    private MyAdapter myAdapter;
    private List<OrderListBean.DataBean.ProBean> orderList = new ArrayList<>();
    private List<OrderListBean.DataBean> orderListBeanData= new ArrayList<>();
    private String[] stringArray;
    private Context context;
    private ProgressBar progressBar;

    public YiFaHuoPager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_order_list, null);
        mLvOftenGet = (ListView) view.findViewById(R.id.lv_often_get);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Log.d("mafuhua", "待收货:" );
        myAdapter = new MyAdapter(orderList);
        mLvOftenGet.setAdapter(myAdapter);
        return view;
    }
    private static int typepos = 0;
    private static List<Integer> typeposList = new ArrayList<>();
    @Override
    public void initData() {
        stringArray = context.getResources().getStringArray(R.array.types);
        getOrderList();
    }

    private void getOrderList() {
        Log.d("mafuhua", "------OrderList_URL-----" + ContactURL.OrderList_URL + MainActivity.useruid+"/type/3");
        XUtils.xUtilsGet(ContactURL.OrderList_URL + MainActivity.useruid+"/type/3", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "------OrderList_URL-----" + result);

                orderList.clear();
                typeposList.clear();
                orderListBeanData.clear();
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
                progressBar.setVisibility(View.GONE);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new WoOftenGetHolder();
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
            Log.d("mafuhua", "orderListBeanData:" + orderListBeanData.size());
            if (typeposList.contains(position)) {

                int i = typeposList.indexOf(position);
                OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                String type = dataBean.getType();
                if (type.equals("1")){
                    tv_order_type.setText(stringArray[0]);
                }else if (type.equals("2")){
                    tv_order_type.setText(stringArray[1]);
                }else{
                    tv_order_type.setTextColor(Color.parseColor("#FEBB24"));
                    tv_order_type.setText("收货");
                }
                tvorderlisttype.setText(dataBean.getShop_title());
                rl_titile.setVisibility(View.VISIBLE);
            } else {
                rl_titile.setVisibility(View.GONE);
            }

            tv_order_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    shanchu(data.getOrder_id());
                }
            });
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

    protected void shanchu(final String order_id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认收货吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                shouhuo(order_id);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }
    public void shouhuo(String order_id){
        HashMap<String, String> map = new HashMap<>();
        map.put("order_id",order_id);
        XUtils.xUtilsPost(ContactURL.SHOUHUO_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                orderList.clear();
                typeposList.clear();
                orderListBeanData.clear();
                myAdapter.notifyDataSetChanged();
                getOrderList();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
