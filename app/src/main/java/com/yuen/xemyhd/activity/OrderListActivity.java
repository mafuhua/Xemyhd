package com.yuen.xemyhd.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gigamole.library.navigationtabstrip.NavigationTabStrip;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.pagers.BasePager;
import com.yuen.xemyhd.pagers.LazyViewPager;
import com.yuen.xemyhd.pagers.OrderListPager;
import com.yuen.xemyhd.pagers.WeifaHuoPager;
import com.yuen.xemyhd.pagers.YiFaHuoPager;
import com.yuen.xemyhd.pagers.YifuKuanPager;
import com.yuen.xemyhd.utils.SysExitUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private NavigationTabStrip mTopNavigationTabStrip;
    private Context context;

    private LazyViewPager viewpager;
    private List<BasePager> pagers = new ArrayList<BasePager>();
   // private FragAdapter adapter;

    private void assignViews() {
        context = this;
        mTopNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_top);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        viewpager = (LazyViewPager) findViewById(R.id.viewpager);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvTitleDec.setText("我的订单");
        mTvTitleDec.setTextColor(Color.WHITE);

        mIvBtnBack.setOnClickListener(this);
        //准备数据源
        pagers.add(new OrderListPager(context));
        pagers.add(new YifuKuanPager(context));
        pagers.add(new WeifaHuoPager(context));
        pagers.add(new YiFaHuoPager(context));
        //给viewPager设置适配器

        viewpager.setAdapter(new MyViewPagerAdapter());
        mTopNavigationTabStrip.setTabIndex(0, true);
        viewpager.setOnPageChangeListener(new LazyViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTopNavigationTabStrip.setTabIndex(position, true);
            }
        });
        mTopNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                viewpager.setCurrentItem(index);
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });


    /*    //准备数据源
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new OrderListPager());
        //    fragments.add(new WeifuKuanPager());
        fragments.add(new YifuKuanPager());
        fragments.add(new WeifaHuoPager());
        fragments.add(new YiFaHuoPager());
        //  fragments.add(new YishouHuoPager());
        adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        // viewpager.setCurrentItem(0);
        //给viewPager设置适配器

        // viewpager.setAdapter(new MyViewPagerAdapter());
        mTopNavigationTabStrip.setTabIndex(0, true);
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("mafuhua", "这是页面"position);
                //  mTopNavigationTabStrip.setTabIndex(position, true);
            }
        });
        mTopNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.d("mafuhua", "这是低页面"indextitle);
                //  viewpager.setCurrentItem(index);
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        SysExitUtil.activityList.add(this);

        assignViews();
        // getOrderList();
    }

    public List<BasePager> getPagers() {
        return pagers;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
        }
    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagers.size();
        }

        /**
         * 1.根据position，返回对应的view
         * 2.view添加到container
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d("mafuhua", "instantiateItem:"+position);
            BasePager currentPager = pagers.get(position);
            View currentPagerView = currentPager.initView();
            container.addView(currentPagerView);
            //一旦初始化界面，就需要初始化数据
            currentPager.initData();
            return currentPagerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println("destroyItem:"+position);
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


    }

    /**
     * FragmentPager适配器
     *
     * @author wwj
     */
    /*public class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;


        public FragAdapter(FragmentManager fm) {
            super(fm);
        }

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("mafuhua", "instantiateItem:"position);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }*/
}
