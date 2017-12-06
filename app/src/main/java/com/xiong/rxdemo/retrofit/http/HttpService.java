package com.xiong.rxdemo.retrofit.http;

import com.xiong.rxdemo.retrofit.RetrofitClient;

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
     * 获取接口，然后就可以玩起来了
     */
    public static Api getInstance() {
        if (INSTANCE == null) {
            INSTANCE = RetrofitClient.getInstance().create(Api.class);
        }
        return INSTANCE;
    }

}
