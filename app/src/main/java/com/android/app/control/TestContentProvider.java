package com.android.app.control;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.android.app.App;

/**
 * Created by yulong.liu on 2016/12/30.
 */

public class TestContentProvider extends ContentProvider {

    private TestMyDatabase myDatabase;
    private SQLiteDatabase dbHelper;
    static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("wanduoduo.com", "liu", 1);
        uriMatcher.addURI("wanduoduo.com", "liu/#", 2);
        uriMatcher.addURI("wanduoduo.com", "liu/*", 3);
        uriMatcher.addURI("wanduoduo.com", "yu", 4);
        uriMatcher.addURI("wanduoduo.com", "yu/*", 5);
        uriMatcher.addURI("wanduoduo.com", "yu/*", 6);
    }

    @Override
    public boolean onCreate() {
        if (myDatabase == null) {
            myDatabase = new TestMyDatabase(App.appContext);
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        dbHelper = myDatabase.getWritableDatabase();
        int code = uriMatcher.match(uri);
        Cursor cursor;
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (code) {
            case 1:
                builder.setTables(TestMyDatabase.TABLE_NAME);
                break;
            case 2:
                builder.setTables(TestMyDatabase.TABLE_NAME2);
                break;
        }
        cursor = builder.query(dbHelper, projection,  selection, selectionArgs,null,"",sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int code = uriMatcher.match(uri);
        switch (code) {
            case 1:
                return "liu";
            case 2:
                return "vnd.android.cursor.dir/5";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        dbHelper = myDatabase.getWritableDatabase();
        int code = uriMatcher.match(uri);
        long id;
        switch (code) {
            case 1:
                id = dbHelper.insert(TestMyDatabase.TABLE_NAME, null, values);
                break;
            case 4:
                id = dbHelper.insert(TestMyDatabase.TABLE_NAME2, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        //通知其他应用有插入成功
      //  this.getContext().getContentResolver().notify();
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
