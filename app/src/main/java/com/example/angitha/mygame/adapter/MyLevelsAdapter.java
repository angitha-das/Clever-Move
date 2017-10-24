package com.example.angitha.mygame.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.viewHolder.LevelViewHolder;

/**
 * Created by angitha on 9/7/17.
 */

public class MyLevelsAdapter extends RecyclerView.Adapter<LevelViewHolder> {

    private String[] levelList;
    private Bitmap[] locksList;
    private Context mContext;
    private GameRules gameRules = new GameRules();
    private GameLevels mGameLevels = GameLevels.getInstance();


    public MyLevelsAdapter(Context mContext, String[] levelList, Bitmap[] locksList) {
        this.levelList = levelList;
        this.locksList = locksList;
        this.mContext = mContext;
    }

    @Override
    public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_levels, parent, false);
        return new LevelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LevelViewHolder holder, final int position) {
        holder.locksImage.setImageBitmap(locksList[position]);
        holder.levelName.setText(levelList[position]);
        if((position) <= mGameLevels.getHighestLevelCrossed(mContext)){
            holder.picker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGameLevels.fromMenu = false;
                    mGameLevels.levelToPlay = position;
                    Intent gamePlayIntent = new Intent(mContext,GamePlayActivity.class);
                    gamePlayIntent.putExtras(gameRules.exportTo(new Bundle()));
                    gamePlayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(gamePlayIntent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return levelList.length;
    }

}
