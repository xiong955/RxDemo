package com.xiong.rxdemo.http.compose;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * @author: xiong
 * @time: 2017/12/06
 * @说明: 调度器
 */

public class ConvertSchedulers<T> implements ObservableTransformer<Response<T>, T> {
    @Override
    public ObservableSource<T> apply(@NonNull Observable<Response<T>> upstream) {
        return upstream
//                .delay(5, TimeUnit.SECONDS)     //请求延迟五秒，再开始
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                //转化一下
                .flatMap(new Function<Response<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull Response<T> response) throws Exception {
                        if (response.isSuccessful()) {
                            return Observable.just(response.body());
                        } else {
                            int code = response.code();
                            String string = response.errorBody().string();
                            return Observable.error(new Throwable(string));
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread(), true)
                .retryWhen(new RetryWhenNetwork());
    }
}

