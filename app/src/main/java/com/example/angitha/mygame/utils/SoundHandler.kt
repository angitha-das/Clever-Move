package com.example.angitha.mygame.utils

import android.content.Context
import android.media.MediaPlayer

import com.example.angitha.mygame.R

object SoundHandler {
    private var successMove: MediaPlayer? = null
    private var failMove: MediaPlayer? = null
    private var levelCompleted: MediaPlayer? = null
    private var levelLost: MediaPlayer? = null

    fun initializeTunes(mContext: Context) {
        successMove = MediaPlayer.create(mContext, R.raw.success_move)
        failMove = MediaPlayer.create(mContext, R.raw.fail_move)
        levelCompleted = MediaPlayer.create(mContext, R.raw.level_complete)
        levelLost = MediaPlayer.create(mContext, R.raw.lost_game)
    }

    fun playSuccessMove(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            successMove!!.start()
        }
    }

    fun playFailMove(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            failMove!!.start()
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
