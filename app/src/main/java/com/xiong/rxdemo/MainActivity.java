package com.xiong.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xiong.rxdemo.http.listener.HttpResponseListener;
import com.xiong.rxdemo.http.HttpService;
import com.xiong.rxdemo.http.HttpSubscriber;
import com.xiong.rxdemo.http.compose.ConvertSchedulers;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });


    }

    private void getCode() {
        HttpService.getInstance().requestCode()
                .compose(new ConvertSchedulers<String>())
                .subscribe(new HttpSubscriber<>(this,String.class , new HttpResponseListener<String>() {
                    @Override
                    public void onSucceed(String data, String method) {
                        Log.e("111","111");
                    }

                    @Override
                    public void onError(Throwable exception) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
