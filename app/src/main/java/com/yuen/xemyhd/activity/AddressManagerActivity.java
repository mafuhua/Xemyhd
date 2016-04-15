package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressManagerActivity extends AppCompatActivity {
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private ListView mLvAddressManager;
    private MyAdapter myAdapter;
    private Context context;
    private List<String> wodename = new ArrayList<String>(
            Arrays.asList("小明", "小红","小明"));
    private Button mBtnAddressAdd;

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mBtnAddressAdd = (Button) findViewById(R.id.btn_address_add);
        mBtnAddressAdd.setTextColor(Color.WHITE);
        mTvTitleDec.setText("收货地址管理");
        mTvTitleDec.setTextColor(Color.WHITE);
        mLvAddressManager = (ListView) findViewById(R.id.lv_address_manager);
        myAdapter = new MyAdapter(wodename);
        mLvAddressManager.setAdapter(myAdapter);
        mBtnAddressAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wodename.add("呵呵");
                myAdapter.notifyDataSetChanged();
            }
        });
        mLvAddressManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,AddressManagerDecActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        assignViews();

    }


    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new ViewHolder();
        }
    }

    public class ViewHolder extends BaseHolder<String> {
        TextView tvaddresslistusername;
        TextView tvaddresslistphone;
        TextView tvaddresslisttype;
        TextView tvaddresslistaddress;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_address_manager_item, null);
            tvaddresslistusername = (TextView) root.findViewById(R.id.tv_address_list_username);
            tvaddresslistphone = (TextView) root.findViewById(R.id.tv_address_list_phone);
            tvaddresslisttype = (TextView) root.findViewById(R.id.tv_address_list_type);
            tvaddresslistaddress = (TextView) root.findViewById(R.id.tv_address_list_address);
            return root;
        }

        @Override
        public void refreshView(String data, int position) {
            tvaddresslistusername.setText(data);
        }
    }
}
