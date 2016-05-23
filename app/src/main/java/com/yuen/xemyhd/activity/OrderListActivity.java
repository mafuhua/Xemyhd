package com.yuen.xemyhd.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.OrderListBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView mLvOftenGet;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private MyAdapter myAdapter;
    private Context context;
    private List<OrderListBean.DataBean.ProBean> orderList = new ArrayList<>();
    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLvOftenGet = (ListView) findViewById(R.id.lv_often_get);
        mTvTitleDec.setText("我的订单");
        mTvTitleDec.setTextColor(Color.WHITE);
        myAdapter = new MyAdapter(orderList);
        mLvOftenGet.setAdapter(myAdapter);
        mIvBtnBack.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        SysExitUtil.activityList.add(this);

        assignViews();
        getOrderList();
    }

    private void getOrderList() {
        XUtils.xUtilsGet(ContactURL.OrderList_URL+ MainActivity.useruid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua","------OrderList_URL-----" +result);
                orderList.clear();
                Gson gson = new Gson();
                OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
                List<OrderListBean.DataBean> orderListBeanData =  orderListBean.getData();
                for (int i = 0; i < orderListBeanData.size(); i++) {
                    OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                    List<OrderListBean.DataBean.ProBean> proBeanList = dataBean.getPro();
                    for (int j = 0; j < proBeanList.size(); j++) {
                        OrderListBean.DataBean.ProBean proBean = proBeanList.get(j);
                        orderList.add(proBean);
                    }

                }
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

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
        }
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
        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_often_get_item, null);
            ivoftenimgtype = (ImageView) root.findViewById(R.id.iv_often_img_type);
            tvorderlisttype = (TextView) root.findViewById(R.id.tv_order_list_type);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvoftenlistshopname = (TextView) root.findViewById(R.id.tv_often_list_shopname);
            tvoftenlistprice = (TextView) root.findViewById(R.id.tv_often_list_price);
            return root;
        }
        @Override
        public void refreshView(OrderListBean.DataBean.ProBean data, int position) {
            tvoftenlistshopname.setText(data.getName());
            x.image().bind(ivordershopimage,data.getImage());
        }
    }
}
