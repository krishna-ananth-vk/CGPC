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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Home extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    Float ucgpa, bl;
    private FirebaseFirestore data = FirebaseFirestore.getInstance();
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dept = new ArrayList<>();
    private ArrayList<Float> cgpa = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private Button applyB ;


    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.home,container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        DocumentReference documentReference = data.collection("user").document(uid);
        final ListenerRegistration registration = documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    ucgpa = Float.parseFloat(documentSnapshot.getString("cgpa"));
                    System.out.println("test------------------");
                }
            }
        });


        db.collection("company").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                names.clear();
                dept.clear();
                cgpa.clear();
                date.clear();
                id.clear();
                for(QueryDocumentSnapshot document : queryDocumentSnapshots){

                    if(document.exists()){
                        names.add(document.getString("name"));
                        id.add(document.getId());
                        dept.add("LPA : "+document.getString("LPA"));
                        cgpa.add(Float.parseFloat(document.getString("gp")));

                    }
                    else {
                        Log.d(TAG,"failed");
                    }
                }
                createList();

                registration.remove();
            }
        });


        return view;


    }

    @Override
    public void onStart() {
        super.onStart();


    }
    void createList(){

        ItemData adapter = new ItemData(getActivity(), names, dept, cgpa, id, uid, ucgpa);

        listView.setAdapter(adapter);


    }
}
