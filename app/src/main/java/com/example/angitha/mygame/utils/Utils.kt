package com.example.angitha.mygame.utils

import android.content.Context
import android.content.Intent

import com.example.angitha.mygame.R
import com.example.angitha.mygame.levels.GameLevels

object Utils {

    fun shareApp(context: Context) {
        try {
            val unicodeVictory = 0x270C
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, context.resources.getString(R.string.app_name))
            var sAux = "\nI have crossed level " + GameLevels.getInstance().getHighestLevelCrossed(context) + " in the game Clever Move!" + getEmojiByUnicode(unicodeVictory) + "\nGet it for Android:"
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + context.packageName
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            context.startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
}
