package com.example.cgpc;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Notification extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> content = new ArrayList<String>();
    private  ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.notification,container, false);
        listView = (ListView) view.findViewById(R.id.notification);
        db.collection("notification").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                title.clear();
                for(QueryDocumentSnapshot document : queryDocumentSnapshots){

                    if(document.exists()){
                        title.add(document.getString("title"));
                        //content.add(document.getString("content"));


                        Log.d(TAG, document.getId() + "=>" +document.getString("title"));
                    }
                    else {
                        Log.d(TAG,"failed");
                    }
                }
                createList();
            }
        });
        return inflater.inflate(R.layout.notification, null);
    }
    void  createList(){
        ItemDataNoti adapter = new ItemDataNoti(getActivity(),title,content);
        listView.setAdapter(adapter);

        Log.d(TAG, "pass");

    }
}
