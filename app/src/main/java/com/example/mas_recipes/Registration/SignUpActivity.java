package com.example.mas_recipes.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.mas_recipes.WelcomeScreens.OnboardingActivity;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    Button signup_btn;
    EditText userName, Email, Password, confirm_Password;

    TextView login_txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findView();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = Email.getText().toString();

                UserEntity userEntity = new UserEntity();
                AppDatabase userDatabase = AppDatabase.getInstance((getApplicationContext()));
                UserDao userDao = userDatabase.userDao();
                userEntity.setUserName(userName.getText().toString());
                userEntity.setEmail(Email.getText().toString());
                userEntity.setPassword(Password.getText().toString());
                userEntity.setConfirmPassword(confirm_Password.getText().toString());


                if (!validationInput(userEntity)) {
                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();

                } else if (!isValidEmail(userEntity)) {
                    Toast.makeText(getApplicationContext(), "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                } else if (!checkPassword(userEntity)) {
                    Toast.makeText(getApplicationContext(), "Please enter the same passwords", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (!Emailcheck(userEntity, userDatabase, userDao)) {
                                Log.d("error", "registration error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SignUpActivity.this, "This Email is Already Used", Toast.LENGTH_SHORT).show();


                                    }
                                });


                            } else {
                                userDao.registerUser(userEntity);

                                UserEntity userEntity1 = userDao.login(userEntity.getEmail(), userEntity.getPassword());
                                userEntity1.setIs_logged(true);
                                userDao.updateProfile(userEntity1);


                                SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("id", userEntity1.getId());
                                editor.putString("name", userEntity1.getUserName());
                                editor.putString("name", userEntity1.getUserName());
                                editor.putString("email", userEntity1.getEmail());
                                editor.putString("password", userEntity1.getPassword());
                                editor.putBoolean("is_user_logged_in", true);

                                editor.apply();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpActivity.this, OnboardingActivity.class);
                                        startActivity(intent);

                                    }
                                });
                            }
                            // Register User


                        }


                    }).start();

                }
            }


            //  }

        });

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent2);
            }
        });

    }

    private void findView() {
        userName = findViewById(R.id.signup_username_edittext);
        Email = findViewById(R.id.signup_email_edittext);
        Password = findViewById(R.id.signup_password_edittext);
        confirm_Password = findViewById(R.id.signup_confirm_password_edittext);
        signup_btn = findViewById(R.id.signup_btn);
        login_txt = findViewById(R.id.login_txt);

    }

    private Boolean validationInput(UserEntity userEntity) {
        if (userEntity.getUserName().isEmpty() ||
                userEntity.getEmail().isEmpty() ||
                userEntity.getPassword().isEmpty() ||
                userEntity.getConfirmPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(UserEntity userEntity) {
        if (userEntity.getEmail() == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(userEntity.getEmail()).matches();
        }


    }

    private static boolean Emailcheck(UserEntity userEntity, AppDatabase userDatabase, UserDao userDao) {
        userEntity = userDao.checkEmail(userEntity.getEmail());
        if (userEntity != null) {
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