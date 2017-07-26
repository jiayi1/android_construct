package com.android.app.core.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Vector;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public abstract class AbsAdapter<T> extends BaseAdapter {

    protected Vector<T> mContent = new Vector<T>();

    public void setDataList(Vector<T> dataList) {
        checkListIsNull();
        mContent.clear();
        if(dataList!= null){
            mContent.addAll(dataList);
            notifyDataSetChanged();
        }

    }

    public void addContent(Vector<T> addList){
        checkListIsNull();
        if (addList != null) {
            mContent.addAll(addList);
        }
    }
    private void checkListIsNull() {
        if (mContent == null) {
            mContent = new Vector<T>();
        }
    }

    public Vector<T> getContent(){
        return mContent;
    }

    @Override
    public int getCount() {
        if (mContent != null) {
            return mContent.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (mContent != null) {
            return mContent.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
