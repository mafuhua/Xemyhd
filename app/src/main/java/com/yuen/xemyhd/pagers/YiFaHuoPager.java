package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class YiFaHuoPager extends BasePager {
    public YiFaHuoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("我是YiFaHuoPager");
        return textView;
    }

    @Override
    public void initData() {

    }


}
