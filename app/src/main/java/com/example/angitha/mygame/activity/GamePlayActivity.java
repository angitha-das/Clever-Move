package com.example.angitha.mygame.activity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.ThemePak;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.BoardView;

import java.util.Random;

/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayActivity extends AppCompatActivity {

    private GamePlayController mGameController;
    private final GameRules mGameRules = new GameRules();

    private ConstraintLayout gameBackground;
    private BoardView boardView;
    private TextView levelIndicator;
    private ImageView close;
    private ImageView refresh;
    private ImageView previousLevel;
    private ImageView nextLevel;
    private ImageView undo;
    Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_game_play);
        mGameRules.importFrom(getIntent().getExtras());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("Position");
        }
        gameBackground = (ConstraintLayout) findViewById(R.id.game_background);
        boardView = (BoardView) findViewById(R.id.game_table_layout);
        levelIndicator = (TextView) findViewById(R.id.level);
        close = (ImageView) findViewById(R.id.close);
        refresh = (ImageView) findViewById(R.id.refresh);
        previousLevel = (ImageView) findViewById(R.id.previousLevel);
        nextLevel = (ImageView) findViewById(R.id.nextLevel);
        undo = (ImageView) findViewById(R.id.undo);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameController.exitGame();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameController.restartGame();
            }
        });

        undo.setEnabled(false);
        undo.setVisibility(View.GONE);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameController.undoPreviousMove();
            }
        });
        previousLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameController.playPreviousGameLevel();
            }
        });
        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameController.playNextGameLevel();
            }
        });

        mGameController = new GamePlayController(this, boardView,levelIndicator,previousLevel,nextLevel,undo,gameBackground);
    }

    @Override
    public void onBackPressed() {
        mGameController.exitGame();
    }
}
