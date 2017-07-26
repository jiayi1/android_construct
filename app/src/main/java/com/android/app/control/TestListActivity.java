package com.android.app.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.R;
import com.android.app.core.common.AbsAdapter;
import com.android.app.core.control.ListBaseActivity;
import com.android.app.core.http.GsonCallBack;
import com.android.app.core.http.HttpRequest;
import com.android.app.core.utils.LogUtil;
import com.android.app.model.TestModel;
import com.android.app.model.TestStudentList;
import com.android.app.model.TestStudentModel;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import okhttp3.Call;

/**
 * Created by yulong.liu on 2016/12/17.
 */

public class TestListActivity extends ListBaseActivity {
    Vector<TestStudentModel> list ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        HttpRequest.getInstence().get("http://xxxx:8080/getList", new GsonCallBack<TestStudentList>() {
            @Override
            protected void success(final TestStudentList resonse) {
                if (resonse.isSuccess()) {
                    LogUtil.i("info",""+resonse.getData().size());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list  = resonse.getData();
                            mBaseAdapter.setDataList(list);

                        }
                    });
                }
            }

            @Override
            protected void error(Call c, IOException e) {

            }
        });



    }

    @Override
    protected AbsAdapter getAdapter() {
        return new TestAdapter();
    }

    class TestAdapter extends AbsAdapter {

        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if(convertView == null){
                holder = new Holder();
                convertView = mInflater.inflate(R.layout.item,null);
                holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            TestStudentModel item = (TestStudentModel) mContent.get(position);
            holder.tv.setText(item.getName()+"");
            return convertView;
        }
        class Holder{
            TextView tv;
        }
    }
}
