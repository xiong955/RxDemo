package com.xiong.rxdemo.http;

import android.content.Context;

import com.xiong.rxdemo.http.listener.HttpResponseListener;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author: xiong
 * @time: 2017/12/06
 * @说明: 网络请求订阅
 */

public class HttpSubscriber<T> implements Observer<T> {

    // 操作泛型
    private Class<T> mClass;
    // 网络回调
    private HttpResponseListener<T> mHttpResponseListener;


    public HttpSubscriber(Context context, Class<T> mClass, HttpResponseListener<T> mHttpResponseListener) {
        this.mClass = mClass;
        this.mHttpResponseListener = mHttpResponseListener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        mHttpResponseListener.onSucceed(t, null);
    }

    @Override
    public void onError(@NonNull Throwable exception) {
        mHttpResponseListener.onError(exception);
    }

    @Override
    public void onComplete() {
        mHttpResponseListener.onComplete();
    }
}
