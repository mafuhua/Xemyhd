package com.yuen.xemyhd.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class GouWuCheFragment extends BaseFragment implements View.OnClickListener {
    private List settingString = new ArrayList(Arrays.asList("意见反馈", "检查更新", "清除缓存", "帮助中心", "关于我们", "退出"));
    private ListView mLvGouwucheList;
    private CheckBox mCbGouwuche;
    private Button mBtnGouWuCheJieSuan;
    private TextView mTvGouWuCheZongJia;
    private Context context;
    private MyAdapter myAdapter;

    private void assignViews(View view) {
        context = getActivity();
        mLvGouwucheList = (ListView) view.findViewById(R.id.lv_gouwuche_list);
        mCbGouwuche = (CheckBox) view.findViewById(R.id.cb_gouwuche_all);
        mTvGouWuCheZongJia = (TextView) view.findViewById(R.id.tv_gouwuche_zongjia);
        mBtnGouWuCheJieSuan = (Button) view.findViewById(R.id.btn_gouwuche_jiesuan);
        myAdapter = new MyAdapter(settingString);
        mLvGouwucheList.setAdapter(myAdapter);
    }


    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.layout_gouwuchefragment, null);
        assignViews(view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_gouwuche_item_jia:
                break;
            case R.id.btn_gouwuche_item_jian:
                break;
            case R.id.btn_gouwuche_jiesuan:
                break;
            case R.id.cb_gouwuche_all:
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

    class ViewHolder extends BaseHolder<String> {
        public CheckBox cbgouwucheitemall;
        public ImageView ivgouwucheimgtype;
        public TextView tvgouwuchelisttype;
        public CheckBox cbgouwucheitem;
        public ImageView ivordershopimage;
        public TextView tvoftenlistshopname;
        public TextView tvoftenlistprice;
        public Button btngouwucheitemjian;
        public Button btngouwucheitemjia;
        public TextView tvgouwucheitemshuliang;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_gouwuche_item, null);
            cbgouwucheitemall = (CheckBox) root.findViewById(R.id.cb_gouwuche_item_all);
            ivgouwucheimgtype = (ImageView) root.findViewById(R.id.iv_gouwuche_img_type);
            tvgouwuchelisttype = (TextView) root.findViewById(R.id.tv_gouwuche_list_type);
            cbgouwucheitem = (CheckBox) root.findViewById(R.id.cb_gouwuche_item);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvoftenlistshopname = (TextView) root.findViewById(R.id.tv_often_list_shopname);
            tvoftenlistprice = (TextView) root.findViewById(R.id.tv_often_list_price);
            btngouwucheitemjian = (Button) root.findViewById(R.id.btn_gouwuche_item_jian);
            btngouwucheitemjia = (Button) root.findViewById(R.id.btn_gouwuche_item_jia);
            tvgouwucheitemshuliang = (TextView) root.findViewById(R.id.tv_gouwuche_item_shuliang);
            return root;
        }

        @Override
        public void refreshView(String data, int position) {
            tvoftenlistshopname.setText(data);
        }

    }
}
