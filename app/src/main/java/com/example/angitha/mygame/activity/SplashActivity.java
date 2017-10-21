package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.utils.Constants;

/**
 * Created by angitha on 1/7/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        TextView title = (TextView) findViewById(R.id.logo);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/pacifico_regular.ttf");
        title.setTypeface(custom_font);

//        Shader myShader = new LinearGradient(
//                0, 0, 0, 70,
//                Color.WHITE, getResources().getColor(R.color.app_title),
//                Shader.TileMode.CLAMP );
//        title.getPaint().setShader( myShader );

        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}
