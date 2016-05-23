package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.view.View;

public abstract class BasePager {
    public Context context;

    public BasePager(Context context) {
        this.context = context;
    }

    /**
     * 初始化界面方法
     * 子类必须重写
     * 在Viewpager的适配器中，初始化item方法中调用，instantiateItem
     *
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     * 在Viewpager的适配器中，初始化item方法中调用，instantiateItem
     */
    public abstract void initData();
}