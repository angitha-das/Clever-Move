package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.utils.Constants;

/**
 * Created by angitha on 28/10/17.
 */

public class SplashActivity extends AppCompatActivity {
    SharedPreferences prefs;
    final Handler handler = new Handler();
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        runnable = new Runnable() {
            @Override
            public void run() {
                if(prefs!=null && !prefs.getBoolean("first_time", false)){
                    Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivityForResult(i,1);
                }else{
                    GameLevels.getInstance().gameTour=false;
                    Intent i = new Intent(SplashActivity.this, GameMenuActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        handler.postDelayed(runnable, Constants.SPLASH_TIME_OUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}