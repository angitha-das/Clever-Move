package com.example.angitha.mygame.utils;

/**
 * Created by angitha on 9/7/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LevelCrossedPrefUtils {

    public static void saveToPrefs(Context context, String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static int getFromPrefs(Context context, String key, int defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getInt(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

//    public static void removeFromPrefs(Context context, int key) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        final SharedPreferences.Editor editor = prefs.edit();
//        editor.remove(key);
//        editor.commit();
//    }
}

