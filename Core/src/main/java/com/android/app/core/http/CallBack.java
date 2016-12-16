package com.android.app.core.http;


import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by yulong.liu on 2016/12/16.
 */

public abstract class CallBack<T> implements Callback {

    @Override
    public void onFailure(Call call, IOException e) {
        error(call,e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()){
            T t = null;
            try {
                t = parse(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            success(t);
        }
    }

    protected abstract T parse(String  body);

    protected abstract void success(T t);

    protected abstract void error(Call c,IOException e);

}
