package com.xiong.rxdemo.http.download.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiong.rxdemo.http.download.DownModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiong
 * @time: 2022/08/11
 * @说明:
 */
public class DataHelperUtil {

    private static String TAG = "DataHelperUtil";
    private static DataHelperUtil instance;

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static DataHelperUtil getInstance() {
        if (instance == null) {
            instance = new DataHelperUtil();
        }
        return instance;
    }

    public void init(Context context) {
        try {
            if (dbHelper == null) {
                dbHelper = new MyDatabaseHelper(context, 1000);
                db = dbHelper.getWritableDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        db.close();
    }

    public void write(DownModel downModel) {
        ContentValues values = new ContentValues();
        values.put("businessName", downModel.getBusinessName());
        values.put("url", downModel.getUrl());
        values.put("path", downModel.getPath());
        values.put("name", downModel.getName());
        values.put("version", downModel.getVersion());
        values.put("downSize", String.valueOf(downModel.getDownSize()));
        values.put("totalSize", String.valueOf(downModel.getTotalSize()));
        values.put("state", downModel.getState());
        values.put("percent", downModel.getPercent());
        db.replace("DownLoad", null, values);
    }


    public void delete(String businessName) {
        db.delete("DownLoad", "businessName = ?", new String[]{businessName});
    }


    public List<DownModel> selectAll() {
        Cursor cursor = db.query("DownLoad", null, "businessName is not null", null, null, null, "time desc");
        if (null != cursor) {
            List<DownModel> data = new ArrayList<>();
            while (cursor.moveToNext()) {
                DownModel downModel = new DownModel();
                downModel.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                downModel.setPath(cursor.getString(cursor.getColumnIndex("path")));
                downModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                downModel.setBusinessName(cursor.getString(cursor.getColumnIndex("businessName")));
                downModel.setVersion(cursor.getInt(cursor.getColumnIndex("version")));
                downModel.setDownSize(Long.parseLong(cursor.getString(cursor.getColumnIndex("downSize"))));
                downModel.setTotalSize(Long.parseLong(cursor.getString(cursor.getColumnIndex("totalSize"))));
                downModel.setState(cursor.getInt(cursor.getColumnIndex("state")));
                downModel.setPercent(cursor.getInt(cursor.getColumnIndex("percent")));
                data.add(downModel);
            }
            cursor.close();
            return data;
        }
        return null;
    }

    public DownModel selectBusinessName(String businessName) {
        Cursor cursor = db.query("DownLoad", null, "businessName = ?", new String[]{businessName}, null, null, null);
        if (null != cursor) {
            if (cursor.moveToNext()) {
                DownModel downModel = new DownModel();
                downModel.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                downModel.setPath(cursor.getString(cursor.getColumnIndex("path")));
                downModel.setName(cursor.getString(cursor.getColumnIndex("name")));
                downModel.setBusinessName(businessName);
                downModel.setVersion(cursor.getInt(cursor.getColumnIndex("version")));
                downModel.setDownSize(Long.parseLong(cursor.getString(cursor.getColumnIndex("downSize"))));
                downModel.setTotalSize(Long.parseLong(cursor.getString(cursor.getColumnIndex("totalSize"))));
                downModel.setState(cursor.getInt(cursor.getColumnIndex("state")));
                downModel.setPercent(cursor.getInt(cursor.getColumnIndex("percent")));
                return downModel;
            }
            cursor.close();
        }
        return null;
    }

}
