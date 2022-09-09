package com.xiong.rxdemo.http;

import android.content.Context;
import android.content.DialogInterface;

import com.xiong.rxdemo.http.listener.HttpResponseListener;
import com.xiong.rxdemo.http.widget.IDialog;
import com.xiong.rxdemo.http.widget.ProgressDialog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author: xiong
 * @time: 2017/12/06
 * @说明: 网络请求订阅
 */

public class HttpSubscriber<T> implements Observer<T> {

    // dialog
    private ProgressDialog mProgressDialog;
    // 操作泛型
    private Class<T> mClass;
    // 网络回调
    private HttpResponseListener<T> mHttpResponseListener;
    // Disposable
    private Disposable disposable;


    public HttpSubscriber(Context context, IDialog state, Class<T> mClass, HttpResponseListener<T> mHttpResponseListener) {
        showProgressDialog(context, state);
        this.mClass = mClass;
        this.mHttpResponseListener = mHttpResponseListener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.disposable = d;
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
        dismissProgressDialog();
        mHttpResponseListener.onComplete();
    }

    private void onCancel() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        dismissProgressDialog();
    }

    /**
     * 显示dialog
     */
    private void showProgressDialog(Context context, IDialog state) {
        switch (state) {
            case UN_LOADING:
                break;
            case NORMAL_LOADING:
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setCancelable(true);
                break;
            case FORBID_LOADING:
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setCancelable(false);
                break;
            default:
                break;
        }
        mProgressDialog.show();
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                HttpSubscriber.this.onCancel();
            }
        });
    }

    /**
     * 销毁dialog
     */
    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
