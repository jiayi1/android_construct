package com.android.app.model;

import com.android.app.core.http.BaseRes;

/**
 * Created by yulong.liu on 2016/12/28.
 */

public class TestStudentModel extends BaseRes {

    /**
     * id : 0
     * name :
     * age : 9
     * addr :
     * num : 1
     */

    private int id;
    private String name;
    private int age;
    private String addr;
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
