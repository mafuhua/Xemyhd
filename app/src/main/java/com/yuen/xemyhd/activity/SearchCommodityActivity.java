package com.yuen.xemyhd.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.SysExitUtil;

public class SearchCommodityActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEtInputSearch;
    private ImageView mIvBtnSearch;
    private Button mBtnSearch;
    private ListView mLvSearch;
    private Context context;
    private void assignViews() {
        context = this;
        mEtInputSearch = (EditText) findViewById(R.id.et_input_search);
        mIvBtnSearch = (ImageView) findViewById(R.id.iv_btn_search);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mLvSearch = (ListView) findViewById(R.id.lv_search);
        mBtnSearch.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_commodity);
        SysExitUtil.activityList.add(this);

        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                Log.d("mafuhua", MyUtils.getInputString(context, mEtInputSearch, "内容不能为空"));

                break;
        }
    }
}
