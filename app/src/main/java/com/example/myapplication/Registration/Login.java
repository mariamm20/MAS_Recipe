package com.example.myapplication.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Home.Home;
import com.example.myapplication.R;
import com.example.myapplication.WelcomeScreens.Onboarding;

public class Login extends AppCompatActivity {

    Button login_btn;
    TextView signup_txt, forgot_password_txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Onboarding.class);
                startActivity(intent);
            }
        });

        signup_txt = findViewById(R.id.signup_txt);
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Login.this, Register.class);
                startActivity(intent2);
            }
        });

        forgot_password_txt = findViewById(R.id.forgot_password_txt);
        forgot_password_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent2);
            }
        });


    }
}