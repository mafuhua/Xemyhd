package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.AddressBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.GsonUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.List;

public class AddressManagerActivity extends AppCompatActivity {
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private ListView mLvAddressManager;
    private MyAdapter myAdapter;
    private Context context;
    private Button mBtnAddressAdd;
    public static List<AddressBean.DataBean> addressBeanData;
    private int addressBeanNum;

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

        mBtnAddressAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressBeanNum>=5) {
                    Toast.makeText(context, "请编辑收货地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(context, AddressManagerDecActivity.class);
                intent.putExtra("id","");
                intent.putExtra("sheng","");
                intent.putExtra("shi","");
                intent.putExtra("qu","");
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                // myAdapter.notifyDataSetChanged();
            }
        });
        mLvAddressManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, AddressManagerDecActivity.class);
                intent.putExtra("id",AddressManagerActivity.addressBeanData.get(position).getId());
                intent.putExtra("sheng",AddressManagerActivity.addressBeanData.get(position).getSheng());
                intent.putExtra("shi",AddressManagerActivity.addressBeanData.get(position).getShi());
                intent.putExtra("qu",AddressManagerActivity.addressBeanData.get(position).getQu());
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
    @Override
    protected void onResume() {
        super.onResume();
        getAdds();
    }
    public void getAdds() {
        XUtils.xUtilsGet(ContactURL.GetAdds_URL + MainActivity.useruid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "-----GetAdds_URL----" + result);
                AddressBean addressBean = GsonUtil.fromJson(result, AddressBean.class);
                addressBeanNum = addressBean.getNum();
                addressBeanData = addressBean.getData();
                myAdapter = new MyAdapter(addressBeanData);
                mLvAddressManager.setAdapter(myAdapter);
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

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new AddressManagerHolder();
        }
    }

    class AddressManagerHolder extends BaseHolder<AddressBean.DataBean> {
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
            tvaddresslisttype.setTextColor(Color.RED);
            return root;
        }

        @Override
        public void refreshView(AddressBean.DataBean data, int position) {
            tvaddresslistusername.setText(data.getName());
            tvaddresslistphone.setText(data.getTel());
            String moren = data.getMoren();
            if (moren.equals("1")) {
                tvaddresslisttype.setVisibility(View.VISIBLE);
            } else {
                tvaddresslisttype.setVisibility(View.GONE);
            }
            tvaddresslistaddress.setText("收货地址:"+data.getSheng() + data.getShi() + data.getQu() + data.getAdds());
        }
    }

}
