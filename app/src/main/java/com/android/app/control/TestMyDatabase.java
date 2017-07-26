package com.android.app.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yulong.liu on 2016/12/30.
 */

public class TestMyDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME="test.db";

    public static final String TABLE_NAME="test";
    public static final String TABLE_NAME2="test2";

    private static int DB_VERSION= 2;

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+" (_id integer primary key autoincrement ,name varchar(30));";
    private static final String CREATE_TABLE2 = "create table "+TABLE_NAME2+" (_id integer primary key autoincrement ,name varchar(30));";
    public TestMyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("drop table  if exists "+ TABLE_NAME);
            db.execSQL("drop table  if exists "+ TABLE_NAME2);
            onCreate(db);
        }
    }
}
