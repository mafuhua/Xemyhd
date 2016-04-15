package com.yuen.xemyhd.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xemyhd.R;
import com.yuen.xemyhd.fragment.MyFragment;

public class HomeMarketActivity extends FragmentActivity implements
        AdapterView.OnItemClickListener {

    private String[] strs = { "常用分类", "服饰内衣", "鞋靴", "手机", "家用电器", "数码", "电脑办公",
            "常用分类", "服饰内衣", "鞋靴", "手机", "家用电器", "数码", "电脑办公",
            "个护化妆", "图书" };
    private ListView listView;
    private MyAdapter adapter;
    private MyFragment myFragment;
    public static int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_market);

        initView();
    }



    /**
     * 初始化view
     */
    private void initView() {
        // TODO Auto-generated method stub
        listView = (ListView) findViewById(R.id.listview);

        adapter = new MyAdapter(this, strs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        //创建MyFragment对象
        myFragment = new MyFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putString(MyFragment.TAG, strs[mPosition]);
        myFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        //拿到当前位置
        mPosition = position;
        //即使刷新adapter
        adapter.notifyDataSetChanged();
        for (int i = 0; i < strs.length; i++) {
            myFragment = new MyFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, myFragment);
            Bundle bundle = new Bundle();
            bundle.putString(MyFragment.TAG, strs[position]);
            myFragment.setArguments(bundle);
            fragmentTransaction.commit();
        }
    }

    class MyAdapter extends BaseAdapter {

        private Context context;
        private String[] strings;
        public  int mPosition;

        public MyAdapter(Context context, String[] strings){
            this.context =context;
            this.strings = strings;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return strings.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return strings[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            mPosition = position;
            tv.setText(strings[position]);
            if (position == HomeMarketActivity.mPosition) {
                convertView.setBackgroundResource(R.drawable.tongcheng_all_bg01);
            } else {
                convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
            }
            return convertView;
        }
    }

}

