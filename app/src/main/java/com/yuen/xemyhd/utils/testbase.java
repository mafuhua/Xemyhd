package com.yuen.xemyhd.utils;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.base.BaseHolder;
import com.yuen.xemyhd.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
public class testbase extends AppCompatActivity{
    class base extends DefaultAdapter{
        public base(List datas) {
            super(datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = View.inflate(getApplicationContext(), R.layout.layout_market_list_item, null);
            }
            return super.getView(position, convertView, parent);
        }

        @Override
        public BaseHolder getHolder() {
            return null;
        }



    }

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new SettingHolder();
        }
    }
}

class SettingHolder extends BaseHolder<String> {
    private TextView tvshopmanagerleft;
    private TextView tvshopmanagerright;

    @Override
    public View initView() {
           /* View view = View.inflate(getapp, R.layout.layout_wode_setting_item, null);
            tvshopmanagerleft = (TextView) view.findViewById(R.id.tv_shop_manager_left);
            tvshopmanagerright = (TextView) view.findViewById(R.id.tv_shop_manager_right);*/
        return null;
    }

    @Override
    public void refreshView(String data, int position) {
        tvshopmanagerleft.setText(data);
        tvshopmanagerright.setText("");
    }


}
