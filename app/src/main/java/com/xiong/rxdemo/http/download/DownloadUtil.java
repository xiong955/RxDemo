package com.xiong.rxdemo.http.download;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xiong.rxdemo.http.compose.RetryWhenNetwork;
import com.xiong.rxdemo.http.download.db.DataHelperUtil;
import com.xiong.rxdemo.http.download.listener.DownloadInterceptor;
import com.xiong.rxdemo.http.download.subscribers.ProgressDownSubscriber;
import com.xiong.rxdemo.http.interfac.Api;
import com.xiong.rxdemo.http.interfac.ServerUrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: xiong
 * @time: 2021/10/25
 * @说明: 下载工具类
 */
public class DownloadUtil {

    private Context mContext;
    /*单利对象*/
    private static DownloadUtil instance;
    /*记录下载数据*/
    private Set<DownModel> downModels;
    /*回调sub队列*/
    private HashMap<String, ProgressDownSubscriber> subMap;
    /*下载进度回掉主线程*/
    private Handler handler;

    public static DownloadUtil getInstance() {
        synchronized (Object.class) {
            if (instance == null) {
                instance = new DownloadUtil();
            }
            return instance;
        }
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public DownloadUtil() {
        downModels = new HashSet<>();
        subMap = new HashMap<>();
        handler = new Handler(Looper.getMainLooper());
    }


    /**
     * 下载单个文件
     *
     * @param model
     */
    public void startDown(final DownModel model) {
        /*正在下载不处理*/
        if (model == null || subMap.get(model.getUrl()) != null) {
            subMap.get(model.getUrl()).DownModel(model);
            return;
        }
        /*添加回调处理类*/
        ProgressDownSubscriber subscriber = new ProgressDownSubscriber(model, handler);
        /*记录回调sub*/
        subMap.put(model.getUrl(), subscriber);
        /*获取service，多次请求公用一个sercie*/
        Api httpService;
        if (downModels.contains(model)) {
            httpService = model.getService();
        } else {
            /**
             * 加入日志监听器，会导致每次都把整个文件加载到内存，所以去掉请求日志监听
             */
            DownloadInterceptor interceptor = new DownloadInterceptor(subscriber);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(6, TimeUnit.SECONDS);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(ServerUrl.mServerUrl)
                    .build();
            httpService = retrofit.create(Api.class);
            model.setService(httpService);
            downModels.add(model);
        }
        /* 下载路径 */
        String currentPath;
        if (model.getPath() == null) {
            currentPath = getTemporaryName(model.getName());
            model.setPath(currentPath);
        } else {
            currentPath = model.getPath();
        }
        httpService.downLoad("bytes=" + model.getDownSize() + "-", model.getUrl())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetwork())
                .map(new Function<Response<ResponseBody>, InputStream>() {
                    @Override
                    public InputStream apply(Response<ResponseBody> responseBodyResponse) throws Exception {
                        return responseBodyResponse.body().byteStream();
                    }
                })
                .observeOn(Schedulers.computation()) // 用于计算任务
                .map(new Function<InputStream, DownModel>() {
                    @Override
                    public DownModel apply(InputStream inputStream) throws Exception {
                        if (model.getDownSize() != 0 && model.getTotalSize() != 0) {
                            writeFile(model.getDownSize(), inputStream, currentPath);
                        } else {
                            writeFile(inputStream, currentPath);
                        }
                        return model;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 生成临时文件名
     *
     * @param name
     * @return
     */
    private String getTemporaryName(String name) {
        String type = "";
        String plugin = "plugin_";
        if (name.contains(".")) {
            type = name.substring(name.lastIndexOf("."));
        }
        String dirName = mContext.getApplicationContext().getExternalFilesDir(null) + "/down/";

        File f = new File(dirName);
        //不存在创建
        if (!f.exists()) {
            f.mkdirs();
        }
        if (type.contains("apk")) {
            // 查询是否存在插件文件,存在就删除
            File file = new File(dirName + name);
            if (file.exists()) {
                file.delete();
            }
            return dirName + name;
        }
        return dirName + plugin + System.currentTimeMillis() + type;
    }


    /**
     * 暂停/取消任务
     *
     * @param model
     */
    public void pause(DownModel model) {
        if (model == null) return;
        model.setState(DownState.PAUSE.getState());
        model.getListener().onPause();
        if (subMap.containsKey(model.getUrl())) {
            ProgressDownSubscriber subscriber = subMap.get(model.getUrl());
            subscriber.getDisposable().dispose();
            subMap.remove(model.getUrl());
        }
        /* 信息写入到数据中 */
        DataHelperUtil.getInstance().write(model);
    }

    /**
     * 移除下载数据
     *
     * @param model
     */
    public void remove(DownModel model) {
        subMap.remove(model.getUrl());
        downModels.remove(model);
    }

    /**
     * 将输入流写入文件
     *
     * @param inputString 输入流
     * @param filePath    文件地址
     */
    private void writeFile(InputStream inputString, String filePath) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] fileReader = new byte[1024];

            int len;
            while ((len = inputString.read(fileReader)) != -1) {
                fos.write(fileReader, 0, len);
            }
            inputString.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 续写文件
     *
     * @param start       开始位置
     * @param inputString 文件流
     * @param filePath    文件地址
     * @return
     */
    public void writeFile(long start, InputStream inputString, String filePath) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            byte[] fileReader = new byte[1024];
            //移动到该位置
            raf.seek(start);

            int len;
            while ((len = inputString.read(fileReader)) != -1) {
                raf.write(fileReader, 0, len);
            }
            inputString.close();
            raf.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

