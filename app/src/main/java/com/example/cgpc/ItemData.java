package com.example.cgpc;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ItemData  extends ArrayAdapter {
    private Activity context;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> dept= new ArrayList<String>();
    private ArrayList<String> cgpa= new ArrayList<String>();
    private ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> icons = new ArrayList<>();
    StorageReference storageReference;


    ItemData(Activity context,ArrayList<String> name, ArrayList<String> dept, ArrayList<String> cgpa, ArrayList<String> date,ArrayList<String> icons) {
        super(context,R.layout.list_item,name);
        this.context = context;
        this.names = name;
        this.icons = icons;
        this.cgpa = cgpa;
        this.dept = dept;
        this.date = date;
        storageReference = FirebaseStorage.getInstance().getReference();

    }
    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item,null,true);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        ImageView icon = rowView.findViewById(R.id.icon);
        TextView dept = (TextView) rowView.findViewById(R.id.dept);
        TextView cgpa = (TextView) rowView.findViewById(R.id.cgpa);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        Button applyB = (Button) rowView.findViewById(R.id.apply);
        applyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG ,"you registered for " + names.get(position) );
            }
        });

        title.setText(this.names.get(position));
        dept.setText(this.dept.get(position));
        cgpa.setText(this.cgpa.get(position));
        date.setText(this.date.get(position));


        storageReference.child(this.icons.get(position));
        //Glide.with(this.context).load(storageReference).into(icon).onResourceReady();


        Log.d(TAG,this.icons.get(position));





        return rowView;
    }
    void getImage(){

    }

}

