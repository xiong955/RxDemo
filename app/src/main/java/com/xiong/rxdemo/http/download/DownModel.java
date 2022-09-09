package com.xiong.rxdemo.http.download;

import com.xiong.rxdemo.http.download.listener.HttpDownOnNextListener;
import com.xiong.rxdemo.http.interfac.Api;

/**
 * @author: xiong
 * @time: 2021/10/26
 * @说明:
 */
public class DownModel {

    private String url;           // 下载地址
    private String path;          // 存储路径
    private String name;          // 文件名
    private String businessName;  // 业务名
    private int version;          // 版本
    private long downSize;        // 下载大小
    private long totalSize;       // 总大小
    private int state;            // 状态 0,未下载 1,下载完成 ,2下载中 3,暂停
    private int percent;          // 下载进度

    /*下载唯一的HttpService*/
    private Api service;
    /*回调监听*/
    private HttpDownOnNextListener<DownModel> listener;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getDownSize() {
        return downSize;
    }

    public void setDownSize(long downSize) {
        this.downSize = downSize;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Api getService() {
        return service;
    }

    public void setService(Api service) {
        this.service = service;
    }

    public HttpDownOnNextListener<DownModel> getListener() {
        return listener;
    }

    public void setListener(HttpDownOnNextListener<DownModel> listener) {
        this.listener = listener;
    }
}
