package com.example.angitha.mygame.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.activity.LevelsRecyclerActivity;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.viewHolder.LevelViewHolder;

/**
 * Created by angitha on 9/7/17.
 */

public class MyLevelsAdapter extends RecyclerView.Adapter<LevelViewHolder> {

    String[] levelList;
    Bitmap[] locksList;

    public MyLevelsAdapter(String[] levelList, Bitmap[] locksList) {
        this.levelList = levelList;
        this.locksList = locksList;
    }

    @Override
    public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_levels, parent, false);
        LevelViewHolder viewHolder = new LevelViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LevelViewHolder holder, final int position) {
        holder.locks.setImageBitmap(locksList[position]);
        holder.locks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do something replay any completed level or resume current level
            }
        });
        holder.levelName.setText(levelList[position]);
    }

    @Override
    public int getItemCount() {
        return levelList.length;
    }

}
