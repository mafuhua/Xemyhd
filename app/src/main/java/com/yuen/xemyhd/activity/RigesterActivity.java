package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.bean.DuanXinBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class RigesterActivity extends AppCompatActivity {
    private EditText mEtRigesterTel;
    private EditText mEtRigesterYzm;
    private Button mTvGetYzm;
    private ImageView mIvBtnRigesterNext;
    private Context context;
    private String fanhuiyzm;
    private String shoujihao;
    private void assignViews() {
        context = this;
        mEtRigesterTel = (EditText) findViewById(R.id.et_rigester_tel);
        mEtRigesterYzm = (EditText) findViewById(R.id.et_rigester_yzm);
        mTvGetYzm = (Button) findViewById(R.id.tv_get_yzm);
        mIvBtnRigesterNext = (ImageView) findViewById(R.id.iv_btn_rigester_next);
        mTvGetYzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mEtRigesterTel.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
                if (tel.matches(telRegex)) {
                    Toast.makeText(context, "正在获取验证码", Toast.LENGTH_SHORT).show();
                    getYzm(tel);
                } else {
                    Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

            }


        });
        mIvBtnRigesterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String yzm = mEtRigesterYzm.getText().toString().trim();
                if (TextUtils.isEmpty(yzm)) {
                    Toast.makeText(context, "验证码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!yzm.equals(fanhuiyzm)){
                    Toast.makeText(context, "验证码不一致!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(RigesterActivity.this, RigesterPswActivity.class);
                intent.putExtra("tel",shoujihao);
                startActivity(intent);
                finish();
            }
        });
    }
    private void getYzm(String tel) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("tel", tel);
        XUtils.xUtilsPost(ContactURL.Duanxin_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "---------Duanxin_URL---------" + result.toString());
                Gson gson = new Gson();
                DuanXinBean duanXinBean = gson.fromJson(result, DuanXinBean.class);
                Toast.makeText(context, "获取验证码成功", Toast.LENGTH_SHORT).show();
                fanhuiyzm = duanXinBean.yan + "";
                shoujihao = duanXinBean.tel + "";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigester_next);
        SysExitUtil.activityList.add(this);

        assignViews();
    }
}
