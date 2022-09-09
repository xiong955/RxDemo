package com.xiong.rxdemo.http.interfac;

import com.xiong.rxdemo.bean.News;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author: xiong
 * @time: 2017/11/30
 * @说明: 接口
 */

public interface Api {

    /* 验证码 */
    @GET(ServerUrl.Test)
    Observable<Response<News>> Test(
//            @Query("page") String page,
//            @Query("type") String type
    );

    @GET
    @Streaming
    Observable<Response<ResponseBody>> downLoad(
            @Header("RANGE") String start,
            @Url String uri
    );

}
