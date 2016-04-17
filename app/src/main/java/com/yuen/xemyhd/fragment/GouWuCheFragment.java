package com.yuen.xemyhd.fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.TestGouwuche;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class GouWuCheFragment extends BaseFragment implements View.OnClickListener {

    private static ListView mLvGouwucheList;
    private static MyAdapter myAdapter;
    private static List<TestGouwuche.DataBean> dataBeanList;
    private static List<TestGouwuche.DataBean.ProBean> proBeanList;
    private static List<String> typenameList = new ArrayList<>();
    private static List<String> shopnameList = new ArrayList<>();
    private static List<String> shoppriceList = new ArrayList<>();
    private static List<Integer> shopnumList = new ArrayList<>();
    private static List<Integer> shopinventList = new ArrayList<>();
    private static List<Boolean> shopcheckchildList = new ArrayList<>();
    private static List<Boolean> shopcheckparentList = new ArrayList<>();
    private static Context context;
    private static int typepos = 0;
    private static List<Integer> typeposList = new ArrayList<>();
    private List settingString = new ArrayList(Arrays.asList("意见反馈", "检查更新", "清除缓存", "帮助中心", "关于我们", "退出"));
    private CheckBox mCbGouwuche;
    private Button mBtnGouWuCheJieSuan;
    private TextView mTvGouWuCheZongJia;

    public static void getdata() {
        if (dataBeanList != null) {
            dataBeanList.clear();
            shopnameList.clear();
            shopcheckparentList.clear();
            shopcheckchildList.clear();
            shopinventList.clear();
            shopnumList.clear();
            typeposList.clear();
            typenameList.clear();
            shoppriceList.clear();
        }
        final RequestParams params = new RequestParams("http://192.168.0.121:8080/buycar.json");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "shopapi/test========" + result);
                Gson gson = new Gson();
                TestGouwuche testGouwuche = gson.fromJson(result, TestGouwuche.class);
                dataBeanList = testGouwuche.data;
                typepos = 0;
                typeposList.add(typepos);
                for (int i = 0; i < dataBeanList.size(); i++) {
                    String typename = dataBeanList.get(i).name;
                    shopcheckparentList.add(false);
                    GouWuCheFragment.proBeanList = dataBeanList.get(i).pro;
                    typepos = typepos + proBeanList.size();
                    typeposList.add(typepos);
                    for (int i1 = 0; i1 < GouWuCheFragment.proBeanList.size(); i1++) {
                        shopnameList.add(GouWuCheFragment.proBeanList.get(i1).pro_name);
                        shopnumList.add(GouWuCheFragment.proBeanList.get(i1).num);
                        shopinventList.add(Integer.parseInt(GouWuCheFragment.proBeanList.get(i1).pro_inventory));
                        shoppriceList.add(GouWuCheFragment.proBeanList.get(i1).price);
                        shopcheckchildList.add(false);
                    }
                    typenameList.add(typename);
                }
                Log.d("mafuhua", "typeposList:" + typeposList);
                myAdapter = new MyAdapter(shopnameList);
                mLvGouwucheList.setAdapter(myAdapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", "shopapi/test========" + isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("mafuhua", "shopapi/test========" + cex);
            }

            @Override
            public void onFinished() {
                Log.d("mafuhua", "shopapi/test========" + "onFinished");
            }
        });
    }

    private void assignViews(View view) {
        context = getActivity();
        mLvGouwucheList = (ListView) view.findViewById(R.id.lv_gouwuche_list);
        mCbGouwuche = (CheckBox) view.findViewById(R.id.cb_gouwuche_all);
        mTvGouWuCheZongJia = (TextView) view.findViewById(R.id.tv_gouwuche_zongjia);
        mBtnGouWuCheJieSuan = (Button) view.findViewById(R.id.btn_gouwuche_jiesuan);
        mBtnGouWuCheJieSuan.setTextColor(Color.WHITE);
        mCbGouwuche.setOnClickListener(this);
        mBtnGouWuCheJieSuan.setOnClickListener(this);
        mLvGouwucheList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "我是" + position, Toast.LENGTH_SHORT).show();
            }
        });
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

            case R.id.btn_gouwuche_jiesuan:
                break;
            case R.id.cb_gouwuche_all:
                if (!mCbGouwuche.isChecked()) {
                    shopcheckchildList.clear();
                    shopcheckparentList.clear();
                    for (int i = 0; i < shopnameList.size(); i++) {
                        shopcheckchildList.add(false);
                    }
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        shopcheckparentList.add(false);
                    }
                    myAdapter.notifyDataSetChanged();
                } else {
                    shopcheckchildList.clear();
                    shopcheckparentList.clear();
                    for (int i = 0; i < shopnameList.size(); i++) {
                        shopcheckchildList.add(true);
                    }
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        shopcheckparentList.add(true);
                    }
                    myAdapter.notifyDataSetChanged();
                }

                break;

        }
    }

    static class MyAdapter extends DefaultAdapter {

        private int numValue;

        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.layout_gouwuche_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //  viewHolder.tvgouwuchelisttype.setText(dataBeanList.get(position).name);
            Log.d("mafuhua", "typeposList:" + typeposList);
            if (typeposList.contains(position)) {
                int indexOf = typeposList.indexOf(position);
                Log.d("mafuhua", "shopcheckparentList.get(indexOf):" + shopcheckparentList.get(indexOf));
                viewHolder.cbgouwucheitemall.setChecked(shopcheckparentList.get(indexOf));
                viewHolder.tvgouwuchelisttype.setText(dataBeanList.get(indexOf).name);
                viewHolder.rlgowucheitemtitle.setVisibility(View.VISIBLE);
            } else {
                viewHolder.rlgowucheitemtitle.setVisibility(View.GONE);
            }
            final Integer shulaing = shopnumList.get(position);
            numValue = shulaing.intValue();
            viewHolder.tvgouwucheitemshuliang.setText(numValue + "");
            viewHolder.tvoftenlistprice.setText(shoppriceList.get(position));
            viewHolder.tvoftenlistshopname.setText(shopnameList.get(position));
            viewHolder.cbgouwucheitem.setChecked(shopcheckchildList.get(position));

            viewHolder.btngouwucheitemjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numValue = numValue + 1;
                    int maxInvent = shopinventList.get(position);
                    if (numValue > maxInvent) {
                        numValue = maxInvent;
                        viewHolder.tvgouwucheitemshuliang.setText(numValue + "");
                        Toast.makeText(context, "我是" + position + "条" + "---", Toast.LENGTH_SHORT).show();
                    } else {
                        viewHolder.tvgouwucheitemshuliang.setText(numValue + "");
                        Toast.makeText(context, "我是" + position + "条" + "---", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            viewHolder.btngouwucheitemjian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // viewHolder.tvgouwucheitemshuliang.setText();
                    numValue = numValue - 1;
                    if (numValue < 1) {
                        numValue = 1;
                        viewHolder.tvgouwucheitemshuliang.setText(1 + "");
                        Toast.makeText(context, "我是" + position + "条" + "---", Toast.LENGTH_SHORT).show();
                    } else {
                        viewHolder.tvgouwucheitemshuliang.setText(numValue + "");
                        Toast.makeText(context, "我是" + position + "条" + "---", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            viewHolder.cbgouwucheitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = viewHolder.cbgouwucheitem.isChecked();
                    Toast.makeText(context, "我是" + position + "条" + "cb" + checked, Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.cbgouwucheitemall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = viewHolder.cbgouwucheitem.isChecked();
                    Toast.makeText(context, "我是" + position + "条" + "cb" + checked, Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        @Override
        public BaseHolder getHolder() {
            return null;
        }


        public class ViewHolder {
            public final CheckBox cbgouwucheitemall;
            public final ImageView ivgouwucheimgtype;
            public final TextView tvgouwuchelisttype;
            public final CheckBox cbgouwucheitem;
            public final ImageView ivordershopimage;
            public final TextView tvoftenlistshopname;
            public final TextView tvoftenlistprice;
            public final Button btngouwucheitemjian;
            public final Button btngouwucheitemjia;
            public final TextView tvgouwucheitemshuliang;
            public final LinearLayout rlgowucheitemtitle;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
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
                rlgowucheitemtitle = (LinearLayout) root.findViewById(R.id.rl_gowuche_item_title);
            }
        }
    }

}
