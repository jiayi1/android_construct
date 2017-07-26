package com.android.app.core.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.app.core.R;
import com.android.app.core.common.AbsAdapter;
import com.android.app.core.common.BaseActivity;
import com.android.app.core.model.Node;
import com.android.app.core.model.NodeChild;
import com.android.app.core.utils.LogUtil;
import com.android.app.core.view.QuickFilterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class ListBaseActivity extends BaseActivity implements QuickFilterView.FilterSelectListener {

    protected QuickFilterView mFilterLayout;
    private ListView mlv;
    protected LayoutInflater mInflater;
    protected AbsAdapter mBaseAdapter;

    private List<Node> list = new ArrayList<Node>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        setUp();
        fillInFilterView();
    }

    private void setUp() {
        mInflater = LayoutInflater.from(this);
        mFilterLayout = (QuickFilterView) findViewById(R.id.filter_layout);
        mFilterLayout.setVisibility(View.VISIBLE);
        mFilterLayout.setFilterSelectListener(this);
        mlv = (ListView) findViewById(R.id.post_lv);
        mBaseAdapter = getAdapter();
        if (mBaseAdapter != null) {
            mlv.setAdapter(mBaseAdapter);
        }
    }

    protected AbsAdapter getAdapter(){
        return null;
    };

    private void fillInFilterView() {
        for (int i = 0; i < 4; i++) {
            Node node = new Node();
            if (i == 0) {
                node.setFilterKey("list0");
                node.setFilterName("地点");
                setNodeChild(node,"aaa");
            }
            if (i == 1) {
                node.setFilterKey("list1");
                node.setFilterName("价格");
                setNodeChild(node,"bbb");
            }
            if (i == 2) {
                node.setFilterKey("list2");
                node.setFilterName("销售");
                setNodeChild(node,"ccc");
            }
            if (i == 3) {
                node.setFilterKey("list3");
                node.setFilterName("配送");
                setNodeChild(node,"aaa");
            }
            list.add(node);
        }
        mFilterLayout.fillWithFilterView(list);
    }

    private void setNodeChild(Node node, String ii) {
        Vector<NodeChild> list =  new Vector<NodeChild>();
        for (int i = 0; i < 15; i++) {
            NodeChild nodeChild = new NodeChild();
            nodeChild.setNodeName(ii+"," + i);
            nodeChild.setNodeChildKey(ii+i);
            list.add(nodeChild);
        }
        node.setChildNodeList(list);
    }

    @Override
    public void selectNode(Vector<NodeChild> nodes) {
        for (int i = 0;i< nodes.size();i++){
            NodeChild child = nodes.get(i);
            LogUtil.i("add","您的选择条件是: "+child.getNodeName());
        }
    }

    @Override
    public void openMoreOptionActivity() {

    }
}
