package com.yuen.xemyhd.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * fragment工厂类
 *
 * @author wangdh
 */
public class FragmentFractory {

    private static FragmentFractory instance = new FragmentFractory();
    private HashMap<Integer, Fragment> cacheFragment = new HashMap<Integer, Fragment>();

    private FragmentFractory() {
    }

    public static FragmentFractory getInstance() {
        return instance;
    }

    //根据当前位置获取对应的fragment
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        fragment = cacheFragment.get(position);
        if (fragment == null) {
            if (position == 0) {//首页
                fragment = new HomeFragment();
            } else if (position == 1) {//应用
                fragment = new KuaiDiFragment();
            } else if (position == 2) {//游戏
                fragment = new GouWuCheFragment2();
            } else if (position == 3) {//专题
                fragment = new WoDeFragment();
            }
            cacheFragment.put(position, fragment);
        }
        return fragment;
    }
}
