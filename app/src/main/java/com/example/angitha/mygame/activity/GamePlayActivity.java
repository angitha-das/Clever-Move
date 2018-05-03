package com.example.angitha.mygame.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.BoardView;

/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayActivity extends AppCompatActivity {

    private GamePlayController mGameController;
    private final GameRules mGameRules = new GameRules();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.activity_game_play);
        if (extras != null) {
            mGameRules.importFrom(extras);
            extras.getInt("Position");
        }

        ConstraintLayout gameBackground = findViewById(R.id.game_background);
        BoardView boardView = findViewById(R.id.game_table_layout);
        TextView levelIndicator = findViewById(R.id.level);
        ImageView close = findViewById(R.id.close);
        ImageView refresh = findViewById(R.id.refresh);
        ImageView previousLevel = findViewById(R.id.previousLevel);
        ImageView nextLevel = findViewById(R.id.nextLevel);
        ImageView undo = findViewById(R.id.undo);

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

        mGameController = new GamePlayController(this, boardView, levelIndicator,
                previousLevel, nextLevel,undo,close,refresh, gameBackground);
    }

    @Override
    public void onBackPressed() {
        mGameController.exitGame();
    }
}
