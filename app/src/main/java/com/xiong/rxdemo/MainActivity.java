package com.xiong.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiong.rxdemo.bean.News;
import com.xiong.rxdemo.http.HttpService;
import com.xiong.rxdemo.http.HttpSubscriber;
import com.xiong.rxdemo.http.compose.ConvertSchedulers;
import com.xiong.rxdemo.http.listener.SimpleNetResponseListener;
import com.xiong.rxdemo.widget.IDialog;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        tv = findViewById(R.id.tv);
    }

    private void getCode() {
        HttpService.getInstance().Test()
                .compose(new ConvertSchedulers<News>())
                .subscribe(new HttpSubscriber<>(this, IDialog.FORBID_LOADING, News.class, new SimpleNetResponseListener<News>() {
                    @Override
                    public void onSucceed(News data, String method) {
                        tv.setText(data.toString());
                    }
                }));
    }
}
