package com.yuen.xemyhd.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseActivity;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SettingActivity extends BaseActivity {
    private List settingString = new ArrayList(Arrays.asList("意见反馈", "检查更新", "清除缓存", "帮助中心", "关于我们", "退出"));
    private ListView mLvSetting;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private CheckBox mCbSettingMessage;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private MyAdapter myAdapter;
    private SharedPreferences sharedPreferences;

    private void assignViews() {
        context = this;
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mCbSettingMessage = (CheckBox) findViewById(R.id.cb_setting_message);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLvSetting = (ListView) findViewById(R.id.lv_setting);
        mTvTitleDec.setText("设置");
        mTvTitleDec.setTextColor(Color.WHITE);
        mIvBtnAdd.setVisibility(View.GONE);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        myAdapter = new MyAdapter(settingString);
        mLvSetting.setAdapter(myAdapter);
        mLvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Intent intent;


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(context, SettingOpinionEditActivity.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        checkUpdateAPK();
                        break;
                    case 2:
                        clearDialog();
                        break;
                    case 3:
                        intent = new Intent(context, SettingOurActivity.class);
                        context.startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(context, SettingOurActivity.class);
                        context.startActivity(intent);
                        break;
                    case 5:
                        logoutDialog();
                        break;

                }
            }
        });
    }

    private void checkUpdateAPK() {
        ProgressDialog mypDialog = new ProgressDialog(context);
        //实例化
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置进度条风格，风格为圆形，旋转的
        //设置ProgressDialog 标题
        mypDialog.setMessage("检查更新");
        //设置ProgressDialog 提示信息
        mypDialog.setIndeterminate(false);
        //设置ProgressDialog 的进度条是否不明确
        mypDialog.setCancelable(true);
        //设置ProgressDialog 是否可以按退回按键取消
        mypDialog.show();
        //让ProgressDialog显示

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        assignViews();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void clearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setMessage("确认清除吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

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

    protected void logoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setMessage("确认退出吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                sharedPreferences.edit().putString("lgusername", "")
                        .putString("lgpassword", "").apply();
             /*   Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);*/
                finish();

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

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new SettingHolder();
        }
    }
    class  SettingHolder extends BaseHolder<String> {
        private TextView tvshopmanagerleft;
        private TextView tvshopmanagerright;
        @Override
        public View initView() {
            View view = View.inflate(context, R.layout.layout_wode_setting_item, null);
            tvshopmanagerleft = (TextView) view.findViewById(R.id.tv_shop_manager_left);
            tvshopmanagerright = (TextView) view.findViewById(R.id.tv_shop_manager_right);
            return view;
        }

        @Override
        public void refreshView(String data,int position) {
            tvshopmanagerleft.setText(data);
            tvshopmanagerright.setText("");
        }

    }
}
