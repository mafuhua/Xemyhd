package com.yuen.xemyhd.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xemyhd.R;
import com.yuen.xemyhd.activity.AddressManagerActivity;
import com.yuen.xemyhd.activity.MainActivity;
import com.yuen.xemyhd.activity.MyInfomationActivity;
import com.yuen.xemyhd.activity.OrderListActivity;
import com.yuen.xemyhd.activity.SettingActivity;
import com.yuen.xemyhd.activity.WoOftenGetActivity;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;
import com.yuen.xemyhd.bean.MyInfoBean;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.MyApplication;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class WoDeFragment extends BaseFragment {
    private LinearLayout mLayoutTitleUsericon;
    private ImageView mIvUserIcon;
    private TextView mTvUserName;
    private TextView mTvUserTel;
    private View view;
    private ListView mIvWoDe;
    private MyAdapter myAdapter;
    private SharedPreferences sharedPreferences;
    private List<String> wodeItemDec = new ArrayList<String>(Arrays.asList("订单", /*"积分",*/
            "我常买", "收货地址", /*"我的分享", "邀请好友", */"客服中心", "设置"));
    private RelativeLayout mRlWodeUserInfo;
    public static MyInfoBean.DataBean myInfoBeanData;

    private void assignViews() {
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        mLayoutTitleUsericon = (LinearLayout) view.findViewById(R.id.layout_title_usericon);
        mRlWodeUserInfo = (RelativeLayout) view.findViewById(R.id.rl_wode_user_info);
        mIvUserIcon = (ImageView) view.findViewById(R.id.iv_user_icon);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mTvUserTel = (TextView) view.findViewById(R.id.tv_user_tel);
        mIvWoDe = (ListView) view.findViewById(R.id.lv_wode);
        myAdapter = new MyAdapter(wodeItemDec);
        mIvWoDe.setAdapter(myAdapter);
        mRlWodeUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyInfomationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getActivity().startActivity(intent);
            }
        });
        mIvWoDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), OrderListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), WoOftenGetActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), AddressManagerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;

                    case 3:
                        takecall();
                        break;
                    case 4:
                        intent = new Intent(getActivity(), SettingActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;
                    case 5:
                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                }
            }
        });
    }

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.layout_wodefragment, null);
        assignViews();
        return view;
    }

    @Override
    public void initData() {
        getUserInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    private void getUserInfo() {
        XUtils.xUtilsGet(ContactURL.GetMyInfo_URL + MainActivity.useruid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "GetMyInfo_URL----------" + result);
                Gson gson = new Gson();
                MyInfoBean myInfoBean = gson.fromJson(result, MyInfoBean.class);
                myInfoBeanData = myInfoBean.getData();
                sharedPreferences.edit().putString("icon",myInfoBeanData.getImg()).apply();
                mTvUserName.setText(myInfoBeanData.getNickname());
                mTvUserTel.setText(MainActivity.usertel);
                if (WoDeFragment.myInfoBeanData.getImg() != null) {
                    x.image().bind(mIvUserIcon, WoDeFragment.myInfoBeanData.getImg(), MyApplication.options);
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

    protected void takecall() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确定联系客服中心吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //用intent启动拨打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "15900655030"));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new WoDeHolder();
        }
    }

    class WoDeHolder extends BaseHolder<String> {
        public ImageView ivwodeitemicon;
        public TextView tvwodeitemdec;
        private int[] wodeItemImg = new int[]{R.drawable.dd3x, /*R.drawable.jf3x,*/ R.drawable.cm3x,
                R.drawable.dz3x,/* R.drawable.fx3x, R.drawable.yq3x,*/ R.drawable.kf3x, R.drawable.sz3x};

        @Override
        public View initView() {
            View root = View.inflate(getActivity(), R.layout.layout_wode_list_item, null);
            ivwodeitemicon = (ImageView) root.findViewById(R.id.iv_wode_item_icon);
            tvwodeitemdec = (TextView) root.findViewById(R.id.tv_wode_item_dec);
            return root;
        }

        @Override
        public void refreshView(String data, int position) {
            tvwodeitemdec.setText(data);
            ivwodeitemicon.setBackgroundResource(wodeItemImg[position]);
        }
    }
}
