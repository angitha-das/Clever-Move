package com.example.angitha.mygame.utils;

import android.content.Context;
import android.content.Intent;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.levels.GameLevels;

public class Utils {

    public static void shareApp(Context context) {
        try {
            int unicodeVictory = 0x270C;
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT,context.getResources().getString(R.string.app_name));
            String sAux = "\nI have crossed level "+ GameLevels.getInstance().getHighestLevelCrossed(context)+" in the game Clever Move!"+ getEmojiByUnicode(unicodeVictory)+"\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + context.getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            e.toString();
        }
    }

    public static String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
