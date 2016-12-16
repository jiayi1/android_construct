package com.android.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.app.core.common.BaseActivity;
import com.android.app.core.http.GsonCallBack;
import com.android.app.core.http.HttpRequest;
import com.android.app.model.ListTestModel;

import java.io.IOException;

import okhttp3.Call;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.btn);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                getData();
                break;
            default:
        }
    }

    private void getData(){
        HttpRequest.getInstence().get("http://mapi.test.wanduoduo.com/suid/scanStatistics", new GsonCallBack<ListTestModel>() {
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
