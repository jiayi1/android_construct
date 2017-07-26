package com.android.app.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.app.core.App;
import com.android.app.core.R;
import com.android.app.core.model.Node;
import com.android.app.core.model.NodeChild;
import com.android.app.core.utils.Util;


import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by yulong.liu on 2016/12/17.
 */

public class QuickFilterView extends LinearLayout implements FilterPopWindow.onFilterListener {

    private LayoutInflater mInflater;
    private FilterSelectListener mFilterSelectlistener;

    private int MAXCOLMECOUNT = 4;
    private boolean isShowMoreBtn;

    private List<Node> mTempFilterList;

    HashMap<String ,Vector<NodeChild>> map = new HashMap<String ,Vector<NodeChild>>();

    private Vector<NodeChild> mSelectedNodeList = new Vector<>();

    public void setFilterSelectListener(FilterSelectListener l){
        this.mFilterSelectlistener = l;
    }
    public QuickFilterView(Context context) {
        this(context,null);
    }

    public QuickFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
    }

    public void fillWithFilterView(List<Node> list){
        mTempFilterList = list;
        formatFilter();
        int size = list.size();
        for (int i = 0;i<size;i++){
            Node node= list.get(i);
            View filterView = mInflater.inflate(R.layout.filter_item,null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            filterView.setLayoutParams(layoutParams);
            TextView filterName = (TextView) filterView.findViewById(R.id.filter_item_btn);
            filterName.setText(node.getFilterName());
            filterView.setTag(node.getFilterKey());
            filterView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = (String) v.getTag();
                    showPop(key);
                }
            });
            addView(filterView);
            if((i+1) < MAXCOLMECOUNT){
                addDevideLine();
            }
            if((MAXCOLMECOUNT- i) ==2){
                addMoreBtn();
                break;
            }
        }
    }

    private void formatFilter(){
        for (int i = 0;i< mTempFilterList.size();i++){
            Node node = mTempFilterList.get(i);
            map.put(node.getFilterKey(),node.getChildNodeList());
        }
    }

    public void updateFilterConditionParmas(){

    }

    private FilterPopWindow popWindow;

    private void addMoreBtn(){
        View moreBtnView = mInflater.inflate(R.layout.filter_item,null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        moreBtnView.setLayoutParams(layoutParams);
        TextView moreBtn = (TextView) moreBtnView.findViewById(R.id.filter_item_btn);
        moreBtn.setText("筛选");
        addView(moreBtnView);
        moreBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFilterSelectlistener!= null){
                    mFilterSelectlistener.openMoreOptionActivity();
                }
            }
        });
    }

    private void showPop(String key){
        if (popWindow == null) {
            popWindow = new FilterPopWindow(getContext());
            popWindow.setItemFilterListener(this);
        }
        Vector<NodeChild> list = map.get(key);
        popWindow.setFilterData(list);
        popWindow.showAsDrop(this);
    }

    private void addDevideLine(){
        View v = new View(App.appContext);
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.width = 1;
        v.setLayoutParams(params);
        v.setBackgroundColor(getResources().getColor(R.color.light_black));
        addView(v);
    }

    @Override
    public void onFilterClick(NodeChild nodeChild) {
        if(!mSelectedNodeList.contains(nodeChild)){
            mSelectedNodeList.add(nodeChild);
        }else {
            for (int i =0; i< mSelectedNodeList.size();i++){
                NodeChild item = mSelectedNodeList.get(i);
                if(item.getNodeChildKey().equals(nodeChild.getNodeChildKey())){
                    item.setNodeChildValue(nodeChild.getNodeChildValue());
                }
            }
        }
        mFilterSelectlistener.selectNode(mSelectedNodeList);

        Util.toast("您点击了:"+nodeChild.getNodeName());
    }

    @Override
    public void onFilterChanged(NodeChild nodeChild) {
        mFilterSelectlistener.selectNode(mSelectedNodeList);
    }

    public interface FilterSelectListener{
        void selectNode(Vector<NodeChild> nodes);
        void openMoreOptionActivity();
    }
}
