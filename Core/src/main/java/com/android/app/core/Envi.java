package com.android.app.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class Envi {

    private static final String DEFAULT_DEVICEID ="000000";

    public static int APP_HEIGHT;
    public static int APP_WIDTH;
    public static float APP_DENSITY;

    public static int APP_VERSION_CODE;
    public static String APP_VERSION_NAME;
    public static String APP_DEVICEID;

    /**
     * 初始化app信息
     */
    public static void initAppParmas(){
        DisplayMetrics dm = App.appContext.getResources().getDisplayMetrics();
        APP_HEIGHT = dm.heightPixels;
        APP_WIDTH = dm.widthPixels;
        APP_DENSITY = dm.density;
        try {
            PackageManager manager = App.appContext.getPackageManager();
            PackageInfo info =  manager.getPackageInfo(App.appContext.getPackageName(),0);
            APP_VERSION_CODE = info.versionCode;
            APP_VERSION_NAME = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        TelephonyManager telephonyManager = (TelephonyManager) App.appContext.getSystemService(Context.TELEPHONY_SERVICE);
        if(!TextUtils.isEmpty(APP_DEVICEID)){
            APP_DEVICEID = telephonyManager.getDeviceId();
        }else {
            APP_DEVICEID = DEFAULT_DEVICEID;
        }
    }
}
