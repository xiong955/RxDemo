package com.xiong.rxdemo.http.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xiong.rxdemo.R;

import androidx.annotation.NonNull;


/**
 * @author: xiong
 * @time: 2019/05/07
 * @说明:
 */

public class ProgressDialog extends Dialog {

    /**
     * 创建自定义ProgressDialog
     * @param context     上下文
     */
    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.loading_dialog_style);
        setContentView(R.layout.dialog_loading);
        //旋转动画
        startAnimation();
        // 设置居中
        getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.6f;
        getWindow().setAttributes(lp);
    }

    @Override
    public void show() {
        super.show();
        startAnimation();
    }

    /** 旋转动画 */
    private void startAnimation(){
        ImageView customLoading = findViewById(R.id.custom_iv_loading);
        Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        customLoading.startAnimation(rotate);
    }
}
