package com.xiong.rxdemo.http.retrofit;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author: xiong
 * @time: 2017/11/30
 * @说明: Retrofit
 */

public class RetrofitClient {

    private final static String TAG = "RetrofitClient";

    /**
     * 单例
     */
    private static Retrofit INSTANCE;

    private RetrofitClient() {
    }

    /**
     * 初始化
     */
    public static void instance(Builder builder) {
        if (null == INSTANCE) {
            INSTANCE = new RetrofitClient().crateRetrofit(builder);
        }
    }

    /**
     * 单例对象
     */
    public static Retrofit getInstance() {
        if (null == INSTANCE) {
            throw new UnknownError("Not initialized");
        }
        return INSTANCE;
    }

    /**
     * 创建Client
     */
    private Retrofit crateRetrofit(Builder config) {
        Log.e("HttpLog", "创建RetrofitClient");
        /* 拦截器 ->  */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("HttpLog", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 初始化OkHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(config.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(config.writeTimeout, TimeUnit.SECONDS)
                .connectTimeout(config.connectionTimeout, TimeUnit.SECONDS);
        // 是否调试
        if (config.debug) {
            builder.addInterceptor(loggingInterceptor);
        }
        OkHttpClient client = builder.build();
        // 初始化 retrofit
        Retrofit retrofit = new Retrofit.Builder()
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(config.baseUrl)
                .build();
        return retrofit;
    }

    public static final class Builder {
        // 读取超时-默认6秒
        int readTimeout = 6;
        // 写入超时-默认6秒
        int writeTimeout = 6;
        // 连接超时-默认6秒
        int connectionTimeout = 6;
        // 是否调试
        boolean debug = true;
        //绑定的BaseUrl
        String baseUrl;

        public Builder(Context context, String appId, String baseUrl) {
            this.baseUrl = baseUrl;
            //toast,网络异常,缓存
        }

        /**
         * 是否开启网络请求输出 调试日志
         *
         * @param debug
         */
        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        /**
         * 读取超时
         *
         * @param readTimeout
         */
        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * 写入超时
         *
         * @param writeTimeout
         */
        public Builder setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * 链接超时
         *
         * @param connectionTimeout
         */
        public Builder setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * 参数构造完毕
         * 初始化 单例的 RetrofitClient
         */
        public void build() {
            instance(this);
        }
    }
}
