package com.vinnovation.mohallakhabar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread mythread = new Thread() {
            @Override
            public void run() {
                try {
                    ProgressBar pbar1 = (ProgressBar) findViewById(R.id.progressBar);
                    pbar1.setProgress(25);
                    sleep(1000);
                    pbar1.setProgress(50);
                    sleep(1000);
                    pbar1.setProgress(75);
                    sleep(1000);
                    pbar1.setProgress(100);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        mythread.start();

    }
}
