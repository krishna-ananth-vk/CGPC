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



import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;


import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ItemData  extends ArrayAdapter {
    String uid;
    Float ucgps;
    String TAG = "Data------------------------------------------------";
    private Activity context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> names;
    private ArrayList<String> id;
    private ArrayList<Float> cgpa;
    private ArrayList<String> students;
    private boolean[] flag;


    ItemData(Activity context, ArrayList<String> name, ArrayList<String> dept, ArrayList<Float> cgpa, ArrayList<String> id, String uid, Float ucgpa) {
        super(context,R.layout.list_item,name);
        this.context = context;
        this.uid = uid;
        this.ucgps = ucgpa;
        this.names = name;
        this.cgpa = cgpa;
        this.id = id;
        flag = new boolean[id.size()];



    }
    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item,null,true);
        flag[position] = false;

        final DocumentReference documentReference = db.collection("company").document(this.id.get(position));

        TextView title = rowView.findViewById(R.id.title);
        ImageView icon = rowView.findViewById(R.id.icon);
        TextView dept = rowView.findViewById(R.id.backlog);
        final TextView msg = rowView.findViewById(R.id.msg);
        final TextView CGPA = rowView.findViewById(R.id.cgpa);
        final Button applyB = rowView.findViewById(R.id.apply);
        System.out.println("========================" + cgpa.get(position) + "-----" + ucgps);
        if (cgpa.get(position) > ucgps) {
            applyB.setEnabled(false);
            msg.setText("Required CGPA is not met");

        }

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    students = (ArrayList<String>) documentSnapshot.get("student");
                    if (students.contains(uid)) {
                        applyB.setText("Registerd");
                        applyB.setEnabled(false);
                        Log.d(TAG, ucgps + "  --  " + cgpa.get(position) + "----" + id.get(position));
                    } else {
                        applyB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                documentReference.update("student", FieldValue.arrayUnion(uid));
                            }
                        });
                    }


                }
            }
        });

        title.setText(this.names.get(position));
//        dept.setText(this.dept.get(position));
        CGPA.setText("CGPA : " + this.cgpa.get(position));
        return rowView;
    }


}

