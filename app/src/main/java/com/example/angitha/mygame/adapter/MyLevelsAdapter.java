package com.example.angitha.mygame.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.viewHolder.LevelViewHolder;

/**
 * Created by angitha on 9/7/17.
 */

public class MyLevelsAdapter extends RecyclerView.Adapter<LevelViewHolder> {

    private Context mContext;
    private GameRules gameRules = new GameRules();
    private GameLevels mGameLevels = GameLevels.getInstance();
    private int totalNumberOfLevels;


    public MyLevelsAdapter(Context mContext) {
        this.mContext = mContext;
        totalNumberOfLevels = mGameLevels.getTotalNumberOfLevels();
    }

    @Override
    public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_levels, parent, false);
        return new LevelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final LevelViewHolder holder, int position) {
            if (holder.getLayoutPosition()> mGameLevels.getHighestLevelCrossed(mContext)) {
                holder.locksImage.setImageResource(R.drawable.locked);
            } else if(holder.getLayoutPosition() == mGameLevels.getHighestLevelCrossed(mContext)) {
                holder.locksImage.setImageResource(R.drawable.open_lock);
            }else{
                holder.locksImage.setImageResource(R.drawable.star);
            }
            holder.levelName.setText(String.format("Level %d", holder.getLayoutPosition() + 1));


            holder.picker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((holder.getAdapterPosition()) <= mGameLevels.getHighestLevelCrossed(mContext)) {
                        mGameLevels.levelToPlay = holder.getAdapterPosition();
                        mGameLevels.fromMenu = false;
                        Intent gamePlayIntent = new Intent(mContext, GamePlayActivity.class);
                        gamePlayIntent.putExtras(gameRules.exportTo(new Bundle()));
                        gamePlayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(gamePlayIntent);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return totalNumberOfLevels;
    }

}
