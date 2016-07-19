package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.bean.AddresBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.GsonUtil;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

import xlkd.provinceslinkage.ProvinceLinkActivity;

public class AddressManagerDecActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private TextView mTvAddressDecLocation;
    private EditText mTvAddressDecAddress;
    private EditText mTvAddressDecUsername;
    private EditText mTvAddressDecTelphone;
    private EditText mTvAddressDecPostCode;
    private Button mBtnAddressDelete;
    private Button mBtnAddressSave;
    private LinearLayout mLlAddressDecLocation;
    private String addsid;
    private String addLoc;
    private CheckBox mCbSettingAddress;
    private String sheng;
    private String shi;
    private String qu;
    private String addsheng;
    private String addshi;
    private String addqu;
    private String orderid;
    private String mTvAddressDecAd;
    private String mTvAddressDecUser;
    private String phone;

    private void assignViews() {
        context = this;
        mTvAddressDecLocation = (TextView) findViewById(R.id.tv_address_dec_location);
        mLlAddressDecLocation = (LinearLayout) findViewById(R.id.ll_address_dec_location);
        mTvAddressDecAddress = (EditText) findViewById(R.id.tv_address_dec_address);
        mTvAddressDecUsername = (EditText) findViewById(R.id.tv_address_dec_username);
        mTvAddressDecTelphone = (EditText) findViewById(R.id.tv_address_dec_telphone);
        mTvAddressDecPostCode = (EditText) findViewById(R.id.tv_address_dec_postcode);
        mBtnAddressDelete = (Button) findViewById(R.id.btn_address_delete);
        mBtnAddressSave = (Button) findViewById(R.id.btn_address_save);
        mBtnAddressDelete = (Button) findViewById(R.id.btn_address_delete);
        mBtnAddressSave = (Button) findViewById(R.id.btn_address_save);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mCbSettingAddress = (CheckBox) findViewById(R.id.cb_setting_address);
        mTvTitleDec.setText("收货地址管理");
        mTvTitleDec.setTextColor(Color.WHITE);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnAddressSave.setTextColor(Color.WHITE);
        mTvAddressDecLocation.setTextColor(Color.BLACK);
        mBtnAddressDelete.setOnClickListener(this);
        mLlAddressDecLocation.setOnClickListener(this);
        mBtnAddressSave.setOnClickListener(this);
        Intent intent = getIntent();
        addsid = intent.getStringExtra("id");
        orderid = intent.getStringExtra("orderid");
        sheng = intent.getStringExtra("sheng");
        shi = intent.getStringExtra("shi");
        qu = intent.getStringExtra("qu");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager_dec);
        SysExitUtil.activityList.add(this);

        assignViews();
        getAdd();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_address_delete:
                delAdd();

                break;
            case R.id.btn_address_save:
                mTvAddressDecAd = mTvAddressDecAddress.getText().toString();
                if (TextUtils.isEmpty(mTvAddressDecAd)) {
                    Toast.makeText(context, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mTvAddressDecUser = mTvAddressDecUsername.getText().toString();
                if (TextUtils.isEmpty(mTvAddressDecUser)) {
                    Toast.makeText(context, "收件人姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                phone = mTvAddressDecTelphone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(context, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String telRegex = "[1][3587]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
                if (!phone.matches(telRegex)) {
                    Toast.makeText(context, "请检查输入的手机号", Toast.LENGTH_SHORT).show();
                   return;
                }

                /*Log.d("mafuhua", addLoc
                        + MyUtils.getInputString(context, mTvAddressDecAddress, )
                        + MyUtils.getInputString(context, mTvAddressDecUsername, "收件人姓名不能为空")
                        + MyUtils.getInputString(context, mTvAddressDecPostCode, "")
                        + MyUtils.getInputString(context, mTvAddressDecTelphone, "手机号码不能为空")
                        + mCbSettingAddress.isChecked());*/
                editAdd();

                break;
            case R.id.ll_address_dec_location:
                Intent intent = new Intent(context, ProvinceLinkActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityForResult(intent, 100);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 101:
                if (data.getStringExtra("add") != null) {
                    addLoc = data.getStringExtra("add");
                    sheng = data.getStringExtra("sheng");
                    shi = data.getStringExtra("shi");
                    qu = data.getStringExtra("qu");
                    mTvAddressDecLocation.setText(addLoc.replace(",", ""));
                }
                break;
            default:
                break;

        }
    }

    public void getAdd() {
        XUtils.xUtilsGet(ContactURL.AddDec_URL + MainActivity.useruid + "/id/" + addsid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "---------" + result);
                AddresBean addresBean = GsonUtil.fromJson(result, AddresBean.class);
                AddresBean.DataBean dataBean = addresBean.getData();
                mTvAddressDecLocation.setText(dataBean.getSheng() + dataBean.getShi() + dataBean.getQu());
                mTvAddressDecAddress.setText(dataBean.getAdds());
                mTvAddressDecTelphone.setText(dataBean.getTel());
                mTvAddressDecUsername.setText(dataBean.getName());
                mTvAddressDecPostCode.setText(dataBean.getCode());
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

    public void editAdd() {
        Toast.makeText(context, "正在保存", Toast.LENGTH_SHORT).show();
        HashMap<String, String> map = new HashMap<>();
        map.put("sheng", sheng);
        map.put("shi", shi);
        if (addsid == null) {
            map.put("id", "0");
        } else {
            map.put("id", addsid);
        }
        if (mCbSettingAddress.isChecked()) {
            map.put("moren", "1");
        } else {
            map.put("moren", "0");
        }
        map.put("uid", MainActivity.useruid);
        map.put("order_id", orderid + "");
        map.put("qu", qu);
        map.put("adds", mTvAddressDecAd);
        map.put("name", mTvAddressDecUser);
        map.put("tel", phone);
        map.put("code", mTvAddressDecPostCode.getText().toString().trim());
        XUtils.xUtilsPost(ContactURL.EditAdds_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "----EditAdds_URL-----" + result);
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
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

    public void delAdd() {
        Toast.makeText(context, "正在删除", Toast.LENGTH_SHORT).show();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", MainActivity.useruid);
        map.put("id", addsid);
        Log.d("mafuhua", "---addsid---" + addsid);
        XUtils.xUtilsPost(ContactURL.DelAdds_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "----DelAdds_URL---" + result);


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

        SystemClock.sleep(1000);
        finish();
    }

}
