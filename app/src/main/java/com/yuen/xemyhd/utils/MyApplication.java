package com.yuen.xemyhd.utils;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/3/22.
 */
public class MyApplication extends Application {
    public static Context context;
    public static ImageOptions options;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        context = this;
        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(true);
        options = new ImageOptions.Builder()
                .setRadius(20)
                        // 是否忽略GIF格式的图片
                .setIgnoreGif(false)
                        // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)

                .build();
    }
}

