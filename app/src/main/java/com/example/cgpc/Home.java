package com.example.cgpc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Home extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<String> icons = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> dept= new ArrayList<String>();
    private ArrayList<String> cgpa= new ArrayList<String>();
    private ArrayList<String> date = new ArrayList<String>();
    private Button applyB ;


    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.home,container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        db.collection("company").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                names.clear();
                dept.clear();
                cgpa.clear();
                date.clear();
                icons.clear();
                for(QueryDocumentSnapshot document : queryDocumentSnapshots){

                    if(document.exists()){
                        names.add(document.getString("name"));
                        icons.add(document.getString("img"));
                        dept.add("LPA : "+document.getString("LPA"));
                        cgpa.add("CGPA: "+document.getString("CGPA"));
                        date.add("Date: "+document.getString("date"));

                        Log.d(TAG, document.getId() + "=>" +document.getData());
                    }
                    else {
                        Log.d(TAG,"failed");
                    }
                }
                createList();
            }
        });

        return view;


    }

    @Override
    public void onStart() {
        super.onStart();





    }
    void createList(){
        ItemData adapter = new ItemData(getActivity(),names,dept,cgpa,date,icons);
        listView.setAdapter(adapter);

    }
}
