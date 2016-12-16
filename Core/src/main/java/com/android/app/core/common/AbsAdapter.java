package com.android.app.core.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class AbsAdapter extends BaseAdapter {

    protected List<Object> mList  = new ArrayList<Object>();

    public void setDataList(List<Object> dataList ){
        checkListIsNull();
        mList.clear();
        mList.addAll(dataList);
    }

    private void checkListIsNull(){
        if(mList == null){
            mList = new ArrayList<Object>();
        }
    }

    @Override
    public int getCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
