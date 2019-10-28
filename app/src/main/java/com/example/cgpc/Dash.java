package com.example.cgpc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Dash extends Fragment {
    private TextView name, dept, gender, mt, dob, prg, cgpa;
    private CardView editBtn, grades;
    String uid;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        View view  = inflater.inflate(R.layout.dash,container, false);
        editBtn = view.findViewById(R.id.editbtn);
        name = view.findViewById(R.id.name);
        dept = view.findViewById(R.id.dept);
        gender = view.findViewById(R.id.gender);
        mt = view.findViewById(R.id.mt);
        dob = view.findViewById(R.id.dob);
        cgpa = view.findViewById(R.id.cgpa);
        prg = view.findViewById(R.id.prg);
        CardView logout = view.findViewById(R.id.logout);
        grades = view.findViewById(R.id.grade);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(getActivity(), Load.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                getActivity().finish();


            }
        });
        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Grades.class));
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });

        uid = user.getUid();
        getUserInfo();

        return view;
    }
    void getUserInfo(){
        final DocumentReference docRef = db.collection("user").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name.setText(document.getString("Name"));
                        dept.setText(document.getString("dept"));
                        gender.setText(document.getString("gender"));
                        dob.setText(document.getString("dob"));
                        cgpa.setText(document.getString("cgpa"));
                        prg.setText(document.getString("program"));
                        mt.setText(document.getString("mt"));

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
