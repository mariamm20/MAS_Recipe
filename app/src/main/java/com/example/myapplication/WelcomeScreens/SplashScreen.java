package com.example.myapplication.WelcomeScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.Registration.Login;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressbar;
    int counter = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        progressbar = findViewById(R.id.progressBar);
        progressbar.setScaleY(1.5f);
        int color = Color.parseColor("#ffffff");
        progressbar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressbar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        Thread thread=new Thread(){
            @Override
            public void run(){
                try {
                    run_ProgressBar();
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(),Onboarding.class);
                    startActivities(new Intent[]{intent});
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}};
        thread.start();

    }

    private void run_ProgressBar() {
        final Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter ++;
                progressbar.setProgress(counter);
                if(counter == 100)
                    timer.cancel();
            }
        };
        timer.schedule(timerTask, 0, 100);
    }
}