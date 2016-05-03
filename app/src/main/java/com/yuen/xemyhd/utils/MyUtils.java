package com.yuen.xemyhd.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MyUtils {
    private static Toast toast = null;

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context  内容器实体
     * @param msg      提示文字所在资源id
     * @param duration 提示时间
     */
    public static void toastShow(Context context, String msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static String getInputString(Context context, EditText editText, String msg) {
        if (editText == null) {
            return null;
        } else {
            String content = editText.getText().toString();
            if (TextUtils.isEmpty(content)) {
                toastShow(context, msg, Toast.LENGTH_SHORT);
            }
                return content;
        }
    }
}
