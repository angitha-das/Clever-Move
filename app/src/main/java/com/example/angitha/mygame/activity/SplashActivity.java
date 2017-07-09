package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.utils.Constants;

/**
 * Created by angitha on 1/7/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, GameMenuActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}
