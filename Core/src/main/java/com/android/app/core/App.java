package com.android.app.core;

import android.app.Application;
import android.content.Context;


/**
 * Created by yulong.liu on 2016/12/15.
 */

public class App extends Application {

    public static Context appContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        appContext = this;
        Envi.initAppParmas();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
