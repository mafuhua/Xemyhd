package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.SearchShopBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.List;

public class SearchCommodityActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtInputSearch;
    private ImageView mIvBtnSearch;
    private Button mBtnSearch;
    private GridView mGvSearch;
    private Context context;
    private List<SearchShopBean.DataBean> searchShopBeanData;

    private void assignViews() {
        context = this;
        mEtInputSearch = (EditText) findViewById(R.id.et_input_search);
        mIvBtnSearch = (ImageView) findViewById(R.id.iv_btn_search);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mGvSearch = (GridView) findViewById(R.id.gv_search);
        mBtnSearch.setOnClickListener(this);
        mGvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchShopBean.DataBean dataBean = searchShopBeanData.get(position);
                Intent intent = new Intent(SearchCommodityActivity.this,CommodityDecActivity.class);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
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
        switch (v.getId()) {
            case R.id.btn_search:
                String string = MyUtils.getInputString(context, mEtInputSearch, "内容不能为空");
                if (string.isEmpty()) {
                    break;
                }
                XUtils.xUtilsGet(ContactURL.SearchShop_URL + HomeMarketActivity.shop_user_id + "/pro_name/" + string, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("mafuhua", "------SearchShop_URL------" + result);
                        Gson gson = new Gson();
                        SearchShopBean searchShopBean =  gson.fromJson(result, SearchShopBean.class);
                        searchShopBeanData = searchShopBean.getData();
                        mGvSearch.setAdapter(new MyAdapter(searchShopBeanData));
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
                break;
        }
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


    class ViewHolder extends BaseHolder<SearchShopBean.DataBean> {
        ImageView ivcommodityicon;
        TextView tvcommoditydec;
        TextView tvcommodityprice;
        TextView tvcommodityinventory;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_commodity_grid_item, null);
            ivcommodityicon = (ImageView) root.findViewById(R.id.iv_commodity_icon);
            tvcommoditydec = (TextView) root.findViewById(R.id.tv_commodity_dec);
            tvcommodityprice = (TextView) root.findViewById(R.id.tv_commodity_price);
            tvcommodityinventory = (TextView) root.findViewById(R.id.tv_commodity_inventory);
            return root;
        }

        @Override
        public void refreshView(SearchShopBean.DataBean data, int position) {
            tvcommodityprice.setText("￥:" + data.getPro_price());
            tvcommoditydec.setText(data.getPro_name());
            x.image().bind(ivcommodityicon, data.getPro_img(), MyUtils.options);

        }
    }
}
