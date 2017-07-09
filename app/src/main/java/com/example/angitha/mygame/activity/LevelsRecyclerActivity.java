package com.example.angitha.mygame.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.angitha.mygame.adapter.MyLevelsAdapter;
import com.example.angitha.mygame.R;

public class LevelsRecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerViewLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_recycler);

        recyclerViewLevels = (RecyclerView) findViewById(R.id.recyclerViewLevels);
        Bitmap[] logos = new Bitmap[5];
        logos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
        logos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
        logos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
        logos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
        logos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_outline_black_24dp);
        MyLevelsAdapter adapter = new MyLevelsAdapter(getResources().getStringArray(R.array.levelList), logos);
        recyclerViewLevels.setLayoutManager(new GridLayoutManager(LevelsRecyclerActivity.this, 3));
        recyclerViewLevels.setAdapter(adapter);
    }
}
