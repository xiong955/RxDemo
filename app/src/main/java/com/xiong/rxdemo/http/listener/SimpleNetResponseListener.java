package com.xiong.rxdemo.http.listener;

import android.util.Log;

/**
 * @author: xiong
 * @time: 2019/05/06
 * @说明:
 */
public abstract class SimpleNetResponseListener<T> implements HttpResponseListener<T> {

    @Override
    public void onError(Throwable exception) {
        Log.e("onError", exception.getMessage());
    }

    @Override
    public void onComplete() {

    }
}