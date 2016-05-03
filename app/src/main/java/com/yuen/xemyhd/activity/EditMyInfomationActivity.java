package com.yuen.xemyhd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.bean.BaseBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class EditMyInfomationActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private EditText mEtInputInfo;
    private Button mBtnEditInfoOk;
    private String name;
    private String flag;


    private void assignViews() {
        mEtInputInfo = (EditText) findViewById(R.id.et_input_info);
        mBtnEditInfoOk = (Button) findViewById(R.id.btn_edit_info_ok);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mBtnEditInfoOk.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        mIvBtnAdd.setVisibility(View.GONE);
        mTvTitleDec.setText(name);
        mTvTitleDec.setTextColor(Color.WHITE);
        mBtnEditInfoOk.setTextColor(Color.WHITE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_infomation);
        name = getIntent().getStringExtra("name");
        flag = getIntent().getStringExtra("flag");
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_info_ok:
                String info = mEtInputInfo.getText().toString().trim();
                if (TextUtils.isEmpty(info)) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    setAddInfo(info);

                }

                 break;
            case R.id.iv_btn_back:
                finish();
                break;
        }
    }


    private void setAddInfo(final String nickname) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("nickname",nickname);
        map.put("uid",MainActivity.useruid);
        XUtils.xUtilsPost(ContactURL.AddName_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                 Log.d("mafuhua", result.toString());
                String res = result.toString();
                if (flag.equals("1")) {
                    Intent intent = new Intent();
                    intent.putExtra("name", nickname);
                    setResult(100, intent);
                    parseJson(res);
                } else if (flag.equals("2")) {
                    parseJson2(res);
                }
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

    private void parseJson(String res) {
        Gson gson = new Gson();
        BaseBean shopNameBean = gson.fromJson(res, BaseBean.class);
        String code = shopNameBean.getCode();
        if (code.equals("0")) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void parseJson2(String res) {
     /*   Gson gson = new Gson();
        ShopTitleBean shopTitleBean = gson.fromJson(res, ShopTitleBean.class);
        String code = shopTitleBean.getCode();
        if (code.equals("0")) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }*/
    }
}
