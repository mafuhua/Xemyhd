package com.yuen.xemyhd.activity;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.utils.Friend;
import com.yuen.xemyhd.utils.SysExitUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.TextInputProvider;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Bob on 2015/4/16.
 */
public class ConversationActivity extends ActionBarActivity implements RongIM.UserInfoProvider {
    private static final String TAG = ConversationActivity.class.getSimpleName();
    private List<Friend> userIdList;
    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private TextView tv_tixian;
    private LinearLayout layout_title_bar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //唯一有用的代码，加载一个 layout
        setContentView(R.layout.conversation);
        initView();
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend(MainActivity.useruid, MainActivity.nickname, MainActivity.icon));
        userIdList.add(new Friend(HomeMarketActivity.shop_user_id, HomeMarketActivity.shop_title, HomeMarketActivity.img));
     /*   userIdList.add(new Friend("359","联通","http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));

        userIdList.add(new Friend("1","移动","http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
*/
        RongIM.setUserInfoProvider(this, true);
        SysExitUtil.activityList.add(this);

        TextInputProvider textInputProvider = new TextInputProvider(RongContext.getInstance());
        RongIM.setPrimaryInputProvider(textInputProvider);
        InputProvider.ExtendProvider[] provider = {
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//相机
        };

        RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, provider);
        //继承的是ActionBarActivity，直接调用 自带的 Actionbar，下面是Actionbar 的配置，如果不用可忽略…
      /*  getSupportActionBar().setTitle(“聊天”);
        getSupportActionBar().setLogo(R.drawable.de_bar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.de_actionbar_back);  */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        for (Friend i : userIdList) {
            if (i.getUserId() == null) break;
            if (i.getUserId().equals(userId)) {
                Log.e("mafuhua", i.getPortraitUri()+i.getUserName()+Uri.parse(i.getPortraitUri()));
                return new UserInfo(i.getUserId(), i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
        return null;
    }

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        iv_btn_add.setVisibility(View.GONE);
        tv_title_dec.setText("小而美");
        iv_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
} 