package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class WeifuKuanPager extends BasePager {
    public WeifuKuanPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("我是WeifuKuanPager");
        return textView;
    }

    @Override
    public void initData() {

    }


}
