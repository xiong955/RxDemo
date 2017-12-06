package com.xiong.rxdemo.retrofit.http;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * @author: xiong
 * @time: 2017/11/30
 * @说明: 接口
 */

public interface Api {

    /* 验证码 */
    @GET(ServerUrl.CODE)
    Observable<Response<String>> requestCode();

}
