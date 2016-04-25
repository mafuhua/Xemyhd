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

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
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

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLvMarketList = (ListView) findViewById(R.id.lv_market_list);
        myAdapter = new MyAdapter(wodeItemDec);
        mLvMarketList.setAdapter(myAdapter);
        mTvTitleDec.setText("超市列表");
        mTvTitleDec.setTextColor(Color.WHITE);

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
        map.put("sheng", MainActivity.province);
        map.put("shi", MainActivity.city);
        map.put("qu", MainActivity.district);
        map.put("adds", MainActivity.street);
        XUtils.xUtilsPost(ContactURL.GetMarketList_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "GetMarketList_URL------" + result);
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

    class ViewHolder extends BaseHolder<String> {
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
        public void refreshView(String data, int position) {
            Log.d("mafuhua", "posit*****ion:" + position);
            tvmarketlistitemtitle.setText(data);
        }
    }

}



