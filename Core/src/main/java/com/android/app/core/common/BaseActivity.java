package com.android.app.core.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
/**
 *
 *----------Dragon be here!----------/
 * 　　 ┏┓　 ┏┓
 * 　　┏┛┻━━━┛┻━┓
 * 　　┃　　　　 ┃
 * 　　┃　　 ━　 ┃
 * 　　┃　┳┛　┗┳ ┃
 * 　　┃　　　　　┃
 * 　　┃　　 ┻　　┃
 * 　　┃　　　　　┃
 * 　　┗━┓　 　┏━┛
 * 　　　┃　　 ┃神兽保佑
 * 　　　┃　　 ┃代码无BUG！
 * 　　　┃　　 ┗━━┓
 * 　　　┃　　　　 ┣┓
 * 　　　┃　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛
 * 　　　 ┃┫┫ ┃┫┫
 * 　　 　┗┻┛ ┗┻┛
 *  ━━━━━━神兽出没━━━━━━by:coder-pig
* Created by yulong.liu on 2016/12/15.
 */

public abstract class BaseActivity extends FragmentActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
