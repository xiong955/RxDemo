package com.xiong.rxdemo.http.download.listener;

/**
 * @author: xiong
 * @time: 2021/10/25
 * @说明: 下载接口
 */
public interface DownloadListener {

    void onProgress(long downSize, long fileSize);//下载进度

}
