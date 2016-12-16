package com.android.app.core.http;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class GsonCallBack<T> extends CallBack<T> {
    private Type type;
    private Gson gson = new Gson();


    public GsonCallBack(){
        type =getSuperclassTypeParameter(getClass());
    }

    @Override
    protected T parse(String body) {
        T t = gson.fromJson(body,type );
        return t;
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

}
