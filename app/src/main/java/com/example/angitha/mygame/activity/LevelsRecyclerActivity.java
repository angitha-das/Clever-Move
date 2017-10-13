package com.example.angitha.mygame.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
            if (i > mGameLevels.getHighestLevelCrossed(getBaseContext())) {
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.closed_lock);
            } else if(i == mGameLevels.getHighestLevelCrossed(getBaseContext())) {
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.open_lock);
            }else{
                logos[i] = BitmapFactory.decodeResource(getResources(), R.drawable.star);
            }
            levelList[i] = "Level " + (i + 1);
        }
        MyLevelsAdapter adapter = new MyLevelsAdapter(getApplicationContext(),levelList, logos);
        recyclerViewLevels.setLayoutManager(new GridLayoutManager(LevelsRecyclerActivity.this, 3));
        recyclerViewLevels.setAdapter(adapter);
    }
}
