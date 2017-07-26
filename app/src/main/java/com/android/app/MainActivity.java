package com.android.app;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.android.app.control.TestListActivity;
import com.android.app.core.common.BaseActivity;
import com.android.app.core.http.GsonCallBack;
import com.android.app.core.http.HttpRequest;
import com.android.app.core.utils.FileUtil;
import com.android.app.core.utils.LogUtil;
import com.android.app.model.ListTestModel;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.btn);
        tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
              //  downLoad();
              //  startActivity(new Intent(this, TestListActivity.class));
                insert();
            default:
        }
    }
    private void insert() {
        ContentResolver cp = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "liu");
        Uri uri = cp.insert(Uri.parse("content://www.com/yu"), values);
        LogUtil.i("info",uri.toString());
    }


    private void getData(){
        HttpRequest.getInstence().get("http://xxx", new GsonCallBack<ListTestModel>() {
            @Override
            protected void success(ListTestModel baseRes) {
                Log.i("info",baseRes.getData().size()+"");
            }

            @Override
            protected void error(Call c, IOException e) {

            }
        });
    }


}
