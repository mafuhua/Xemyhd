package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.MarketListBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeMarketListActivity extends AppCompatActivity {
    private LinearLayout mLayoutTitleBar;
    private List<String> wodeItemDec = new ArrayList<String>(Arrays.asList("订单", "积分",
            "我常买", "收货地址", "我的分享", "邀请好友", "客服中心", "设置"));
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private ListView mLvMarketList;
    private Context context;
    private MyAdapter myAdapter;
    public static List<MarketListBean.DataBean> marketListBeanData;

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLvMarketList = (ListView) findViewById(R.id.lv_market_list);
        mTvTitleDec.setText("超市列表");
        mTvTitleDec.setTextColor(Color.WHITE);
        mLvMarketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HomeMarketActivity.class);
                String id1 = marketListBeanData.get(position).getId();
                intent.putExtra("id",id1);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_market_list);
        assignViews();
        getMarketList();
    }

    public void getMarketList() {
        HashMap<String, String> map = new HashMap<>();
        if (MainActivity.province==null) {
            MainActivity.getLoc();

        }
            map.put("sheng", MainActivity.province);
            map.put("shi", MainActivity.city);
            map.put("qu", MainActivity.district);
            map.put("adds", MainActivity.street);
            Log.d("mafuhua", "GetMarketList_URL------" + map.toString());
            XUtils.xUtilsPost(ContactURL.GetMarketList_URL, map, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("mafuhua", "GetMarketList_URL------" + result);
                    Gson gson = new Gson();
                    MarketListBean marketListBean = gson.fromJson(result, MarketListBean.class);
                    marketListBeanData = marketListBean.getData();
                    myAdapter = new MyAdapter(marketListBeanData);
                    mLvMarketList.setAdapter(myAdapter);
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
            return new ViewHolder();
        }
    }

    class ViewHolder extends BaseHolder<MarketListBean.DataBean> {
        private TextView tvmarketlistitemtitle;
        private TextView tvmarketlistitemcontent;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_market_list_item, null);
            tvmarketlistitemtitle = (TextView) root.findViewById(R.id.tv_market_listitem_title);
            tvmarketlistitemcontent = (TextView) root.findViewById(R.id.tv_market_listitem_content);
            return root;
        }

        @Override
        public void refreshView(MarketListBean.DataBean data, int position) {
            tvmarketlistitemtitle.setText(data.getShop_title());
            tvmarketlistitemcontent.setText(data.getShop_shi() + data.getShop_qu() + data.getShop_adds());

        }
    }

}



