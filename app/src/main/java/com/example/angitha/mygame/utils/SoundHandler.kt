package com.example.angitha.mygame.utils

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

import com.example.angitha.mygame.R

object SoundHandler {

    private var soundPool: SoundPool? = null
    private var successMove: Int = 0
    private var failMove: Int = 0
    private var levelCompleted: Int = 0
    private var levelLost: Int = 0
    private var failVibrate:Vibrator?= null
    private var loaded = false
    private var volume: Float = 0.0f

    fun initializeTunes(mContext: Context) {

        failVibrate = mContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        val audioManager = mContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager?

        // Load the sound
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        soundPool!!.setOnLoadCompleteListener({ _, _, _ -> loaded = true })

        successMove = soundPool!!.load(mContext, R.raw.success_move, 1)
        failMove = soundPool!!.load(mContext, R.raw.fail_move, 1)
        levelCompleted = soundPool!!.load(mContext, R.raw.level_complete, 1)
        levelLost = soundPool!!.load(mContext, R.raw.lost_game, 1)


        val actualVolume = audioManager!!
                .getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxVolume = audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        volume = actualVolume / maxVolume
    }

    fun playSuccessMove(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            if (loaded) {
                soundPool!!.play(successMove, volume, volume, 1, 0, 1f)
            }
        }
    }

    fun playFailMove(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    failVibrate!!.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    //deprecated in API 26
                    failVibrate!!.vibrate(50)
                }
            }
    }

    fun levelCompleted(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            if (loaded) {
                soundPool!!.play(levelCompleted, volume, volume, 1, 0, 1f)
            }
        }
    }

    fun levelLost(mContext: Context) {
        if (PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND, true)) {
            if (loaded) {
                soundPool!!.play(levelLost, volume, volume, 1, 0, 1f)
            }
        }
    }
}
