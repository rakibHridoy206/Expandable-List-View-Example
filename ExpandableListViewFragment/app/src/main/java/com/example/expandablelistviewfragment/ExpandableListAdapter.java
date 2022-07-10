package com.example.expandablelistviewfragment;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> listDataHeader;
    private final HashMap<String, List<String>> listHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Objects.requireNonNull(listHashMap.get(listDataHeader.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(listHashMap.get(listDataHeader.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        TextView lvListHeader = view.findViewById(R.id.groupNameTV);
        lvListHeader.setTypeface(null, Typeface.BOLD);
        lvListHeader.setText(headerTitle);
        AppCompatImageView img = view.findViewById(R.id.groupImage);
        img.setSelected(isExpanded);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String)getChild(groupPosition,childPosition);
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        TextView textChild = view.findViewById(R.id.lvListItem);
        textChild.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
