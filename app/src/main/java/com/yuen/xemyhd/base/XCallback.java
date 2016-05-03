package com.yuen.xemyhd.base;

import org.xutils.common.Callback;

/**
 * Created by Administrator on 2016/5/2.
 */
public abstract class XCallback implements Callback.CommonCallback {
    @Override
    public void onSuccess(Object result) {
        Success(result);
    }

    public abstract void Success(Object result);

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
