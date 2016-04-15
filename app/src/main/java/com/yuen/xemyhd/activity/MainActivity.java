package com.yuen.xemyhd.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.fragment.FragmentFractory;
import com.yuen.xemyhd.utils.MyUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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


    private void assignViews() {
        context = this;
        mRbHomeShouye = (RadioButton) findViewById(R.id.rb_home_shouye);
        mRgHome = (RadioGroup) findViewById(R.id.rg_home);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
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
        mRgHome.check(R.id.rb_home_shouye);
        supportFragmentManager = getSupportFragmentManager();
        homeFragment = FragmentFractory.getInstance().createFragment(0);
        kuaiDiFragment = FragmentFractory.getInstance().createFragment(1);
        gouWuCheFragment = FragmentFractory.getInstance().createFragment(2);
        woDeFragment = FragmentFractory.getInstance().createFragment(3);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_home_content, homeFragment)
                .add(R.id.fl_home_content, kuaiDiFragment).hide(kuaiDiFragment)
                .add(R.id.fl_home_content, gouWuCheFragment).hide(gouWuCheFragment)
                .add(R.id.fl_home_content, woDeFragment).hide(woDeFragment)
                .show(homeFragment)
                .commit();
        currentFragment = homeFragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();

    }

    @Override
    public void onClick(View v) {
        transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.rb_home_shouye:
                mTvTitleDec.setText("首页");
                if (currentFragment!=homeFragment){
                    switchContent(currentFragment,homeFragment);
                    currentFragment = homeFragment;
                }
                break;
            case R.id.rb_home_kuaidi:
                mTvTitleDec.setText("快递");
                switchContent(currentFragment,kuaiDiFragment);
                currentFragment = kuaiDiFragment;
                break;
            case R.id.rb_home_gouwuche:
                mTvTitleDec.setText("购物车");
                switchContent(currentFragment,gouWuCheFragment);
                currentFragment = gouWuCheFragment;
                break;
            case R.id.rb_home_wode:
                mTvTitleDec.setText("个人中心");
                switchContent(currentFragment,woDeFragment);
                currentFragment = woDeFragment;
                break;
        }

    }


    public void switchContent(Fragment from, Fragment to) {
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(R.id.fl_home_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }

}
