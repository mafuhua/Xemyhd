package com.yuen.xemyhd.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.bean.ShopListBean;
import com.yuen.xemyhd.fragment.MyFragment;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomeMarketActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    public static int mPosition;
    public static int mRCPosition;
    private static List<ShopListBean.T2DataBean> shopListBeanT2_data = new ArrayList<>();
    private List titleString = new ArrayList();
    private ListView listView;
    private MyAdapter myListAdapter;
    private MyFragment myFragment;
    private RecyclerView mRcHomeHorizontal;
    private MyRCAdapter myRCAdapter;
    private MyGridAdapter myGridAdapter;
    private GridView gv_commoditylist;
    private List<ShopListBean.DataBean> shopListBeanData = new ArrayList<>();
    private Context context;
    private ImageOptions options;
    private List<ShopListBean.TDataBean> t_data = new ArrayList<>();
    private List<ShopListBean.TDataBean> t_Contentdata = new ArrayList<>();
    private TextView tv_market_shoptime;
    private String shop_user_id;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_market);
        Intent intent = getIntent();
        shop_user_id = intent.getStringExtra("id");
        initView();
        getShopList();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 初始化view
     */
    private void initView() {
        context = this;
        // TODO Auto-generated method stub
        listView = (ListView) findViewById(R.id.listview);
        tv_market_shoptime = (TextView) findViewById(R.id.tv_market_shoptime);
        gv_commoditylist = (GridView) findViewById(R.id.gv_commoditylist);
        mRcHomeHorizontal = (RecyclerView) findViewById(R.id.rc_home_horizontal);
        myGridAdapter = new MyGridAdapter();
        gv_commoditylist.setAdapter(myGridAdapter);
        myListAdapter = new MyAdapter();
        listView.setAdapter(myListAdapter);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcHomeHorizontal.setLayoutManager(linearLayoutManager);
        //设置适配器
        myRCAdapter = new MyRCAdapter(context);
        myRCAdapter.setOnItemClickLitener(new MyRCAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==0){
                    MyUtils.toastShow(getApplicationContext(), "全部", Toast.LENGTH_SHORT);
                }else {
                   // MyUtils.toastShow(getApplicationContext(), shopListBeanT2_data.get(position - 1).getNav_name(), Toast.LENGTH_SHORT);
                }
                if (position == 0) {
                   // getShopListTitle(position);
                } else {
                    getShopListContent(position);
                }

            }
        });
        mRcHomeHorizontal.setAdapter(myRCAdapter);
        listView.setOnItemClickListener(this);
        options = new ImageOptions.Builder()
                //设置使用缓存
                .setUseMemCache(true)
                        // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        //拿到当前位置
        mPosition = position;
        //即使刷新adapter
        myListAdapter.notifyDataSetChanged();
        if (position == 0) {
            getShopList();
        } else {
            getShopListTitle(position);
        }

    }

    public void getShopList() {
        XUtils.xUtilsGet(ContactURL.GetShopList_URL + shop_user_id, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ShopListBean shopListBean = gson.fromJson(result, ShopListBean.class);
                t_data = shopListBean.getT_data();
                shopListBeanData = shopListBean.getData();
                ShopListBean.DataUserBean shopListBeanData_user = shopListBean.getData_user();
                tv_market_shoptime.setText("营业时间" + shopListBeanData_user.getShop_time() + ":00 - " + shopListBeanData_user.getShop_etime() + ":00");
                myGridAdapter.notifyDataSetChanged();
                myListAdapter.notifyDataSetChanged();
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

    public void getShopListTitle(final int position) {

        XUtils.xUtilsGet(ContactURL.GetShopListTitle_URL + shop_user_id + "/type_id/" + t_data.get(position - 1).getId(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                for (int i = 0; i < t_data.size(); i++) {
                    Log.d("mafuhua", "result-----------" + t_data.get(i).getId());
                }
                Log.d("mafuhua", "result-----------" + result);
                Log.d("mafuhua", "result-----------" + ContactURL.GetShopListTitle_URL + shop_user_id + "/type_id/" + t_data.get(position - 1).getId());
                Gson gson = new Gson();
                ShopListBean shopListBean = gson.fromJson(result, ShopListBean.class);
                if (shopListBean.getData() == null) {
                    shopListBeanData.clear();
                } else {
                    shopListBeanData = shopListBean.getData();
                }
                myGridAdapter.notifyDataSetChanged();
                shopListBeanT2_data = shopListBean.getT2_data();
                //  myGridAdapter.notifyDataSetChanged();
                //    myListAdapter.notifyDataSetChanged();
                myRCAdapter.notifyDataSetChanged();
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
    public void getShopListContent(final int position) {
        XUtils.xUtilsGet(ContactURL.GetShopListContent_URL + shop_user_id + "/type_id/" + shopListBeanT2_data.get(position - 1).getId(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                for (int i = 0; i < t_data.size(); i++) {
                    Log.d("mafuhua", "result***********" + t_data.get(i).getId());
                }
                Log.d("mafuhua", "result***********" + result);
                Log.d("mafuhua", "result***********" + ContactURL.GetShopListContent_URL + shop_user_id + "/type_id/" + shopListBeanT2_data.get(position - 1).getId());
                Gson gson = new Gson();
                ShopListBean shopListBean = gson.fromJson(result, ShopListBean.class);
                if (shopListBean.getData() == null) {
                    shopListBeanData.clear();
                } else {
                    shopListBeanData = shopListBean.getData();
                }
                myGridAdapter.notifyDataSetChanged();
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

    static class MyRCAdapter extends RecyclerView.Adapter<MyRCAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private OnItemClickLitener mOnItemClickLitener;
        public int mrcPosition;

        public MyRCAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public int getItemCount() {
            return shopListBeanT2_data.size() + 1;
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.layout_home_recycle_textview,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mTxt = (TextView) view.findViewById(R.id.home_icon__item_text);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder,final int i) {
            if (i == 0) {
                viewHolder.mTxt.setText("全部");
                //viewHolder.mTxt.setTextColor(Color.YELLOW);
            } else {
                viewHolder.mTxt.setText(shopListBeanT2_data.get(i - 1).getNav_name());
            }
          /*  if (mrcPosition == HomeMarketActivity.mRCPosition) {
                viewHolder.mTxt.setTextColor(Color.parseColor("#FEBB24"));
            } else {
                viewHolder.mTxt.setTextColor(Color.parseColor("#757575"));
            }*/
            //如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
                    }
                });

            }
        }


        /**
         * ItemClick的回调接口
         *
         * @author zhy
         */
        public interface OnItemClickLitener {
            void onItemClick(View view, int position);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImg;
            TextView mTxt;

            public ViewHolder(View arg0) {
                super(arg0);
            }
        }

    }

    class MyAdapter extends BaseAdapter {
        public int mPosition;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return t_data.size() + 1;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            mPosition = position;
            if (position == 0) {
                tv.setText("全部");
            } else {
                tv.setText(t_data.get(position - 1).getNav_name());
            }
            if (position == HomeMarketActivity.mPosition) {
                convertView.setBackgroundColor(Color.WHITE);
                tv.setTextColor(getResources().getColor(R.color.titlebar_bg));
            } else {
                convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
                tv.setTextColor(Color.parseColor("#757575"));
            }
            return convertView;
        }
    }

    class MyGridAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return shopListBeanData.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.layout_commodity_grid_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvcommodityprice.setText("￥:" + shopListBeanData.get(position).getPro_price());
            viewHolder.tvcommoditydec.setText(shopListBeanData.get(position).getPro_name());
            x.image().bind(viewHolder.ivcommodityicon, shopListBeanData.get(position).getPro_img(), options);
            return convertView;
        }

        public class ViewHolder {
            public final ImageView ivcommodityicon;
            public final TextView tvcommoditydec;
            public final TextView tvcommodityprice;
            public final TextView tvcommodityinventory;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                ivcommodityicon = (ImageView) root.findViewById(R.id.iv_commodity_icon);
                tvcommoditydec = (TextView) root.findViewById(R.id.tv_commodity_dec);
                tvcommodityprice = (TextView) root.findViewById(R.id.tv_commodity_price);
                tvcommodityinventory = (TextView) root.findViewById(R.id.tv_commodity_inventory);
            }
        }
    }
}

