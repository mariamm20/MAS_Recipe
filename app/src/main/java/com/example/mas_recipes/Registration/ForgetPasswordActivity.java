package com.example.mas_recipes.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mas_recipes.R;

public class ForgetPasswordActivity extends AppCompatActivity {

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
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signup_txt2 = findViewById(R.id.forget_pass_signup_txt);
        signup_txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ForgetPasswordActivity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }
}