package com.hlub.dev.test_expandable_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListView extends BaseExpandableListAdapter {

    Context context;
    List<String>listHeader;
    HashMap<String,List<User>>listChilder;

    public CustomExpandableListView(Context context, List<String> listHeader, HashMap<String, List<User>> listChilder) {
        this.context = context;
        this.listHeader = listHeader;
        this.listChilder = listChilder;
    }

    //so luong phan tu
    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    //số lượng phần tử có trong group
    @Override
    public int getChildrenCount(int groupPosition) {
        return listChilder.get(listHeader.get(groupPosition)).size();
    }


    ///lấy object phần tử (1 phan tu)
    @Override
    public Object getGroup(int groupPosition) {
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childerPosition) {
        return listChilder.get(listHeader.get(groupPosition)).get(childerPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childerPosition) {
        return childerPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle= (String) getGroup(groupPosition);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.group_view,null);
        TextView tvHeader=view.findViewById(R.id.tvHeader);
        tvHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childerPosition, boolean b, View view, ViewGroup viewGroup) {
        User user= (User) getChild(groupPosition,childerPosition);
        int id= user.getId();
        String name= user.getName();
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.childer_view,null);
        TextView tvItemId=view.findViewById(R.id.tvChilderId);
        TextView tvItemName=view.findViewById(R.id.tvChilderName);
        tvItemId.setText(id+"");
        tvItemName.setText(name+"");

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childerPosition) {
        return true;
    }
}
