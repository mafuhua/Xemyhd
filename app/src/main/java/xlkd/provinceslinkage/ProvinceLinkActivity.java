package xlkd.provinceslinkage;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yuen.xemyhd.R;

import xlkd.util.Util;

public class ProvinceLinkActivity extends Util {

    private Spinner provinceSpinner;
    private Spinner citySpinner;
    private Spinner districtSpinner;
    private Context context;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Button mBtnShopQuedingAddress;


    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mBtnShopQuedingAddress = (Button) findViewById(R.id.btn_shop_queding_address);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvTitleDec.setText("所在地区");
        mTvTitleDec.setTextColor(Color.WHITE);
        mIvBtnAdd.setVisibility(View.GONE);
        mBtnShopQuedingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincelink);
        initView();
        assignViews();


    }

    private void initView() {
        provinceSpinner = (Spinner) this.findViewById(R.id.province);
        citySpinner = (Spinner) this.findViewById(R.id.city);
        districtSpinner = (Spinner) this.findViewById(R.id.district);
        initProvinceDatas();
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mProvinceDatas);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentProviceName = mProvinceDatas[position];
                String[] cities = mCitisDatasMap.get(mCurrentProviceName);
                if (cities == null) {
                    cities = new String[]{""};
                }
                ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, cities);
                citySpinner.setAdapter(cityAdapter);
                mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[0];
                String[] areas = mDistrictDatasMap.get(mCurrentCityName);
                mCurrentDistrictName = areas[0];
                if (areas == null) {
                    areas = new String[]{""};
                }
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, areas);
                districtSpinner.setAdapter(districtAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[position];
                String[] areas = mDistrictDatasMap.get(mCurrentCityName);
                mCurrentDistrictName = areas[0];
                if (areas == null) {
                    areas = new String[]{""};
                }
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, areas);
                districtSpinner.setAdapter(districtAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner sp = (Spinner) parent;
                mCurrentDistrictName = (String) sp.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
