package com.xiong.rxdemo.http.download.listener;

/**
 * @author: xiong
 * @time: 2022/08/12
 * @说明:
 */
public abstract class HttpDownOnNextListener<T> {
    /**
     * 成功后回调方法
     *
     * @param t
     */
    public abstract void onNext(T t);

    /**
     * 开始下载
     */
    public abstract void onStart();

    /**
     * 完成下载
     */
    public abstract void onComplete();


    /**
     * 下载进度
     *
     * @param downSize
     * @param fileSize
     * @param percent
     */
    public abstract void updateProgress(long downSize, long fileSize, long percent);

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     *
     * @param e
     */
    public void onError(Throwable e) {

    }

    /**
     * 暂停下载
     */
    public void onPause() {

    }
}
