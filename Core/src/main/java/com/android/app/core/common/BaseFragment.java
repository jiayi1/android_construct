package com.android.app.core.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public abstract class BaseFragment extends Fragment{
    protected View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(getLayoutViewId(),container,false);
        setUp();
        return baseView;
    }

    protected abstract int getLayoutViewId();

    protected abstract void setUp();

}
