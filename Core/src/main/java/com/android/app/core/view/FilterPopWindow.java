package com.android.app.core.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.app.core.Envi;
import com.android.app.core.R;
import com.android.app.core.common.AbsAdapter;
import com.android.app.core.model.NodeChild;
import com.android.app.core.utils.Util;

import java.util.Vector;

/**
 * Created by yulong.liu on 2016/12/17.
 */

public class FilterPopWindow extends PopupWindow implements AdapterView.OnItemClickListener {

    private Vector<NodeChild> mDataList;

    private LayoutInflater mInflater;

    private onFilterListener listener;

    private FilterAdapter adapter;
    private ListView mlv;

    public void setFilterData(Vector<NodeChild> list){
        this.mDataList = list;
    }

    public FilterPopWindow(Context context) {
        setHeight(400);
        setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       // setFocusable(true);
        setOutsideTouchable(true);
        this.update();
        setBackgroundDrawable(new ColorDrawable());
        mInflater = LayoutInflater.from(context);
        View view  = mInflater.inflate(R.layout.filter_content_layout,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.height = (int)(Envi.APP_HEIGHT*0.7);
        setContentView(view);
        mlv = (ListView) view.findViewById(R.id.filter_lv);
        mlv.setLayoutParams(params);
        adapter = new FilterAdapter();
        mlv.setAdapter(adapter);
        mlv.setOnItemClickListener(this);

    }

    public void setItemFilterListener(onFilterListener l){
        this.listener = l;
    }


    public void showAsDrop(View anchor){
        if(anchor != null){
            adapter.setDataList(mDataList);
            mlv.smoothScrollToPosition(0);
            showAsDropDown(anchor,0,0);
        }
    }


    class FilterAdapter extends AbsAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if(convertView == null){
                holder = new Holder();
                convertView = mInflater.inflate(R.layout.filter_content_lv_item,null);
                holder.itemView = (TextView) convertView.findViewById(R.id.filter_content_lv_item_tv);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }
            NodeChild node = (NodeChild) mContent.get(position);
            holder.itemView.setText(node.getNodeName());
            return convertView;
        }

        class Holder{
            TextView itemView;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NodeChild nodeChild = mDataList.get(position);
        if(listener != null){
            listener.onFilterChanged(nodeChild);
            listener.onFilterClick(nodeChild);
            dismiss();
        }
    }

    public interface onFilterListener{
        void onFilterClick(NodeChild nodeChild);
        void onFilterChanged(NodeChild nodeChild);
    }

}
