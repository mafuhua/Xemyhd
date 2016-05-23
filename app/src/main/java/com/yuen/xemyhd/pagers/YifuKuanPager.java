package com.yuen.xemyhd.pagers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


public class YifuKuanPager extends BasePager {
    public YifuKuanPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("我是YifuKuanPager");
        return textView;
    }

    @Override
    public void initData() {

    }


}
