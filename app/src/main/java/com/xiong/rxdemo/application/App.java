package com.xiong.rxdemo.application;

import android.app.Application;

import com.xiong.rxdemo.BuildConfig;
import com.xiong.rxdemo.http.interfac.ServerUrl;
import com.xiong.rxdemo.http.retrofit.RetrofitClient;

/**
 * @author: xiong
 * @time: 2017/11/30
 * @说明: 初始化
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 网络请求初始化
        initNet();
    }

    private void initNet() {
        // 创建Retrofit
        new RetrofitClient.Builder(this, BuildConfig.APPLICATION_ID, ServerUrl.mServerUrl)
                .setDebug(BuildConfig.DEBUG)
                .setConnectionTimeout(10)
                .setReadTimeout(10)
                .setWriteTimeout(10)
                .build();
    }

}
