package com.xiong.rxdemo.http.download.subscribers;

import android.os.Handler;

import com.xiong.rxdemo.http.download.DownModel;
import com.xiong.rxdemo.http.download.DownState;
import com.xiong.rxdemo.http.download.DownloadUtil;
import com.xiong.rxdemo.http.download.db.DataHelperUtil;
import com.xiong.rxdemo.http.download.listener.DownloadListener;
import com.xiong.rxdemo.http.download.listener.HttpDownOnNextListener;

import java.lang.ref.SoftReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: xiong
 * @time: 2022/08/12
 * @说明:
 */
public class ProgressDownSubscriber<T> implements Observer<T>, DownloadListener {

    //弱引用结果回调
    private SoftReference<HttpDownOnNextListener> mSubscriberOnNextListener;
    /*下载数据*/
    private DownModel downModel;
    private Handler handler;
    private Disposable disposable;

    public ProgressDownSubscriber(DownModel downModel, Handler handler) {
        this.mSubscriberOnNextListener = new SoftReference<>(downModel.getListener());
        this.downModel = downModel;
        this.handler = handler;
    }

    public void DownModel(DownModel downModel) {
        this.mSubscriberOnNextListener = new SoftReference<>(downModel.getListener());
        this.downModel = downModel;
    }
    // Observer

    @Override
    public void onSubscribe(Disposable disposable) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onStart();
        }
        downModel.setState(DownState.START.getState());
        this.disposable = disposable;
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
        DownloadUtil.getInstance().remove(downModel);
        downModel.setState(DownState.ERROR.getState());
        DataHelperUtil.getInstance().write(downModel);
    }

    @Override
    public void onComplete() {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onComplete();
        }
        DownloadUtil.getInstance().remove(downModel);
        downModel.setState(DownState.FINISH.getState());
        DataHelperUtil.getInstance().write(downModel);
    }

    public Disposable getDisposable() {
        return disposable;
    }


    @Override
    public void onProgress(long downSize, long fileSize) {
        if (downModel.getTotalSize() > fileSize) {
            downSize = downModel.getTotalSize() - fileSize + downSize;
        } else {
            downModel.setTotalSize(fileSize);
        }
        downModel.setDownSize(downSize);

//        handler.post(new Runnable() {
//            @Override
//            public void run() {
                if (downModel.getState() == DownState.PAUSE.getState()) return;
                downModel.setState(DownState.START.getState());
                long percent = downModel.getDownSize() * 100 / downModel.getTotalSize();
                downModel.setPercent((int) percent);
                mSubscriberOnNextListener.get().updateProgress(downModel.getDownSize(), downModel.getTotalSize(), percent);
                DataHelperUtil.getInstance().write(downModel);
//            }
//        });
    }

}
