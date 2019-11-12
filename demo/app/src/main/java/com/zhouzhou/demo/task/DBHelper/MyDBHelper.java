package com.zhouzhou.demo.task.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * author : ZhouZhou
 * e-mail : zhou.zhou@sim.com
 * date   : 19-11-11下午1:33
 * desc   :
 * version: 1.0
 */
public class MyDBHelper extends SQLiteOpenHelper {

    private static String USER_TABLE_NAME = "employees";
    private static String JOB_TABLE_NAME = "jobs";
    private Context mContext;

    public MyDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+USER_TABLE_NAME+" ("+" id Integer primary key autoincrement,"+" name text)";
        db.execSQL(sql);
        String sq = "create table jobs ("+" id Integer primary key autoincrement,"+" job text)";
//        lod
        Log.d("zhozho","yyg");
        db.execSQL(sq);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists USER_TABLE_NAME");
        db.execSQL("drop table if exists JOB_TABLE_NAME");
        onCreate(db);
    }
}
