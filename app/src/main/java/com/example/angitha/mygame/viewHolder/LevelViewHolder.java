package com.example.angitha.mygame.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.R;

/**
 * Created by angitha on 9/7/17.
 */

public class LevelViewHolder extends RecyclerView.ViewHolder{

    public ImageView locks;
    public TextView levelName;

    public LevelViewHolder(View itemView) {
        super(itemView);
        locks = (ImageView)itemView.findViewById(R.id.locks);
        levelName = (TextView)itemView.findViewById(R.id.levelName);
    }
}