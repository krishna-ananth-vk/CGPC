package com.example.cgpc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.textfield.TextInputEditText;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Load extends Activity {
    TextInputEditText email;
    TextInputEditText password;
    TextView login;
    String e, p;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);


    }

    @Override
    protected void onStart() {
        super.onStart();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
        }


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e = email.getText().toString();
                p = password.getText().toString();
                if (TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
                    Toast.makeText(Load.this, "email or password not provided",
                            Toast.LENGTH_LONG).show();
                } else {
                    signInUser(e, p);
                    login.setText("Logging in");
                    login.setEnabled(false);

                }

            }
        });

    }

    void signInUser(String e, String p) {
        final ProgressDialog progressDialog = new ProgressDialog(Load.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            login.setEnabled(true);
                            login.setText("Logging in");
                            startActivity(new Intent(Load.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            login.setEnabled(true);
                            login.setText("Logging in");
                            progressDialog.cancel();
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Load.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
