package com.example.cgpc;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class EditProfile extends Activity {
    final Calendar calendar = Calendar.getInstance();
    Spinner dept, prg, gen;
    String dateStr;
    int y, m, d;
    TextView dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        dateStr = "31/01/1999";
        final SimpleDateFormat da = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        try {
            calendar.setTime(da.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        dept = findViewById(R.id.dept);
        prg = findViewById(R.id.prg);
        gen = findViewById(R.id.gen);

        // ------------academic details------------
        // ------------academic details------------


        // department

        ArrayAdapter<CharSequence> deptAdapter = ArrayAdapter.createFromResource(this, R.array.department, android.R.layout.simple_spinner_item);

        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dept.setAdapter(deptAdapter);

        //programme

        ArrayAdapter<CharSequence> prgAdapter = ArrayAdapter.createFromResource(this, R.array.programme, android.R.layout.simple_spinner_item);

        prgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        prg.setAdapter(prgAdapter);


        // -------------personal details------------
        // -------------personal details------------

        // gender
        ArrayAdapter<CharSequence> genAdapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);

        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gen.setAdapter(genAdapter);

        // dob
        dob = findViewById(R.id.dob);
        dob.setText(dateStr);


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                y = calendar.get(Calendar.YEAR);
                m = calendar.get(Calendar.MONTH);
                d = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog date = new DatePickerDialog(EditProfile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                dateStr = da.format(calendar.getTime());
                                dob.setText(dateStr);
                            }
                        }, y, m, d
                );
                date.show();
            }
        });


    }

}
