package com.example.angitha.mygame.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.adapter.MyLevelsAdapter;
import com.example.angitha.mygame.levels.GameLevels;

public class LevelsRecyclerActivity extends AppCompatActivity{
    RecyclerView recyclerViewLevels;
    int totalNumberOfLevels;
    LinearLayout levelPicker;
    GameLevels mGameLevels = GameLevels.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_recycler);

        recyclerViewLevels = (RecyclerView) findViewById(R.id.recyclerViewLevels);
        levelPicker = (LinearLayout) findViewById(R.id.levelPicker);
        totalNumberOfLevels= mGameLevels.getTotalNumberOfLevels();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i;
        Bitmap[] logos = new Bitmap[totalNumberOfLevels];
        String[] levelList = new String[totalNumberOfLevels];

        for(i= 0;i< totalNumberOfLevels; i++) {
            if (i < mGameLevels.getGameLevelToPlay(getBaseContext())) {
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_open_amber_400_24dp);
            } else if(i == mGameLevels.getGameLevelToPlay(getBaseContext())) {
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
            }else{
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_black_24dp);
            }
            levelList[i] = "Level " + (i + 1);
        }
        MyLevelsAdapter adapter = new MyLevelsAdapter(getApplicationContext(),levelList, logos);
        recyclerViewLevels.setLayoutManager(new GridLayoutManager(LevelsRecyclerActivity.this, 3));
        recyclerViewLevels.setAdapter(adapter);
    }
}
