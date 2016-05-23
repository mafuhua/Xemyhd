package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class WeifaHuoPager extends BasePager {
    public WeifaHuoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("我是WeifaHuoPager");
        return textView;
    }

    @Override
    public void initData() {

    }


}
