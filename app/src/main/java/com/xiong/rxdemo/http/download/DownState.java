package com.xiong.rxdemo.http.download;

/**
 * @author: xiong
 * @time: 2022/08/12
 * @说明: 0, 未下载 1,下载完成 ,2下载中 3,暂停 4,错误
 */
public enum DownState {
    UNDOWN(0),
    FINISH(1),
    START(2),
    PAUSE(3),
    ERROR(4);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownState(int state) {
        this.state = state;
    }
}
