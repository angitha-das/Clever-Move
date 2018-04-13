package com.example.angitha.mygame.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.angitha.mygame.R;

public class SoundHandler {
    private static MediaPlayer successMove,failMove,levelCompleted,levelLost;

    public static void initializeTunes(Context mContext){
        successMove= MediaPlayer.create(mContext, R.raw.success_move);
        failMove= MediaPlayer.create(mContext,R.raw.fail_move);
        levelCompleted= MediaPlayer.create(mContext,R.raw.level_complete);
        levelLost = MediaPlayer.create(mContext,R.raw.lost_game);
    }

    public static void playSuccessMove(Context mContext){
        if(PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND,true)){
            successMove.start();
        }
    }
    public static void playFailMove(Context mContext){
        if(PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND,true)){
            failMove.start();
        }
    }
    public static void levelCompleted(Context mContext){
        if(PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND,true)){
            levelCompleted.start();
        }
    }
    public static void levelLost(Context mContext){
        if(PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND,true)){
            levelLost.start();
        }
    }

}
