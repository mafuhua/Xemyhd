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
import com.yuen.xemyhd.bean.ChanMaiBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WoOftenGetActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView mLvOftenGet;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private MyAdapter myAdapter;
    private Context context;
    private List<String> wodeItemDec = new ArrayList<String>(Arrays.asList("订单", "积分",
            "我常买", "收货地址", "我的分享", "邀请好友", "客服中心", "设置"));
    private List<ChanMaiBean.DataBean> chanMaiBeanData;

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLvOftenGet = (ListView) findViewById(R.id.lv_often_get);
        mTvTitleDec.setText("我常买");
        mTvTitleDec.setTextColor(Color.WHITE);

        mIvBtnBack.setOnClickListener(this);

        XUtils.xUtilsGet(ContactURL.CHANGMAI_URL+MainActivity.useruid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua","---CHANGMAI_URL---" +ContactURL.CHANGMAI_URL+MainActivity.useruid);
                Gson gson = new Gson();
                ChanMaiBean chanMaiBean = gson.fromJson(result, ChanMaiBean.class);
                chanMaiBeanData = chanMaiBean.getData();
                myAdapter = new MyAdapter(chanMaiBeanData);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_often_get);
        SysExitUtil.activityList.add(this);

        assignViews();
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

    class WoOftenGetHolder extends BaseHolder<ChanMaiBean.DataBean> {
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
        public void refreshView(ChanMaiBean.DataBean data, int position) {
            tvoftenlistshopname.setText(data.getName());
            tvoftenlistprice.setText(data.getPrice());
            x.image().bind(ivordershopimage,ContactURL.BASEIMG_URL+data.getImage(), MyUtils.options);
            tvoftenlistshopname.setText(data.getName());
            tvoftenlistshopname.setText(data.getName());
        }
    }
}
