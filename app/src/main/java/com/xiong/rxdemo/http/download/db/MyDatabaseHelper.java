package com.xiong.rxdemo.http.download.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * @author: xiong
 * @time: 2022/08/11
 * @说明:
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {


    public static final String CREATE_DOWNLOAD = "create table DownLoad(businessName text primary key," +
            "url text,path text,name text,version integer,downSize text,totalSize text," +
            "state integer,percent integer)";


    public MyDatabaseHelper(Context context, int version) {
        super(context, getMyDatabaseName(context), null, version);
    }

    private static String getMyDatabaseName(Context context) {
        String data = "DownLoadData.db";
        File file = context.getDatabasePath("xxx");
        String dbPath = file.getPath() + "/";
        File dbp = new File(dbPath);
        if (!dbp.exists()) {
            dbp.mkdirs();
        }
        data = dbPath + data;
        return data;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOWNLOAD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 在onUpgrade()方法中先使用drop语句如果已经存在表，就把表删掉，因为数据库已经存在了，onCreate()方法怎么样都不会再执行的
        db.execSQL("drop table if exists DownLoad");
        onCreate(db);
    }
}
