package com.yuen.xemyhd.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.activity.HomeMarketListActivity;
import com.yuen.xemyhd.activity.MainActivity;
import com.yuen.xemyhd.activity.SearchWorldActivity;
import com.yuen.xemyhd.bean.BaseBean;
import com.yuen.xemyhd.bean.FriendBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyApplication;
import com.yuen.xemyhd.utils.MyUtils;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    // 图片资源ID
    private final int[] imageIds = {R.drawable.tu1, R.drawable.tu2, R.drawable.tu3};
    public OnButtonClick onButtonClick;
    private ViewPager mVpHomepageDec;
    private LinearLayout mLlPointGroup;
    private RelativeLayout mRlHomeMarket;
    private RelativeLayout mRlHomeWorld;
    private ImageView mIvBtnHomeLove;
    private RecyclerView mRcHomeHorizontal;
    private MyRCAdapter myRCAdapter;
    private List<FriendBean.DataBean> mDatas;
    private Button mBtnHomeAddIcon;
    private MyPagerAdapter myPagerAdapter;
    private Context context;
    /**
     * 页面改变时，上一个页面的下标
     */
    private int lastPosition;
    /**
     * 自动切换是否开启
     */
    private boolean isRunning = false;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            // 让viewPager 显示下一页
            if (isRunning && mVpHomepageDec.getCurrentItem() < myPagerAdapter.getCount() - 1) {
                mVpHomepageDec.setCurrentItem(mVpHomepageDec.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(88, 3000);
            }
        }

        ;
    };

    private void assignViews(View view) {
        context = getActivity();
        mRcHomeHorizontal = (RecyclerView) view.findViewById(R.id.rc_home_horizontal);
        mVpHomepageDec = (ViewPager) view.findViewById(R.id.vp_homepage_dec);
        mLlPointGroup = (LinearLayout) view.findViewById(R.id.ll_point_group);
        mBtnHomeAddIcon = (Button) view.findViewById(R.id.btn_home_addicon);
        mRlHomeMarket = (RelativeLayout) view.findViewById(R.id.rl_home_market);
        mRlHomeWorld = (RelativeLayout) view.findViewById(R.id.rl_home_world);
        mIvBtnHomeLove = (ImageView) view.findViewById(R.id.iv_btn_home_love);
        myPagerAdapter = new MyPagerAdapter();
        mVpHomepageDec.setAdapter(myPagerAdapter);
        //  addPoints();
        regListener();
        isRunning = true;
        handler.sendEmptyMessageDelayed(88, 3000); // 发送一个延时消息，3秒后，执行handlerMessage
        mIvBtnHomeLove.setOnClickListener(this);
        mBtnHomeAddIcon.setOnClickListener(this);
        mRlHomeMarket.setOnClickListener(this);
        mRlHomeWorld.setOnClickListener(this);
        mDatas = new ArrayList<>();
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcHomeHorizontal.setLayoutManager(linearLayoutManager);
        //设置适配器
        myRCAdapter = new MyRCAdapter(getActivity(), mDatas);
        myRCAdapter.setOnItemClickLitener(new MyRCAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        mRcHomeHorizontal.setAdapter(myRCAdapter);

    }

    private void regListener() {
        //给viewPager 添加页面改变的监听
        mVpHomepageDec.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            /**
             *  当页面选择发生改变时，调用此方法
             *  @param position 新选择的页面的下标
             */
            public void onPageSelected(int position) {
                position = position % imageIds.length; // 防止集合下标越界
              /*
                //改变描述文字
                tvDesc.setText(imageDescriptions[position]);*/
                // 改变指示点
                // 上一个页面，灰点
            /*    mLlPointGroup.getChildAt(lastPosition).setEnabled(false);
                // 找到对应下标的point ，并改变显示
                mLlPointGroup.getChildAt(position).setEnabled(true);
*/
                lastPosition = position;// 为上一个页面赋值

            }

            @Override
            // 当页面滑动时，调用此方法
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            // 当页面的滑动状态发生改变时，
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addPoints() {
        for (int i = 0; i < imageIds.length; i++) {
            // 动态添加指示点
            ImageView point = new ImageView(getActivity());
            point.setBackgroundResource(R.drawable.point_bg); // 设置背景

            // 默认让第一个点是选中状态
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            // 布局参数 : 当布局添加子view 时， 布局参数一定要和布局的类型 匹配
            // 向线性布局中，添加子view时，一定要指定线性布局的布局参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);

            layoutParams.leftMargin = 10; // 左边距，10象素
            layoutParams.topMargin = 5; // 上边距 ,5 象素

            mLlPointGroup.addView(point, layoutParams); // 添加至页面中之前准备好的布局


        }
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.layout_homefragment, null);
        assignViews(view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_home_market:
                if (MainActivity.city == null || MainActivity.district == null || MainActivity.street == null || MainActivity.province == null) {
                    MyUtils.toastShow(getActivity(), "正在定位中...", Toast.LENGTH_SHORT);
                    break;
                }
                intent = new Intent(context, HomeMarketListActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_home_world:
                intent = new Intent(context, SearchWorldActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_btn_home_love:
                if (mDatas.size() > 0) {
                    String uids = "";
                    for (int i = 0; i < mDatas.size(); i++) {
                        uids = mDatas.get(i).getUid() + ",";
                    }

                    HashMap<String, String> map = new HashMap<>();
                    map.put("user_id", uids);
                    XUtils.xUtilsPost(ContactURL.JPUSH_URL, map, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("HomeFragment", "---JPUSH_URL------" + result);
                         /*   Gson gson = new Gson();
                            BaseBean baseBean = gson.fromJson(result, BaseBean.class);
                            Toast.makeText(context, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
*/
                            if (onButtonClick != null) {
                                onButtonClick.onClick(mIvBtnHomeLove);
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


                    // getActivity().sendBroadcast(new Intent("com.yuen.xemyhd.fragment.HomeFragment"));
                }
                break;
            case R.id.btn_home_addicon:
                XUtils.xUtilsGet(ContactURL.YIQIGOU_URL + MainActivity.useruid, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("HomeFragment", "---YIQIGOU_URL------" + result);
                        Gson gson = new Gson();
                        BaseBean baseBean = gson.fromJson(result, BaseBean.class);
                        if (baseBean.getCode().equals("1")) {
                            Toast.makeText(context, "不能一起购", Toast.LENGTH_SHORT).show();
                        } else {
                            settingShopManger();
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


                //   addData(0);
                break;


        }
    }

    public void addData(FriendBean.DataBean data) {
        mDatas.add(data);
        myRCAdapter.notifyDataSetChanged();
        // 加入如下代码保证position的位置正确性
        /*if (position != mDatas.size() - 1) {
            myRCAdapter.notifyItemRangeChanged(position, mDatas.size() - position);
        }*/
    }

    public void removeData(int position) {
        mDatas.remove(position);
        myRCAdapter.notifyItemRemoved(position);
    }

    public void settingShopManger() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请输入您好友的手机号");
        final EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        // editText.setHint(hint);
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weight = editText.getText().toString().trim();
                addFriend(weight);

            }


        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addFriend(String weight) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tel", weight);
        map.put("user_id", "1");
        XUtils.xUtilsPost(ContactURL.AddFriend_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "-----AddFriend_URL-----" + result);
                Gson gson = new Gson();
                if (result.contains("data")) {
                    FriendBean friendBean = gson.fromJson(result, FriendBean.class);
                    FriendBean.DataBean data = friendBean.getData();
                    addData(data);
                } else {
                    BaseBean baseBean = gson.fromJson(result, BaseBean.class);
                    Toast.makeText(context, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();

        isRunning = false;
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public interface OnButtonClick {
        void onClick(View view);
    }

    static class MyRCAdapter extends RecyclerView.Adapter<MyRCAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private List<FriendBean.DataBean> mDatas;
        private OnItemClickLitener mOnItemClickLitener;

        public MyRCAdapter(Context context, List<FriendBean.DataBean> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.layout_home_recycle_iconview,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mImg = (ImageView) view.findViewById(R.id.home_icon_item_image);
            viewHolder.mTxt = (TextView) view.findViewById(R.id.home_icon__item_text);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            x.image().bind(viewHolder.mImg, mDatas.get(i).getImage(), MyApplication.options);
            viewHolder.mTxt.setText(mDatas.get(i).getNickname());
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

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
           /* LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
            imageView .setLayoutParams(mParams);*/
            imageView.setImageResource(imageIds[position % imageIds.length]);
            // x.image().bind(imageView, imageIds[position], options);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
