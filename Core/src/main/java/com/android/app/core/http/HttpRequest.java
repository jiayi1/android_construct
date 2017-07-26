package com.android.app.core.http;

import android.text.TextUtils;

import com.android.app.core.utils.FileUtil;
import com.android.app.core.utils.LogUtil;
import com.android.app.core.utils.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yulong.liu on 2016/12/16.
 */

public class HttpRequest {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    private static HttpRequest httpRequest;

    public static synchronized HttpRequest getInstence() {
        if (httpRequest == null) {
            synchronized (HttpRequest.class) {
                if (httpRequest == null) {
                    httpRequest = new HttpRequest();
                }
            }
        }
        return httpRequest;
    }

    private HttpRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logger);
        client = builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build();
    }

    /**
     * GET 请求
     *
     * @param url
     * @param callback
     */
    public void get(String url, CallBack callback) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 下载
     */
    public void downLoad(String url, final String path, final FileUtil.DownLoadListener downLoadListener) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Util.toast(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    InputStream is =  response.body().byteStream();
                    FileUtil.saveInputStream(is,path,downLoadListener);
                } else {
                    if(!TextUtils.isEmpty(response.message())){
                        Util.toast(response.message());
                    }
                }
            }
        });

    }

    /**
     * post 异步请求
     *
     * @param url
     * @param json
     * @param callBack
     */
    public void post(String url, String json, CallBack callBack) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callBack);
    }
}
