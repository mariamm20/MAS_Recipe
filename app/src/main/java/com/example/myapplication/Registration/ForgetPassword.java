package com.example.myapplication.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class ForgetPassword extends AppCompatActivity {

    Button reset_link_btn;
    TextView signup_txt2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        reset_link_btn = findViewById(R.id.reset_link_btn);
        reset_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                startActivity(intent);
            }
        });

        signup_txt2 = findViewById(R.id.signup_txt2);
        signup_txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ForgetPassword.this, Register.class);
                startActivity(intent2);
            }
        });
    }
}