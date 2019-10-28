package com.example.cgpc;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Noti extends AppCompatActivity {
    ListView listView;
    private ActionBar toolbar;
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> content = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Notification");

        listView = findViewById(R.id.notification);
        title.add("Placement postponed");
        title.add("You registration has been cancelled");
        title.add("Placement postponed");
        content.add("We are sorry to inform you that placement drive scheduled on 20/10/2019 has been postponed.  ");
        content.add("It seems you violated the terms and conditions of placement cell by not attending the drive you registered for. And hereby CGPC cancelled your registration. To attend the future drives you are requested to re-register");
        content.add("Placement postponed");

        createList();
    }


    void createList() {
        ItemDataNoti adapter = new ItemDataNoti(this, title, content);
        listView.setAdapter(adapter);

        Log.d(TAG, "pass");

    }
}
