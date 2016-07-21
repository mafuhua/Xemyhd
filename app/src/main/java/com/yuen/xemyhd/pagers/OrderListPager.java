package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.activity.CommodityDecActivity;
import com.yuen.xemyhd.activity.MainActivity;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.OrderListBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class OrderListPager extends BasePager {
    private static int typepos = 0;
    private static List<Integer> typeposList = new ArrayList<>();
    private ListView mLvOftenGet;
    private MyAdapter myAdapter;
    private List<OrderListBean.DataBean.ProBean> orderList = new ArrayList<>();
    private List<OrderListBean.DataBean> orderListBeanData= new ArrayList<>();
    private String[] stringArray;
    private Context context;
    private NewAdapter newAdapter;
    private ProgressBar progressBar;

    public OrderListPager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_order_list, null);
        Log.d("mafuhua", "全部:");
        mLvOftenGet = (ListView) view.findViewById(R.id.lv_often_get);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        myAdapter = new MyAdapter(orderList);
        newAdapter = new NewAdapter();
        mLvOftenGet.setAdapter(newAdapter);
        return view;
    }

    @Override
    public void initData() {
        stringArray = context.getResources().getStringArray(R.array.types);
        getOrderList();
    }

    private void getOrderList() {
      //  Log.d("mafuhua", "------OrderList_URL-----" + ContactURL.OrderList_URL + MainActivity.useruid+"/type/2");
        XUtils.xUtilsGet(ContactURL.OrderList_URL + MainActivity.useruid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "------OrderList_URL-----" + result);
                orderList.clear();
                orderListBeanData.clear();
                typeposList.clear();
                Gson gson = new Gson();
                OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
                orderListBeanData = orderListBean.getData();
                typepos = 0;
                typeposList.add(typepos);
                for (int i = 0; i < orderListBeanData.size(); i++) {
                    OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                    List<OrderListBean.DataBean.ProBean> proBeanList = dataBean.getPro();
                    typepos = typepos + proBeanList.size();
                    typeposList.add(typepos);
                    for (int j = 0; j < proBeanList.size(); j++) {
                        OrderListBean.DataBean.ProBean proBean = proBeanList.get(j);
                        orderList.add(proBean);
                    }

                }
                progressBar.setVisibility(View.GONE);
                newAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new WoOftenGetHolder();
        }
    }
    class  NewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return orderList==null?0:orderList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.layout_often_get_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
           viewHolder.tvoftenlistshopname.setText(orderList.get(position).getName());
            viewHolder.tvoftenlistprice.setText(orderList.get(position).getPrice());
            viewHolder.tvoftenlistcount.setText("X" + orderList.get(position).getNum());
            x.image().bind(viewHolder.ivordershopimage, orderList.get(position).getImage());
            Log.d("mafuhua", "typeposListoo:" + typeposList);
            Log.d("mafuhua", "typeposList:" + position);
            Log.d("mafuhua", "orderListBeanData:" + orderListBeanData.size());
            if (typeposList.contains(position)) {

                int i = typeposList.indexOf(position);
                final OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                String type = dataBean.getType();
                if (type.equals("1")) {
                    viewHolder.tvordertype.setText(stringArray[0]);

                } else if (type.equals("2")) {
                    viewHolder.tvordertype.setText(stringArray[1]);
                } else if (type.equals("3")){
                    viewHolder.tvordertype.setText(stringArray[2]);
                } else {
                    viewHolder.tvordertype.setText(stringArray[3]);
                }

                viewHolder.tvorderlistype.setText(dataBean.getShop_title());
                viewHolder.rltitile.setVisibility(View.VISIBLE);
            } else {
                viewHolder.rltitile.setVisibility(View.GONE);
            }
            viewHolder.ivordershopimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommodityDecActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("id", orderList.get(position).getPro_id());

                    Log.d("mafuhua", "commodityi----d" + orderList.get(position).getPro_id());
                    context.startActivity(intent);
                }
            });


            return convertView;
        }
    }
    public  class ViewHolder {
        public View rootView;
        public View tt;
        public ImageView ivoftenimgtype;
        public TextView tvorderlistype;
        public TextView tvordertype;
        public TextView tvorderdel;
        public RelativeLayout rltitile;
        public ImageView ivordershopimage;
        public TextView tvoftenlistshopname;
        public TextView tvoftenlistprice;
        public TextView tvoftenlistcount;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tt = (View) rootView.findViewById(R.id.tt);
            this.ivoftenimgtype = (ImageView) rootView.findViewById(R.id.iv_often_img_type);
            this.tvorderlistype = (TextView) rootView.findViewById(R.id.tv_order_list_type);
            this.tvordertype = (TextView) rootView.findViewById(R.id.tv_order_type);
            this.tvorderdel = (TextView) rootView.findViewById(R.id.tv_order_del);
            this.rltitile = (RelativeLayout) rootView.findViewById(R.id.rl_titile);
            this.ivordershopimage = (ImageView) rootView.findViewById(R.id.iv_order_shop_image);
            this.tvoftenlistshopname = (TextView) rootView.findViewById(R.id.tv_often_list_shopname);
            this.tvoftenlistprice = (TextView) rootView.findViewById(R.id.tv_often_list_price);
            this.tvoftenlistcount = (TextView) rootView.findViewById(R.id.tv_often_list_count);
        }

    }

    class WoOftenGetHolder extends BaseHolder<OrderListBean.DataBean.ProBean> {
        public ImageView ivoftenimgtype;
        public TextView tvorderlisttype;
        public ImageView ivordershopimage;
        public TextView tvoftenlistshopname;
        public TextView tvoftenlistprice;
        public TextView tv_order_type;
        public TextView tv_often_list_count;
        private RelativeLayout rl_titile;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_often_get_item, null);
            ivoftenimgtype = (ImageView) root.findViewById(R.id.iv_often_img_type);
            rl_titile = (RelativeLayout) root.findViewById(R.id.rl_titile);
            tvorderlisttype = (TextView) root.findViewById(R.id.tv_order_list_type);
            tv_order_type = (TextView) root.findViewById(R.id.tv_order_type);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvoftenlistshopname = (TextView) root.findViewById(R.id.tv_often_list_shopname);
            tvoftenlistprice = (TextView) root.findViewById(R.id.tv_often_list_price);
            tv_often_list_count = (TextView) root.findViewById(R.id.tv_often_list_count);
            return root;
        }

        @Override
        public void refreshView(final OrderListBean.DataBean.ProBean data, int position) {
            tvoftenlistshopname.setText(data.getName());
            tvoftenlistprice.setText(data.getPrice());
            tv_often_list_count.setText("X" + data.getNum());
            x.image().bind(ivordershopimage, data.getImage());
            Log.d("mafuhua", "typeposListoo:" + typeposList);
            Log.d("mafuhua", "typeposList:" + position);
            if (typeposList.contains(position)) {

                int i = typeposList.indexOf(position);
                final OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                String type = dataBean.getType();
                if (type.equals("1")) {
                    tv_order_type.setText(stringArray[0]);

                } else if (type.equals("2")) {
                    tv_order_type.setText(stringArray[1]);
                } else {
                    tv_order_type.setText(stringArray[2]);
                }

                tvorderlisttype.setText(dataBean.getShop_title());
                rl_titile.setVisibility(View.VISIBLE);
            } else {
                rl_titile.setVisibility(View.GONE);
            }
            ivordershopimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommodityDecActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent.putExtra("id", data.getPro_id());

                    Log.d("mafuhua", "commodityi----d" + data.getPro_id());
                    context.startActivity(intent);
                }
            });
        }
    }

}
