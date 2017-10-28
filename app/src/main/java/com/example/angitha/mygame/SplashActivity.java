package com.example.angitha.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.angitha.mygame.activity.IntroActivity;
import com.example.angitha.mygame.utils.Constants;

/**
 * Created by angitha on 28/10/17.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                // close splash activity
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}
//https://android.jlelse.eu/right-way-to-create-splash-screen-on-android-e7f1709ba154