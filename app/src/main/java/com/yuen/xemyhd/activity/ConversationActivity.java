package com.yuen.xemyhd.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.utils.SysExitUtil;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.TextInputProvider;
import io.rong.imlib.model.Conversation;

/**
 * Created by Bob on 2015/4/16.
 */  
public class ConversationActivity extends ActionBarActivity {
  
    private static final String TAG = ConversationActivity.class.getSimpleName();  
  
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override  
    public void onCreate(Bundle savedInstanceState) {
  
        super.onCreate(savedInstanceState);  
        //唯一有用的代码，加载一个 layout  
        setContentView(R.layout.conversation);
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
} 