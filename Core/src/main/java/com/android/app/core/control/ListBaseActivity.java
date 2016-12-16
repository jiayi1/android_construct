package com.android.app.core.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.app.core.R;
import com.android.app.core.common.BaseActivity;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class ListBaseActivity extends BaseActivity {

    protected LinearLayout mFilterLayout;
    private ListView mlv;
    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        setUp();
        fillInFilterView();
    }

    private void setUp()  {
        mInflater = LayoutInflater.from(this);
        mFilterLayout = (LinearLayout) findViewById(R.id.filter_layout);
        mlv = (ListView) findViewById(R.id.post_lv);
    }

    private void fillInFilterView(){
        int i =1;
        switch (i){
            case 1:
                //fall through
        }
    }

}
