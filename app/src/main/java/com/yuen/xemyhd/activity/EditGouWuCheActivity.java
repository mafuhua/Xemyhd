package com.yuen.xemyhd.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.SysExitUtil;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class EditGouWuCheActivity extends AppCompatActivity implements View.OnClickListener {
    public static MyAdapter myAdapter;
    public static boolean checkalltype;
    public static TextView mTvGouWuCheZongJia;
    public static CheckBox mCbGouwuche;
    public static double totalprice;
    public static Button mBtnGouWuCheJieSuan;
    private static ListView mLvGouwucheList;
    private static List<TestGouwuche.DataBean> dataBeanList;
    private static List<TestGouwuche.DataBean.ProBean> proBeanList;
    private static List<TestGouwuche.DataBean.ProBean> myproBeanList = new ArrayList<>();
    private static List<String> typenameList = new ArrayList<>();
    private static List<String> shopnameList = new ArrayList<>();
    private static Context context;
    private static int typepos = 0;
    private static List<Integer> typeposList = new ArrayList<>();
    private TextView mTvTitileDec;

    public static void getdata() {
        final RequestParams params = new RequestParams(ContactURL.GouWuChe_URL + MainActivity.useruid);
        //  final RequestParams params = new RequestParams("http://192.168.2.128:8080/buycar.json");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (dataBeanList != null) {
                    dataBeanList.clear();
                    shopnameList.clear();
                    typeposList.clear();
                    typenameList.clear();
                    myproBeanList.clear();
                }
                Log.d("mafuhua", "shopapi/ShopListEmptyBean========" + result.toString());
                Gson gson = new Gson();
                TestGouwuche testGouwuche = gson.fromJson(result, TestGouwuche.class);
                if (testGouwuche.getCode().equals("2")) {
                    myAdapter = new MyAdapter(null);
                    mLvGouwucheList.setAdapter(myAdapter);
                } else {
                    dataBeanList = testGouwuche.getData();
                    typepos = 0;
                    typeposList.add(typepos);
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        String typename = dataBeanList.get(i).getShop_title();
                        EditGouWuCheActivity.proBeanList = dataBeanList.get(i).getPro();
                        typepos = typepos + proBeanList.size();
                        typeposList.add(typepos);
                        for (int j = 0; j < EditGouWuCheActivity.proBeanList.size(); j++) {
                            myproBeanList.add(proBeanList.get(j));
                            shopnameList.add(EditGouWuCheActivity.proBeanList.get(j).getPro_name());
                            //checkitemmap.put(GouWuCheFragment2.proBeanList.get(j).getId(), false);
                        }
                        typenameList.add(typename);
                    }
                    Log.d("mafuhua", "typeposList:" + typeposList);
                    myAdapter = new MyAdapter(shopnameList);
                    mLvGouwucheList.setAdapter(myAdapter);
                }

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gouwuche);
        SysExitUtil.activityList.add(this);

        assignViews();
        getdata();
    }

    private void assignViews() {
        context = this;
        mLvGouwucheList = (ListView) findViewById(R.id.lv_gouwuche_list);
        mCbGouwuche = (CheckBox) findViewById(R.id.cb_gouwuche_all);
        mTvGouWuCheZongJia = (TextView) findViewById(R.id.tv_gouwuche_zongjia);
        mTvTitileDec = (TextView) findViewById(R.id.tv_title_dec);
        mBtnGouWuCheJieSuan = (Button) findViewById(R.id.btn_gouwuche_jiesuan);
        mBtnGouWuCheJieSuan.setTextColor(Color.WHITE);
        mTvTitileDec.setTextColor(Color.WHITE);
        mCbGouwuche.setOnClickListener(this);
        mBtnGouWuCheJieSuan.setOnClickListener(this);
    }

    public static void jiaOrjian(String url, String shopid) {
        HashMap<String, String> map = new HashMap<>();
        Log.d("GouWuCheFragment2", url + shopid);
        map.put("user_id", MainActivity.useruid);
        map.put("pro_id", shopid);
        XUtils.xUtilsPost(url, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("GouWuCheFragment2", result);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_gouwuche_jiesuan:
                StringBuffer sb = new StringBuffer();
                if (dataBeanList != null) {
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        List<TestGouwuche.DataBean.ProBean> proBeanList = dataBeanList.get(i).getPro();
                        for (int j = 0; j < proBeanList.size(); j++) {
                            if (proBeanList.get(j).isCheckitem()) {
                                String shop_id = proBeanList.get(j).getId();
                                String pro_name = proBeanList.get(j).getPro_name();
                                Log.d("mafuhuax", "您选删除了" + shop_id + "----" + pro_name);
                                sb.append(shop_id + ",");
                                proBeanList.remove(j);
                                j--;
                                //  XUtils.xUtilsPost(ContactURL.DelGouWuChe_URL,);
                            }
                        }
                    }
                    String s = sb.toString();
                    if (!s.isEmpty()) {
                        delShop(s.substring(0, s.length() - 1));
                    }
                }


                break;
            case R.id.cb_gouwuche_all:
               /* checkalltype = !checkalltype;
                mCbGouwuche.setChecked(checkalltype);
                totalprice = 0;
                Log.d("mafuhua", "checkalltype========:" + checkalltype);
                if (checkalltype) {
                    if (dataBeanList != null) {
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            dataBeanList.get(i).setCheckTitle(checkalltype);
                            Log.d("mafuhua", "全选" + dataBeanList.get(i).getShop_title() + checkalltype);
                            for (int j = 0; j < dataBeanList.get(i).getPro().size(); j++) {
                                dataBeanList.get(i).getPro().get(j).setCheckitem(checkalltype);
                            }
                        }
                    } else {
                        if (dataBeanList != null) {
                            for (int i = 0; i < dataBeanList.size(); i++) {

                                dataBeanList.get(i).setCheckTitle(checkalltype);
                            }
                            for (int i = 0; i < myproBeanList.size(); i++) {
                                myproBeanList.get(i).setCheckitem(checkalltype);
                                Log.d("mafuhua", "未全选" + myproBeanList.get(i).getPro_name() + myproBeanList.get(i).isCheckitem());
                            }
                        }

                    }
                    if (myAdapter != null) {
                        myAdapter.notifyDataSetChanged();
                    }
                }*/

                checkalltype = !checkalltype;
                mCbGouwuche.setChecked(checkalltype);
                totalprice = 0;
                Log.d("mafuhua", "checkalltype========:" + checkalltype);
                if (checkalltype) {
                    if (dataBeanList == null) {
                        MyUtils.toastShow(context, "购物车没有商品", Toast.LENGTH_SHORT);
                    } else {
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            dataBeanList.get(i).setCheckTitle(checkalltype);
                            Log.d("mafuhua", "全选" + dataBeanList.get(i).getShop_title() + checkalltype);
                            for (int j = 0; j < dataBeanList.get(i).getPro().size(); j++) {
                                dataBeanList.get(i).getPro().get(j).setCheckitem(checkalltype);
                            }
                        }

                    }

                } else {
                    if (dataBeanList == null) {
                        MyUtils.toastShow(context, "购物车没有商品", Toast.LENGTH_SHORT);
                    } else {
                        for (int i = 0; i < dataBeanList.size(); i++) {

                            dataBeanList.get(i).setCheckTitle(checkalltype);
                        }
                        for (int i = 0; i < myproBeanList.size(); i++) {
                            myproBeanList.get(i).setCheckitem(checkalltype);
                            Log.d("mafuhua", "未全选" + myproBeanList.get(i).getPro_name() + myproBeanList.get(i).isCheckitem());
                        }
                    }
                }
                if (myAdapter != null) {
                    myAdapter.notifyDataSetChanged();
                }
                break;

        }
    }

    private void delShop(String shop_id) {
        Log.d("mafuhua", "----DelGouWuChe_URL-----" + ContactURL.DelGouWuChe_URL + shop_id);
        XUtils.xUtilsGet(ContactURL.DelGouWuChe_URL + shop_id, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.d("mafuhua", "----DelGouWuChe_URL-----" + result);
                getdata();
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

    public static class MyAdapter extends DefaultAdapter {
        private int indexOf = 0;

        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final EditGouWuCheHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.layout_gouwuche_item, null);
                viewHolder = new EditGouWuCheHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (EditGouWuCheHolder) convertView.getTag();
            }
            if (typeposList.contains(position)) {
                indexOf = typeposList.indexOf(position);
                boolean checkTitle = dataBeanList.get(indexOf).isCheckTitle();
                List<TestGouwuche.DataBean.ProBean> proBeans = dataBeanList.get(indexOf).getPro();
                int checkitemcount = 0;
                Log.d("mafuhua", "indexOf:" + indexOf + !checkTitle);
                for (int i = 0; i < proBeans.size(); i++) {
                    if (proBeans.get(i).isCheckitem()) {
                        checkitemcount++;
                    }
                }

                if (checkitemcount == proBeans.size()) {
                    Log.d("mafuhua", "checkitemcount++++----------" + checkitemcount);
                    Log.d("mafuhua", "proBeans.size()+++----------" + proBeans.size());
                    dataBeanList.get(indexOf).setCheckTitle(true);
                    viewHolder.cbgouwucheitemall.setChecked(true);
                } else {
                    Log.d("mafuhua", "checkitemcount----------" + checkitemcount);
                    Log.d("mafuhua", "proBeans.size()----------" + proBeans.size());
                    dataBeanList.get(indexOf).setCheckTitle(false);
                    viewHolder.cbgouwucheitemall.setChecked(false);
                }
                if (checkalltype) {
                    viewHolder.cbgouwucheitemall.setChecked(dataBeanList.get(indexOf).isCheckTitle());
                    Log.d("mafuhua", "全选-----------" + dataBeanList.get(indexOf).getShop_title());
                    for (int i = 0; i < proBeans.size(); i++) {
                        proBeans.get(i).setCheckitem(checkalltype);
                        Log.d("mafuhua", "proBeans.get(i).getPro_name()" + proBeans.get(i).getPro_name() + checkalltype);
                    }
                } else {
                    Log.d("mafuhua", "不全选--------------" + dataBeanList.get(indexOf).getShop_title() + "----" + dataBeanList.get(indexOf).isCheckTitle());
                    viewHolder.cbgouwucheitemall.setChecked(dataBeanList.get(indexOf).isCheckTitle());
                }

                //  Log.d("mafuhua", "dataBeanList.get(indexOf).isCheckTitle():" + position + "***点击**" + checkTitle);

                viewHolder.tvgouwuchelisttype.setText(dataBeanList.get(indexOf).getShop_title());
                viewHolder.rlgowucheitemtitle.setVisibility(View.VISIBLE);

            } else {
                viewHolder.rlgowucheitemtitle.setVisibility(View.GONE);
            }
            viewHolder.tvoftenlistshopname.setText(shopnameList.get(position));
            x.image().bind(viewHolder.ivordershopimage, myproBeanList.get(position).getPro_img());
            viewHolder.tvgouwucheitemshuliang.setText(myproBeanList.get(position).getPro_num());
            viewHolder.tvoftenlistprice.setText(myproBeanList.get(position).getPro_price());
            viewHolder.cbgouwucheitem.setChecked(myproBeanList.get(position).isCheckitem());
            Log.d("mafuhua", "myproBeanList.get(position).isCheckitem()================:" + position + "========" + myproBeanList.get(position).isCheckitem());
            viewHolder.btngouwucheitemjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < myproBeanList.size(); i++) {
                        myproBeanList.get(i).setPro_num(myproBeanList.get(i).getPro_num());
                    }
                    int num = Integer.parseInt(myproBeanList.get(position).getPro_num());
                    if (Integer.parseInt(myproBeanList.get(position).getPro_inventory()) > num) {
                        num += 1;
                        myproBeanList.get(position).setPro_num(num + "");
                        viewHolder.tvgouwucheitemshuliang.setText(num + "");
                        jiaOrjian(ContactURL.XIUGAI_JIA_NUM_URL, myproBeanList.get(position).getPro_id() + "");
                    } else {
                        viewHolder.tvgouwucheitemshuliang.setText(num + "");
                    }
                }
            });
            viewHolder.btngouwucheitemjian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < myproBeanList.size(); i++) {
                        myproBeanList.get(i).setPro_num(myproBeanList.get(i).getPro_num());
                    }
                    int num = Integer.parseInt(myproBeanList.get(position).getPro_num());
                    if (num >= 2) {
                        num -= 1;
                        myproBeanList.get(position).setPro_num(num + "");
                        viewHolder.tvgouwucheitemshuliang.setText(num + "");
                        jiaOrjian(ContactURL.XIUGAI_JIAN_NUM_URL, myproBeanList.get(position).getPro_id() + "");
                    } else {
                        viewHolder.tvgouwucheitemshuliang.setText(num + "");
                    }
                }
            });
            viewHolder.cbgouwucheitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("mafuhua", "cbgouwucheitem+++++indexOf:" + indexOf);

                    boolean checkitem = myproBeanList.get(position).isCheckitem();
                    myproBeanList.get(position).setCheckitem(!checkitem);
                    boolean checkall = !checkitem;
                    if (checkall) {
                        int checkitemcount = 0;
                        for (int i = 0; i < myproBeanList.size(); i++) {
                            if (myproBeanList.get(i).isCheckitem()) {
                                checkitemcount++;
                            }
                        }
                        if (checkitemcount == myproBeanList.size()) {
                            mCbGouwuche.setChecked(checkall);
                        }

                    } else {
                        mCbGouwuche.setChecked(checkall);
                        checkalltype = checkall;
                    }

                    //dataBeanList.get(indexOf)
                    myAdapter.notifyDataSetChanged();
                }
            });
            viewHolder.cbgouwucheitemall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexOf = typeposList.indexOf(position);
                    boolean checkTitle = dataBeanList.get(indexOf).isCheckTitle();
                    dataBeanList.get(indexOf).setCheckTitle(!checkTitle);

                    boolean checkall = !checkTitle;
                    Log.d("mafuhua", "checkall============:" + checkall);
                    if (checkall) {
                        int checkallcount = 0;
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            if (dataBeanList.get(i).isCheckTitle()) {
                                checkallcount++;
                            }
                        }
                        if (checkallcount == dataBeanList.size()) {
                            mCbGouwuche.setChecked(checkall);
                        }
                    } else {
                        mCbGouwuche.setChecked(checkall);
                        checkalltype = checkall;
                    }


                    List<TestGouwuche.DataBean.ProBean> proBeans = dataBeanList.get(indexOf).getPro();
                    //  Log.d("mafuhua", "indexOf:" + indexOf + !checkTitle);
                    for (int i = 0; i < proBeans.size(); i++) {
                        proBeans.get(i).setCheckitem(!checkTitle);
                        Log.d("mafuhua", "--------" + proBeans.get(i).getPro_name() + !checkTitle);
                    }
                    // Log.d("mafuhua", "dataBeanList.get(indexOf).isCheckTitle():" + position + "***点击**" + !checkTitle);
                    myAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        @Override
        public BaseHolder getHolder() {
            return null;
        }


    }

    static class EditGouWuCheHolder {
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


        public EditGouWuCheHolder(View root) {
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
