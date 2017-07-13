package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.angitha.mygame.adapter.MyLevelsAdapter;
import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GameMenuController;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.rules.GameRules;

public class LevelsRecyclerActivity extends AppCompatActivity{
    RecyclerView recyclerViewLevels;
    int totalNumberOfLevels;
    LinearLayout levelPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_recycler);

        GameLevels gameLevelsObject = new GameLevels();
        recyclerViewLevels = (RecyclerView) findViewById(R.id.recyclerViewLevels);
        levelPicker = (LinearLayout) findViewById(R.id.levelPicker);
        totalNumberOfLevels= gameLevelsObject.getNumberOfLevels();

        int i;
        Bitmap[] logos = new Bitmap[totalNumberOfLevels];
        String[] levelList = new String[totalNumberOfLevels];

        for(i= 0;i< totalNumberOfLevels; i++) {
            if (i < gameLevelsObject.getGameLevel(getApplicationContext())) {
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_open_amber_400_24dp);
            } else if(i == gameLevelsObject.getGameLevel(getApplicationContext())) {
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
            }else{
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_black_24dp);
            }
            levelList[i] = "Level " + (i + 1);
        }
        MyLevelsAdapter adapter = new MyLevelsAdapter(levelList, logos);
        recyclerViewLevels.setLayoutManager(new GridLayoutManager(LevelsRecyclerActivity.this, 3));
        recyclerViewLevels.setAdapter(adapter);
    }
}
