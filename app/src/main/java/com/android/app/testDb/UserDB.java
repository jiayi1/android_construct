package com.android.app.testDb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.app.core.db.BaseORMModel;
import com.android.app.core.db.Colume;
import com.android.app.core.db.DataBase;
import com.android.app.core.db.Table;

import java.util.ArrayList;

/**
 * Created by yulong.liu on 2016/12/14.
 */

public class UserDB extends DataBase {

    @Override
    protected ArrayList<Class<? extends BaseORMModel>> getTables() {
        ArrayList<Class<? extends BaseORMModel>> list = new ArrayList<Class<? extends BaseORMModel>>();
        list.add(Entity.class);
        list.add(EntityTwo.class);
        return list;
    }

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public String getDBName() {
        return "user.db";
    }

    @Table(name = "personal")
    public static class Entity extends BaseORMModel {


        @Colume(name = "name")
        public String useName;

        @Colume(name = "addr")
        public String addr;
    }


    @Table(name = "class")
    public static class EntityTwo extends BaseORMModel {

        @Colume(name = "className")
        String useName;

        @Colume(name = "num")
        String addr;

        @Colume(name = "sex")
        Integer  age;
    }

    public void insert(String name,String addr){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("addr",addr);
        sqlDataBase.getWritableDatabase("123456").insert("personal",null,values);
    }
    public ArrayList<Entity> queryAll(){
        ArrayList<Entity> list = query(Entity.class);
        if (list!= null) {
            for (Entity en :list){
                Log.i("info",en.useName);
            }
            return list;
        }
        return null;
    }

    protected void upgrageDB(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            Log.i("info","drop table liu if exists");
        }
    }
}
