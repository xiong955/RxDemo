package com.xiong.rxdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiong.rxdemo.bean.News;
import com.xiong.rxdemo.http.HttpService;
import com.xiong.rxdemo.http.HttpSubscriber;
import com.xiong.rxdemo.http.compose.ConvertSchedulers;
import com.xiong.rxdemo.http.download.DownModel;
import com.xiong.rxdemo.http.download.DownloadUtil;
import com.xiong.rxdemo.http.download.listener.HttpDownOnNextListener;
import com.xiong.rxdemo.http.listener.SimpleNetResponseListener;
import com.xiong.rxdemo.http.widget.IDialog;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "DownUtil";

    private Button start, pause;
    private TextView tv;

    private DownModel downModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        tv = findViewById(R.id.tv);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadUtil.getInstance().pause(downModel);
            }
        });

        DownloadUtil.getInstance().setContext(getApplicationContext());
        downModel = new DownModel();
    }

    private void down() {
        downModel.setBusinessName("test-plugin");
        downModel.setName("test.zip");
        downModel.setUrl(""); // 下载地址
        downModel.setVersion(1);
        downModel.setListener(listener);
        DownloadUtil.getInstance().startDown(downModel);
    }

    private void getCode() {
        HttpService.getInstance().Test()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .compose(new ConvertSchedulers<News>())
                .retryWhen(new RetryWhenNetwork())
                .subscribe(new HttpSubscriber<>(this, IDialog.FORBID_LOADING, News.class, new SimpleNetResponseListener<News>() {
                    @Override
                    public void onSucceed(News data, String method) {

                    }
                }));
    }

    HttpDownOnNextListener<DownModel> listener = new HttpDownOnNextListener<DownModel>() {

        long lastSize = 0;
        long exitTime = 0;

        @Override
        public void onNext(DownModel model) {
            Log.d(TAG, "下载完成,文件地址" + model.getPath());
        }

        @Override
        public void onStart() {
            Log.d(TAG, "开始下载");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "下载结束");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d(TAG, "下载失败," + e.toString());
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, "下载暂停");
        }

        @Override
        public void updateProgress(long downSize, long fileSize, long percent) {
            try {
                Log.d("下载进度", "下载进度-->" + downSize + "," + fileSize + "," + percent);
                String downSizeShow = downSize / 1024 / 1024 + "M";
                String fileSizeShow = fileSize / 1024 / 1024 + "M";
                String text = percent + "%\n" + downSizeShow + "/" + fileSizeShow;
                if ((System.currentTimeMillis() - exitTime) > 1000) {
                    long bytes = downSize - lastSize;
                    lastSize = downSize;
                    exitTime = System.currentTimeMillis();
                    Log.d("下载进度", "一秒下载大小" + bytes);
                    String speed = bytes / 1024 / 1024 + "M";
                    text = text + "\n" + speed;
                    tv.setText(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
