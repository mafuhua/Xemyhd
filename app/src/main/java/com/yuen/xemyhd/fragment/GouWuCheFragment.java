package com.yuen.xemyhd.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.layout_gouwuche_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.btngouwucheitemjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "我是" + position + "条" + "+++", Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.btngouwucheitemjian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "我是" + position + "条" + "---", Toast.LENGTH_SHORT).show();
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
            public final RelativeLayout rlgowucheitemtitle;
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
                rlgowucheitemtitle = (RelativeLayout) root.findViewById(R.id.rl_gowuche_item_title);
            }
        }
    }


}
