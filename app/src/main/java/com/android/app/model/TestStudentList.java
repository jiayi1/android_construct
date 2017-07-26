package com.android.app.model;

import com.android.app.core.http.BaseRes;

import java.util.List;
import java.util.Vector;

/**
 * Created by yulong.liu on 2016/12/28.
 */

public class TestStudentList extends BaseRes {
    private Vector<TestStudentModel> data;

    public Vector<TestStudentModel> getData() {
        return data;
    }

    public void setData(Vector<TestStudentModel> data) {
        this.data = data;
    }
}
