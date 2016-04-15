package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuen.xemyhd.R;

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
        mTvTitleDec.setText("收货地址管理");
        mTvTitleDec.setTextColor(Color.WHITE);

        mBtnAddressSave.setTextColor(Color.WHITE);
        mTvAddressDecLocation.setTextColor(Color.BLACK);
        mBtnAddressDelete.setOnClickListener(this);
        mLlAddressDecLocation.setOnClickListener(this);
        mBtnAddressSave.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager_dec);
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_address_delete:

                break;
            case R.id.btn_address_save:
                break;
            case R.id.ll_address_dec_location:
                Intent intent = new Intent(context, ProvinceLinkActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;

        }
    }


}
