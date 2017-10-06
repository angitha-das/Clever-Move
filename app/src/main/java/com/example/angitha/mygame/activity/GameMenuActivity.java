package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GameMenuController;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.MenuView;

/**
 * Created by angitha on 1/7/17.
 */

public class GameMenuActivity extends AppCompatActivity implements GameMenuController.MenuControllerListener{
    GameLevels gameLevels = GameLevels.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_menu);
        MenuView menuView = (MenuView) findViewById(R.id.menuView);

        TextView title =(TextView) findViewById(R.id.app_name);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/pacifico_regular.ttf");
        title.setTypeface(custom_font);

        Shader myShader = new LinearGradient(
                0, 0, 0, 70,
                Color.WHITE, getResources().getColor(R.color.app_title),
                Shader.TileMode.CLAMP );
        title.getPaint().setShader( myShader );
        GameMenuController gameMenuController =new GameMenuController(this, menuView);
        menuView.setListeners(gameMenuController);
    }

    @Override
    public void onPlay(@NonNull GameRules gameRules) {
        gameLevels.fromMenu = true;
        Intent gamePlayIntent = new Intent(this,GamePlayActivity.class);
        gamePlayIntent.putExtras(gameRules.exportTo(new Bundle()));
        startActivity(gamePlayIntent);
    }

    @Override
    public void quitGame(){
        finish();
    }

    @Override
    public void showAllLevels(){
        Intent gameLevelsIntent = new Intent(this,LevelsRecyclerActivity.class);
        startActivity(gameLevelsIntent);
    }
}
