package com.example.angitha.mygame.utils

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

import com.example.angitha.mygame.R

object SoundHandler {
    private var successMove: MediaPlayer? = null
    private var failMove: MediaPlayer? = null
    private var levelCompleted: MediaPlayer? = null
    private var levelLost: MediaPlayer? = null
    var failVibrate:Vibrator?= null




    fun initializeTunes(mContext: Context) {
        successMove = MediaPlayer.create(mContext, R.raw.success_move)
        failMove = MediaPlayer.create(mContext, R.raw.fail_move)
        levelCompleted = MediaPlayer.create(mContext, R.raw.level_complete)
        levelLost = MediaPlayer.create(mContext, R.raw.lost_game)
        failVibrate = mContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    fun playSuccessMove(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            successMove!!.start()
        }
    }

    fun playFailMove(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
//            failMove!!.start()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                failVibrate!!.vibrate(VibrationEffect.createOneShot(50,VibrationEffect.DEFAULT_AMPLITUDE))
            }else{
                //deprecated in API 26
                failVibrate!!.vibrate(50)
            }
        }
    }

    fun levelCompleted(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            levelCompleted!!.start()
        }
    }

    fun levelLost(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            levelLost!!.start()
        }
    }

}
