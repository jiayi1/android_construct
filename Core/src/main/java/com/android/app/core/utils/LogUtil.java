package com.android.app.core.utils;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class LogUtil {

    private static boolean isLog  = true;
    public static void i(@NonNull String tag, @NonNull String msg){
        if(isLog){
            Log.i(tag,""+msg);
        }
    }

    public static void e(@NonNull String tag, @NonNull String e){
        if(isLog){
            Log.e(tag,e);
        }
    }
    public static void e(@NonNull String tag, @NonNull Exception e){
        if(isLog){
            Log.e(tag,e.getMessage(),e);
        }
    }

    public static void d(@NonNull String tag, @NonNull String msg){
        if(isLog){
            Log.i(tag,""+msg);
        }
    }
    public static void v(@NonNull String tag, @NonNull String msg){
        if(isLog){
            Log.v(tag,msg);
        }
    }
}
