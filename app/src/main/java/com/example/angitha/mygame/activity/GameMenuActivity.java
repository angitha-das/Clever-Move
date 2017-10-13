package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GameMenuController;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.MenuView;
import com.example.angitha.mygame.view.PegLayout;

/**
 * Created by angitha on 1/7/17.
 */

public class GameMenuActivity extends AppCompatActivity implements GameMenuController.MenuControllerListener {
    GameLevels gameLevels = GameLevels.getInstance();
    Animation play_animation;
    ImageView play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_menu);
        MenuView menuView = (MenuView) findViewById(R.id.menuView);

        TextView title = (TextView) findViewById(R.id.app_name);
        play_button = (ImageView) findViewById(R.id.play);

        ScaleAnimation scaleIn = new ScaleAnimation(0.6f, 0.9f, 0.6f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleIn.setDuration(500);
        scaleIn.setRepeatCount(Animation.INFINITE);
        scaleIn.setRepeatMode(Animation.REVERSE);
        play_button.startAnimation(scaleIn);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/pacifico_regular.ttf");
        title.setTypeface(custom_font);
        GameMenuController gameMenuController = new GameMenuController(this, menuView);
        menuView.setListeners(gameMenuController);
    }

    @Override
    public void onPlay(@NonNull GameRules gameRules) {
        gameLevels.fromMenu = true;
        Intent gamePlayIntent = new Intent(this, GamePlayActivity.class);
        gamePlayIntent.putExtras(gameRules.exportTo(new Bundle()));
        startActivity(gamePlayIntent);
    }

    @Override
    public void showAllLevels() {
        Intent gameLevelsIntent = new Intent(this, LevelsRecyclerActivity.class);
        startActivity(gameLevelsIntent);
    }

}
