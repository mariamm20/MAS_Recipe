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

import com.example.mas_recipes.Home.HomeActivity;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.UserDao;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.UserEntity;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    EditText email, password;
    TextView signup_txt, forgot_password_txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();

                } else {

                    //perform query
                    AppDatabase userDatabase = AppDatabase.getInstance(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(emailText, passwordText);
                            if (userEntity == null) {
                                Log.d("login error", "invalid credentials");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();


                                    }
                                });

                            } else {
                                userEntity.setIs_logged(true);
                                userDao.updateProfile(userEntity);
                                // Store the user ID in a shared preference
                                SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("id", userEntity.getId());
                                editor.putString("name", userEntity.getUserName());
                                editor.putString("email", userEntity.getEmail());
                                editor.putString("password", userEntity.getPassword());
                                editor.putBoolean("is_user_logged_in", true);

                                editor.apply();

                                //String name = userEntity.userName;
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                    }).start();


                }
            }
        });

        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });

        forgot_password_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent2);
            }
        });

    }

    private void findViews() {
        email = findViewById(R.id.login_email_edittext);
        password = findViewById(R.id.login_password_edittext);
        login_btn = findViewById(R.id.login_btn);
        signup_txt = findViewById(R.id.signup_txt);
        forgot_password_txt = findViewById(R.id.forgot_password_txt);
    }
}