package com.xiong.rxdemo.http.listener;

/**
 * @author: xiong
 * @time: 2017/12/06
 * @说明: 网络请求回调
 */

public interface HttpResponseListener<T> {
    /**
     * 成功后回调方法
     *
     * @param data
     * @param method
     */
    void onSucceed(T data, String method);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param exception
     */
    void onError(Throwable exception);

    /**
     * 观察者结束,不管 错误与否，都会被调用
     */
    void onComplete();
}
