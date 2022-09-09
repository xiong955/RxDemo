package com.xiong.rxdemo.http;

import com.xiong.rxdemo.http.interfac.Api;
import com.xiong.rxdemo.http.retrofit.RetrofitClient;

/**
 * @author: xiong
 * @time: 2017/11/30
 * @说明:
 */

public class HttpService {
    private HttpService() {
    }

    /**
     * 网络请求 接口
     */
    private static Api INSTANCE;

    /**
     * 单例获取接口
     */
    public static Api getInstance() {
        if (INSTANCE == null) {
            INSTANCE = RetrofitClient.getInstance().create(Api.class);
        }
        return INSTANCE;
    }

}
