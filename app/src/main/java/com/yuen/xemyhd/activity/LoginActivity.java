package com.yuen.xemyhd.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseActivity;
import com.yuen.xemyhd.bean.LoginBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtLoginUsername;
    private EditText mEtLoginPassword;
    private TextView mTvLoginForgetPassword;
    private ImageView mIvBtnLogin;
    private SharedPreferences sharedPreferences;
    private String username;
    private String password;
    private TextView mTvLoginRigister;
    public static String login_type;
    private void assignViews() {
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        mEtLoginUsername = (EditText) findViewById(R.id.et_login_username);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mTvLoginForgetPassword = (TextView) findViewById(R.id.tv_login_forget_password);
        mTvLoginRigister = (TextView) findViewById(R.id.tv_login_rigester);
        mIvBtnLogin = (ImageView) findViewById(R.id.iv_btn_login);
        mTvLoginForgetPassword.setTextColor(Color.WHITE);
        mTvLoginRigister.setTextColor(Color.WHITE);
        mIvBtnLogin.setOnClickListener(this);
        mTvLoginForgetPassword.setOnClickListener(this);
        mTvLoginRigister.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SysExitUtil.activityList.add(this);

        assignViews();
        boolean rempsw = sharedPreferences.getBoolean("rempsw", false);
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
        if (rempsw) {
            mEtLoginUsername.setText(username);
            mEtLoginPassword.setText(password);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((sharedPreferences.getString("lgusername", "").length() > 1) && (sharedPreferences.getString("lgusername", "").length() > 1)) {
            //  Log.d("mafuhua", "******"+sharedPreferences.getString("username", ""));
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_btn_login:
              /*  String userName = mEtLoginUsername.getText().toString().trim();
                String password = mEtLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                    break;

                }*/
                login();
                break;
            case R.id.tv_login_forget_password:
                login_type = "1";
                intent = new Intent(LoginActivity.this, ForgetPSWActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_rigester:
                login_type = "2";
                intent = new Intent(LoginActivity.this, RigesterActivity.class);
                startActivity(intent);
                break;

        }
    }


    private void login() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "admin");
        map.put("password", "123456");
        XUtils.xUtilsPost(ContactURL.LOGIN_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                  Log.d("mafuhua","----------LOGIN_URL---------"+ result);
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                LoginBean.DataBean dataBean = loginBean.getData();
                sharedPreferences.edit()
                        .putString("username", "admin")
                        .putString("password", "123456")
                        .putString("lgusername", "admin")
                        .putString("lgpassword", "123456")
                        .putString("uid", dataBean.getUid())
                        .putString("token", dataBean.getToken())
                        .putString("tel", dataBean.getTel()).apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", isOnCallback + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SysExitUtil.exit();
    }

}
