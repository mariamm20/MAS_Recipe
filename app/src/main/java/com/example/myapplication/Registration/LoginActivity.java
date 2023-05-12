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

import com.example.myapplication.Home.HomeActivity;
import com.example.myapplication.R;
import com.example.myapplication.RoomDatabase.UserDao;
import com.example.myapplication.RoomDatabase.UserDatabase;
import com.example.myapplication.RoomDatabase.UserEntity;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    EditText email, password;
    TextView signup_txt, forgot_password_txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_email_edittext);
        password = findViewById(R.id.login_password_edittext);

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
                                  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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
                Intent intent2 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });

        forgot_password_txt = findViewById(R.id.forgot_password_txt);
        forgot_password_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent2);
            }
        });


    }
}