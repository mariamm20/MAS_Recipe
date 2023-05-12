package com.example.myapplication.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Home.Home;
import com.example.myapplication.R;
import com.example.myapplication.WelcomeScreens.Onboarding;

public class Login extends AppCompatActivity {

    Button login_btn;
    EditText email, password;
    TextView signup_txt, forgot_password_txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  String emailText = email.getText().toString();
                  String passwordText = password.getText().toString();
                  if(emailText.isEmpty() || passwordText.isEmpty())
                  {
                      Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();

                  }
                  else
                  {

                      //perform query
                      UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                      final UserDao userDao = userDatabase.userDao();
                      new Thread(new Runnable() {
                          @Override
                          public void run() {
                              UserEntity userEntity = userDao.login(emailText, passwordText);
                              if(userEntity == null)
                              {
                                  runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();

                                      }
                                  });
                              }
                              else {
                                  //String name = userEntity.userName;
                                  Intent intent = new Intent(Login.this, Home.class);
                                  startActivity(intent);
                              }
                          }
                      }).start();
                  }
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