package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

        TextView title =  findViewById(R.id.logo);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/pacifico_regular.ttf");
        title.setTypeface(custom_font);

//        Shader myShader = new LinearGradient(
//                0, 0, 0, 70,
//                Color.WHITE, getResources().getColor(R.color.app_title),
//                Shader.TileMode.CLAMP );
//        title.getPaint().setShader( myShader );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(i);
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}
