package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.fragment.FragmentFractory;
import com.yuen.xemyhd.fragment.GouWuCheFragment2;
import com.yuen.xemyhd.utils.MyApplication;
import com.yuen.xemyhd.utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明AMapLocationClient类对象
    public static AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public static AMapLocationClientOption mLocationOption = null;
    private RadioButton mRbHomeShouye;
    private RadioButton mRbHomeKuaidi;
    private RadioButton mRbHomeGouwuche;
    private RadioButton mRbHomeWode;
    private Context context;
    private FrameLayout mFlHomeContent;
    private FragmentManager supportFragmentManager;
    private Fragment homeFragment;
    private Fragment kuaiDiFragment;
    private Fragment gouWuCheFragment;
    private Fragment woDeFragment;
    private Fragment currentFragment;
    private FragmentTransaction transaction;
    private RadioGroup mRgHome;
    private TextView mTvTitleDec;
    private TextView mTvTitleEdit;
    public static String province;
    public static String city;
    public static String district;
    public static String street;
    //声明定位回调监听器
    public static AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    df.format(date);//定位时间
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    province = aMapLocation.getProvince();
                    city = aMapLocation.getCity();
                    district = aMapLocation.getDistrict();
                    street = aMapLocation.getStreet();
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    Log.d("mafuhua", province + city + district + street);
                    //  aMapLocation.getAOIName();//获取当前定位点的AOI信息
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    private void assignViews() {
        context = this;
        mRbHomeShouye = (RadioButton) findViewById(R.id.rb_home_shouye);
        mRgHome = (RadioGroup) findViewById(R.id.rg_home);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mTvTitleEdit = (TextView) findViewById(R.id.tv_title_edit);
        mFlHomeContent = (FrameLayout) findViewById(R.id.fl_home_content);
        mRbHomeKuaidi = (RadioButton) findViewById(R.id.rb_home_kuaidi);
        mRbHomeGouwuche = (RadioButton) findViewById(R.id.rb_home_gouwuche);
        mRbHomeWode = (RadioButton) findViewById(R.id.rb_home_wode);
        Drawable drawable = getResources().getDrawable(R.drawable.shouye);
        mTvTitleDec.setTextColor(Color.WHITE);
        int dp = MyUtils.dip2px(context, 20);
        drawable.setBounds(0, 0, dp, dp);
        mRbHomeShouye.setCompoundDrawables(null, drawable, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.kuaidi);
        drawable1.setBounds(0, 0, dp, dp);
        mRbHomeKuaidi.setCompoundDrawables(null, drawable1, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.gouwuche);
        drawable2.setBounds(0, 0, dp, dp);
        mRbHomeGouwuche.setCompoundDrawables(null, drawable2, null, null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.wode);
        drawable3.setBounds(0, 0, dp, dp);
        mRbHomeWode.setCompoundDrawables(null, drawable3, null, null);
        mRbHomeShouye.setOnClickListener(this);
        mRbHomeKuaidi.setOnClickListener(this);
        mRbHomeGouwuche.setOnClickListener(this);
        mRbHomeWode.setOnClickListener(this);
        mTvTitleEdit.setOnClickListener(this);
        mRgHome.check(R.id.rb_home_shouye);
        supportFragmentManager = getSupportFragmentManager();
        homeFragment = FragmentFractory.getInstance().createFragment(0);
        kuaiDiFragment = FragmentFractory.getInstance().createFragment(1);
        gouWuCheFragment = FragmentFractory.getInstance().createFragment(2);
        woDeFragment = FragmentFractory.getInstance().createFragment(3);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_home_content, homeFragment, "homeFragment")
                .add(R.id.fl_home_content, kuaiDiFragment, "kuaiDiFragment").hide(kuaiDiFragment)
                .add(R.id.fl_home_content, gouWuCheFragment, "gouWuCheFragment").hide(gouWuCheFragment)
                .add(R.id.fl_home_content, woDeFragment, "woDeFragment").hide(woDeFragment)
                .show(homeFragment)
                .commit();
        currentFragment = homeFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Log.d("mafuhua", "savedInstanceState----------:" + savedInstanceState);
            savedInstanceState = null;
            if (savedInstanceState == null) {
                Log.d("mafuhua", "savedInstanceState**********----------:" + savedInstanceState);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        getLoc();


    }

    public static void getLoc() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        //  mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onClick(View v) {
        transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.rb_home_shouye:
                mTvTitleDec.setText("首页");
                if (currentFragment != homeFragment) {
                    switchContent(currentFragment, homeFragment, "首页", View.GONE);
                }
                break;
            case R.id.rb_home_kuaidi:
                switchContent(currentFragment, kuaiDiFragment, "快递", View.GONE);
                break;
            case R.id.rb_home_gouwuche:
                mTvTitleEdit.setTextColor(Color.WHITE);
                switchContent(currentFragment, gouWuCheFragment, "购物车", View.VISIBLE);
                GouWuCheFragment2.getdata();
                break;
            case R.id.rb_home_wode:
                switchContent(currentFragment, woDeFragment, "个人中心", View.GONE);
                break;
            case R.id.tv_title_edit:
                Intent intent = new Intent(this, EditGouWuCheActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
        }

    }

    private void setGouwuche() {
        GouWuCheFragment2.checkalltype = false;
        GouWuCheFragment2.mCbGouwuche.setChecked(GouWuCheFragment2.checkalltype);
        GouWuCheFragment2.mTvGouWuCheZongJia.setText("合计:0.0");
        GouWuCheFragment2.mBtnGouWuCheJieSuan.setText("结算");
    }


    public void switchContent(Fragment from, Fragment to, String title, int gone) {
        mTvTitleEdit.setVisibility(gone);
        setGouwuche();
        mTvTitleDec.setText(title);
        currentFragment = to;
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(R.id.fl_home_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }

}
