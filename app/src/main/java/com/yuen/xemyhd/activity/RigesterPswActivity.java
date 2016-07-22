package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.bean.DuanXinBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class RigesterPswActivity extends AppCompatActivity {
    private EditText mEtRigesterPsw;
    private EditText mEtRigesterPswd;
    private ImageView mIvBtnRigesterFinish;
    private Context context;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private String tel;

    private void assignViews() {
        context = this;
        mEtRigesterPsw = (EditText) findViewById(R.id.et_rigester_psw);
        mEtRigesterPswd = (EditText) findViewById(R.id.et_rigester_pswd);
        mIvBtnRigesterFinish = (ImageView) findViewById(R.id.iv_btn_rigester_finish);
        if (LoginActivity.login_type.equals("2")) {
            mIvBtnRigesterFinish.setImageResource(R.drawable.zc3x);
        }else if (LoginActivity.login_type.equals("1")){
            mIvBtnRigesterFinish.setImageResource(R.drawable.qr3x2);
        }
        mIvBtnRigesterFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psw = mEtRigesterPsw.getText().toString().trim();
                String pswd = mEtRigesterPswd.getText().toString().trim();
                if (TextUtils.isEmpty(psw) || TextUtils.isEmpty(psw)) {
                    Toast.makeText(context, "密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!psw.equals(pswd)) {
                    Toast.makeText(context, "密码不一致!", Toast.LENGTH_SHORT).show();
                    return;
                }
                getRigester(pswd);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        tel = intent.getStringExtra("tel");
        setContentView(R.layout.activity_rigester_psw);
        SysExitUtil.activityList.add(this);
        assignViews();
    }

    private void getRigester(String pswd) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("tel", tel);
        map.put("pwd", pswd);
        XUtils.xUtilsPost(ContactURL.Rigester_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "---------Rigester_URL---------" + result.toString());
                Gson gson = new Gson();
                DuanXinBean duanXinBean = gson.fromJson(result, DuanXinBean.class);
                if (duanXinBean.code.equals("0")) {
                    Toast.makeText(context, "注册成功，请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RigesterPswActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(context, duanXinBean.msg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RigesterPswActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
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
}

