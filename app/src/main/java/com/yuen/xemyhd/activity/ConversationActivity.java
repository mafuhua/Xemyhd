package com.yuen.xemyhd.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.yuen.xemyhd.R;

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