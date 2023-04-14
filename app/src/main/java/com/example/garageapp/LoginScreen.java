package com.example.garageapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    TextInputEditText enter_email, enter_password;
    ProgressBar progressBar;
    Button btn_login;
    TextView Register_First;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        enter_email = findViewById(R.id.enter_email);
        enter_password = findViewById(R.id.enter_password);
        progressBar = findViewById(R.id.progressBar);
        btn_login = findViewById(R.id.btn_login);
        Register_First = findViewById(R.id.RegisterFirst);
        mAuth = FirebaseAuth.getInstance();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email = String.valueOf(enter_email.getText());
                password = String.valueOf(enter_password.getText());
                progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginScreen.this, "Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginScreen.this, "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginScreen.this, "Login in completed.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);

                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(LoginScreen.this, "Register First or Check Email & Password  ",
                                            Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });

        Register_First.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}