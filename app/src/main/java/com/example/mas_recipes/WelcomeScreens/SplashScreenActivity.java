package com.example.mas_recipes.WelcomeScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.mas_recipes.Home.HomeActivity;
import com.example.mas_recipes.R;
import com.example.mas_recipes.Registration.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar progressbar;
    int counter = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        findView();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isUserLoggedIn = prefs.getBoolean("is_user_logged_in", false);
        Log.d("SplashScreenActivity", "isUserLoggedIn = " + isUserLoggedIn);

        if (isUserLoggedIn) {
            Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        run_ProgressBar();
                        sleep(3000);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    private void findView() {
        progressbar = findViewById(R.id.progressBar);
    }

    private void run_ProgressBar() {
//        progressbar.setScaleY(1.5f);
        int color = Color.parseColor("#43A047");
        progressbar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
//        progressbar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressbar.setProgress(counter);
                if (counter == 100)
                    timer.cancel();
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

}