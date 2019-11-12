package com.zhouzhou.demo.task.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.zhouzhou.demo.task.DBHelper.MyDBHelper;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.zhouzhou.demo.task.provider";
    private static final int USER_CODE = 1;
    private static final int JOB_CODE = 2;

    private Context mContexxt = null;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db = null;
    private static UriMatcher uriMatcher = null;


    public MyContentProvider() {
    }

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "employees", USER_CODE);
        uriMatcher.addURI(AUTHORITY, "jobs", JOB_CODE);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(getTableName(uri), selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER_CODE:
                return "vnd.android.cursor.dir/vnd.com.zhouzhou.demo.task.provider.employees";
            case JOB_CODE:
                return "vnd.android.cursor.dir/vnd.com.zhouzhou.demo.task.provider.jobs";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.insert(getTableName(uri), null, values);
        Log.d("zhouzhou", getTableName(uri) + "");
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public boolean onCreate() {
        mContexxt = getContext();
        myDBHelper = new MyDBHelper(mContexxt, "man.db", null, 3);
        db = myDBHelper.getReadableDatabase();
        Log.d("zhozho", "434");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(getTableName(uri), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        return db.update(getTableName(uri), values, selection, selectionArgs);
    }

    private String getTableName(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER_CODE:
                return "employees";
            case JOB_CODE:
                return "jobs";
            default:
                break;
        }
        return null;
    }
}
