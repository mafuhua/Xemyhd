package com.yuen.xemyhd.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
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
import com.yuen.xemyhd.lisetner.MyReceiveMessageListener;
import com.yuen.xemyhd.lisetner.MyReceivePushMessageListener;
import com.yuen.xemyhd.utils.Friend;
import com.yuen.xemyhd.utils.MyApplication;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.SysExitUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明AMapLocationClient类对象
    public static AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public static AMapLocationClientOption mLocationOption = null;
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
    public static String useruid;
    public static String usertel;
    public static String token;
    public static String nickname;
    public static String icon;

    /**
     * Notification管理
     */
    public static NotificationManager mNotificationManager;
    /**
     * Notification构造器
     */
    public static NotificationCompat.Builder mBuilder;
    /**
     * Notification的ID
     */
    public static int notifyId = 100;
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
    private SharedPreferences sharedPreferences;
    private List<Friend> userIdList;



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

    /**
     * 初始化通知栏
     */
    public static void initNotify() {

        mNotificationManager = (NotificationManager) MyApplication.context.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(MyApplication.context);
        mBuilder.setContentTitle("小而美")
                .setContentText("您有一条新消息")
                .setAutoCancel(true)
//				.setNumber(number)//显示数量
                .setTicker("小而美:您有一条新消息")//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
//				.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.drawable.logo2x);
        Intent resultIntent = new Intent(MyApplication.context, ConvertalkActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.context, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        icon = sharedPreferences.getString("icon", "");
    }

    private void assignViews() {
        context = this;
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        useruid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");
        usertel = sharedPreferences.getString("tel", "");
        icon = sharedPreferences.getString("icon", "");
        nickname = sharedPreferences.getString("nickname", "");
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
     /*   userIdList = new ArrayList<Friend>();
       userIdList.add(new Friend("352","联通","http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        userIdList.add(new Friend("456", "移动", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        RongIM.setUserInfoProvider(this, true);*/
        /**
         * 设置接收 push 消息的监听器。
         */
        RongIM.setOnReceivePushMessageListener(new MyReceivePushMessageListener());
        /**
         *  设置接收消息的监听器。
         */
        RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理取 Token

                Log.e("MainActivity", "——Connect Token— -" + "失效的状态处理，需要重新获取 Token");
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError— -" + errorCode);
            }
        });


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
        SysExitUtil.activityList.add(this);

        setContentView(R.layout.activity_main);
        assignViews();
        getLoc();


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

    /*  @Override
      public UserInfo getUserInfo(String userId) {
          for (Friend i : userIdList) {
              if (i.getUserId().equals(userId)) {
                  Log.e("mafuhua", i.getPortraitUri());
                  return new UserInfo(i.getUserId(),i.getUserName(),Uri.parse(i.getPortraitUri()));
              }
          }
          return null;
      }*/
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
