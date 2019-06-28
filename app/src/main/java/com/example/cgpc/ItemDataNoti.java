package com.example.cgpc;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ItemDataNoti extends ArrayAdapter {
    private Activity context;
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> content= new ArrayList<String>();


    ItemDataNoti(Activity context, ArrayList<String> title, ArrayList<String> content) {
        super(context,R.layout.simple_list,title);
        Log.d(TAG ,"pass1" );
        this.context = context;
        this.title = title;
        this.content = content;

    }

    public View getView(final int position, View view, ViewGroup parent){
        Log.d(TAG ,"pass2" );
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.simple_list,null,true);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView content = (TextView) rowView.findViewById(R.id.content);
        Log.d(TAG ,"pass3" );

        title.setText(this.title.get(position));
        content.setText(this.content.get(position));
        Log.d(TAG ,this.content.get(position) );



        return rowView;
    }
}
