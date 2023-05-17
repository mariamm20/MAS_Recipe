package com.example.mas_recipes.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.UserDao;
import com.example.mas_recipes.RoomDatabase.UserEntity;

import java.util.Objects;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button reset_link_btn;
    EditText email, password, confirm_password;
    TextView signup_txt2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        findView();

        reset_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String confirmPasswordText = confirm_password.getText().toString();
                UserEntity userEntity = new UserEntity();
                AppDatabase userDatabase = AppDatabase.getInstance((getApplicationContext()));
                UserDao userDao = userDatabase.userDao();
                userEntity.setEmail(emailText);
                userEntity.setPassword(passwordText);
                userEntity.setConfirmPassword(confirmPasswordText);

                if (!validationInput(userEntity)) {
                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();

                } else if (!checkPassword(userEntity)) {
                    Toast.makeText(getApplicationContext(), "Please enter the same passwords", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (!Emailcheck(userEntity, userDatabase, userDao)) {
                                Log.d("error", "not found");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ForgetPasswordActivity.this, "This Email is not Existed", Toast.LENGTH_SHORT).show();
                                    }
                                });


                            } else {
                                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                UserEntity user = userDao.email(userEntity.getEmail());
                                Log.d("passworddddddddd", user.getId().toString());
                                UserEntity userEntity1 = userDao.getProfileById(user.getId());
                                userEntity1.setPassword(passwordText);
                                userEntity1.setConfirmPassword(confirmPasswordText);
                                userDao.updateProfile(userEntity1);


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ForgetPasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    }
                                });
                            }


                        }


                    }).start();

                }


            }
        });

        signup_txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ForgetPasswordActivity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void findView() {
        reset_link_btn = findViewById(R.id.reset_link_btn);
        email = findViewById(R.id.forget_pass_email_edittext);
        password = findViewById(R.id.forget_pass);
        confirm_password = findViewById(R.id.forget_confirm_pass);
        signup_txt2 = findViewById(R.id.forget_pass_signup_txt);

    }

    private static boolean Emailcheck(UserEntity userEntity, AppDatabase userDatabase, UserDao userDao) {

        userEntity = userDao.checkEmail(userEntity.getEmail());
        if (userEntity == null) {
            return false;
        }

        return true;


    }

    private Boolean validationInput(UserEntity userEntity) {
        if (
                userEntity.getEmail().isEmpty() ||
                        userEntity.getPassword().isEmpty() ||
                        userEntity.getConfirmPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean checkPassword(UserEntity userEntity) {
        if (!Objects.equals(userEntity.getPassword(), userEntity.getConfirmPassword())) {
            return false;
        }


        return true;

    }
}