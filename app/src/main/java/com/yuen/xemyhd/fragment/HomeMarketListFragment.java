package com.yuen.xemyhd.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.activity.CommodityDecActivity;
import com.yuen.xemyhd.utils.SysExitUtil;

import org.xutils.image.ImageOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class HomeMarketListFragment extends AppCompatActivity {
    public boolean isLoadingMore = false;//是否加载下一页
    public boolean isRefresh = false;//是否刷新
    private EditText mEtInputSearch;
    private ImageView mIvBtnSearch;
    private GridViewWithHeaderAndFooter mGvCommoditylist;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private List<String> proPriceList = new ArrayList<>();
    private List<String> proImageList = new ArrayList<>();
    private List<String> proIDList = new ArrayList<>();
    private List<String> proSheLvesList = new ArrayList<>();
    private List<String> proNameList = new ArrayList<>();
    private List<String> proInventoryList = new ArrayList<>();
    private ImageOptions options;
    private int page = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:

                    mSwipeRefreshLayout.setRefreshing(false);
                    //adapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    };
    private View footerView;

    private void assignViews() {
        context = this;
        mEtInputSearch = (EditText) findViewById(R.id.et_input_search);
        mIvBtnSearch = (ImageView) findViewById(R.id.iv_btn_search);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mGvCommoditylist = (GridViewWithHeaderAndFooter) findViewById(R.id.gv_commoditylist);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        footerView = layoutInflater.inflate(R.layout.refresh_footer, null);
        mGvCommoditylist.addFooterView(footerView);
        footerView.setVisibility(View.GONE);
        mGvCommoditylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CommodityDecActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("commodityid", proIDList.get(position));
                context.startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Toast.makeText(context, "正在刷新", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override

                    public void run() {
                        if (!isRefresh) {
                            isLoadingMore = true;
                            Log.d("mafuhua", "刷新");
                            proNameList.clear();
                            proImageList.clear();
                            proPriceList.clear();
                            proInventoryList.clear();
                            proIDList.clear();
                            proSheLvesList.clear();
                            page = 0;
                            mHandler.sendEmptyMessage(1);
                        }

                    }
                }).start();

            }
        });
        options = new ImageOptions.Builder()
                //设置使用缓存
                .setUseMemCache(true)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_market_fragment_list);
        SysExitUtil.activityList.add(this);
        //getCommodityList();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        assignViews();
        getShop(id);
    }
    public void getShop(String id){
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
       /* XUtils.xUtilsPost(url, map, new XCallback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "---------"+result);
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
        });*/
    }
    @Override
    protected void onResume() {
        super.onResume();
        proNameList.clear();
        proImageList.clear();
        proPriceList.clear();
        proInventoryList.clear();
        proIDList.clear();
        proSheLvesList.clear();
        page = 0;
    }




}
