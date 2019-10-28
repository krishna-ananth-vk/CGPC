package com.example.cgpc;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class GradeListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> title;
    private HashMap<String, List<String>> listItems;

    public GradeListAdapter(Context context, List<String> title, HashMap<String, List<String>> listItems) {
        this.context = context;
        this.title = title;
        this.listItems = listItems;

    }

    @Override
    public Object getChild(int listposition, int expandedListPosition) {
        return this.listItems.get(this.title.get(listposition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listposition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grade_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expanded_item);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.listItems.get(this.title.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.title.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.title.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
