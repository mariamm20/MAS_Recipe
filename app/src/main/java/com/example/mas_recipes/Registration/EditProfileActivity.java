package com.example.mas_recipes.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mas_recipes.Home.HomeActivity;
import com.example.mas_recipes.R;
import com.example.mas_recipes.RoomDatabase.AppDatabase;
import com.example.mas_recipes.RoomDatabase.UserDao;
import com.example.mas_recipes.RoomDatabase.UserEntity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class EditProfileActivity extends AppCompatActivity {

    ImageButton edit_profile_back_btn;
    Button edit_profile_btn;
    EditText name, email, password;
    TextView logout_txt;

    AppDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        findView();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "recipe_db").allowMainThreadQueries().build();

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String Name = pref.getString("name", "user name");
        String Email = pref.getString("email", "email");
        String Password = pref.getString("password", "password");
        Integer user_id = pref.getInt("id", -1);
        name.setText(Name);
        email.setText(Email);
        password.setText(Password);

        edit_profile_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        logout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(EditProfileActivity.this, R.style.MyAlertDialogTheme);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setIcon(R.drawable.ic_baseline_logout_24);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // logout
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase userDatabase = AppDatabase.getInstance(getApplicationContext());
                                final UserDao userDao = userDatabase.userDao();
                                UserEntity userEntity = userDao.getProfileById(user_id);
                                userDao.logout(user_id);
                                SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();


                                editor.putBoolean("is_user_logged_in", false);
                                editor.commit();
                                boolean isLoggedIn = pref.getBoolean("is_user_logged_in", false);
                                Log.d("SharedPreferences", "IsLoggedIn: " + isLoggedIn);
                                Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }
                        }).start();
                        Toast.makeText(getApplicationContext(), "Logged Out Successfully!", Toast.LENGTH_SHORT).show();


                        //finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });

        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(EditProfileActivity.this, R.style.MyAlertDialogTheme);
                builder.setTitle("Edit Profile");
                builder.setMessage("Save Changes?");
                builder.setIcon(R.drawable.ic_baseline_edit_24);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // update code here
                                AppDatabase userDatabase = AppDatabase.getInstance(getApplicationContext());
                                final UserDao userDao = userDatabase.userDao();
                                UserEntity userEntity = userDao.getProfileById(user_id);

                                userEntity.setUserName(name.getText().toString());
                                userEntity.setEmail(email.getText().toString());
                                userEntity.setPassword(password.getText().toString());
                                userDao.updateProfile(userEntity);
                                UserEntity userEntity1 = userDao.getProfileById(user_id);
                                SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("id", userEntity1.getId());
                                editor.putString("name", userEntity1.getUserName());
                                editor.putString("email", userEntity1.getEmail());
                                editor.putString("password", userEntity1.getPassword());
                                editor.putBoolean("is_user_logged_in", true);

                                editor.apply();
                                Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                                startActivity(intent);

                            }
                        }).start();
                        Toast.makeText(getApplicationContext(), "Profile Updated Successfully!", Toast.LENGTH_SHORT).show();

                        // finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });



                builder.show();
            }
        });


    }

    private void findView() {
        edit_profile_back_btn = findViewById(R.id.edit_profile_back_btn);
        edit_profile_btn = findViewById(R.id.edit_profile_btn);
        logout_txt = findViewById(R.id.logout_txt);
        name = findViewById(R.id.edit_profile_username_edittext);
        email = findViewById(R.id.edit_profile_email_edittext);
        password = findViewById(R.id.edit_profile_password_edittext);
    }
}