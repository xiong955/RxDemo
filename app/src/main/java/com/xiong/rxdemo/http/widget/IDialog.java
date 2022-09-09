package com.xiong.rxdemo.http.widget;

/**
 * @author: xiong
 * @time: 2019/05/07
 * @说明:
 */
public enum IDialog {
    //不显示进度条
    UN_LOADING,
    //显示进度条,点击空白出可以取消
    NORMAL_LOADING,
    //显示进度条,点击空白处 不可取消
    FORBID_LOADING
}
