package com.android.app;

import com.android.app.core.utils.FileUtil;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class App extends com.android.app.core.App implements Thread.UncaughtExceptionHandler{

    private Thread.UncaughtExceptionHandler defaultExceptionHandler ;

    @Override
    public void onCreate() {
        super.onCreate();
        defaultExceptionHandler =Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
//        LogUtil.e("info",throwable.getMessage());
//        LogUtil.e("info",throwable.getCause()+"");
//        throwable.printStackTrace();
//        LogUtil.e("info",throwable.toString());
        FileUtil.saveTextToFile(throwable.toString()+","+ thread.getName(),"/crash.txt");
        defaultExceptionHandler.uncaughtException(thread,throwable);

    }
}
