package com.example.angitha.mygame.levels;

import android.content.Context;

import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.utils.Constants;

import static com.example.angitha.mygame.utils.PrefUtils.getFromPrefs;
import static com.example.angitha.mygame.utils.PrefUtils.saveToPrefs;

/**
 * Created by angitha on 4/7/17.
 */

public class GameLevels {
    private int totalNumberOfLevels = 80;


    //clicked from levels activity.disable unlocked levels from click
    public int levelToPlay;
    public boolean fromMenu = false;
    public boolean gameTour = false;
    public static int ROWS;
    public static int COLS;

    /*
    0 invisible
    1 filled
    2 empty
     */

    private static int[][] gridForLevel1 = {
            {0,0,0,0,0,0},
            {0,2,1,1,0,0},
            {0,1,0,0,0,0},
            {0,2,1,2,0,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel2 = {
            {0,0,0,0,0,0},
            {0,2,2,2,0,0},
            {0,2,1,1,0,0},
            {0,2,1,1,0,0},
            {0,2,2,2,0,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel3 = {
            {0,0,0,0,0,0},
            {0,2,1,2,0,0},
            {0,2,1,2,0,0},
            {0,2,1,1,0,0},
            {0,2,2,2,0,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel4 = {
            {0,0,0,0,0,0},
            {0,2,2,2,2,0},
            {0,2,2,1,2,0},
            {0,1,1,1,2,0},
            {0,2,2,1,2,0},
            {0,2,2,2,2,0}
    };

    private static int[][] gridForLevel5 = {
            {0,0,0,0,0,0},
            {0,2,2,2,2,0},
            {0,1,1,1,2,0},
            {0,2,2,1,2,0},
            {0,2,2,1,2,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel6 = {
            {0,0,0,0,0,0},
            {0,2,1,1,2,0},
            {0,1,1,2,2,0},
            {0,1,1,2,2,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
    };

    private static int[][] gridForLevel7 = {
            {0,0,0,0,0,0},
            {0,0,1,0,0,0},
            {0,0,1,0,0,0},
            {0,2,1,1,2,0},
            {0,0,1,0,0,0},
            {0,0,1,0,0,0},
            {0,0,0,0,0,0},
    };

    private static int[][] gridForLevel8 = {
            {0,0,0,0,0,0},
            {0,2,2,2,2,0},
            {0,1,1,1,2,0},
            {0,2,1,1,2,0},
            {0,2,2,1,2,0},
            {0,2,2,1,2,0},
            {0,0,0,0,0,0},
    };

    private static int[][] gridForLevel9 = {
            {0,0,0,0,0,0},
            {0,2,1,2,0,0},
            {0,2,1,1,0,0},
            {0,1,1,2,0,0},
            {0,2,1,2,0,0},
            {0,2,1,2,0,0},
            {0,0,0,0,0,0},
    };

    private static int[][] gridForLevel10 = {
            {0,0,0,0,0,0},
            {0,2,1,2,0,0},
            {0,1,1,1,0,0},
            {0,2,1,1,0,0},
            {0,2,1,2,0,0},
            {0,0,0,0,0,0},
    };

    private static int[][] gridForLevel11 = {
            {0,0,0,0,0,0,0},
            {0,2,2,2,2,0,0},
            {0,2,1,2,2,0,0},
            {0,2,1,2,2,0,0},
            {0,2,1,1,1,1,2},
            {0,2,1,2,2,0,0},
            {0,2,1,2,2,0,0},
            {0,0,0,0,0,0,0},
    };

    private static int[][] gridForLevel12 = {
            {0,0,0,0,0,0},
            {2,2,2,2,2,2},
            {2,1,1,1,1,2},
            {2,1,2,2,1,2},
            {2,2,1,1,2,2},
            {2,2,2,2,2,2},
    };

    private static int[][] gridForLevel13 = {
            {0,0,0,0,0,0,0},
            {2,2,2,2,2,2,2},
            {2,1,1,1,1,1,2},
            {2,2,2,1,1,1,2},
            {2,2,2,2,2,2,2},
            {0,0,0,0,0,0,0},
    };


    private static int[][] gridForLevel14 = {
            {0,0,0,0,0,0,0},
            {2,2,2,2,2,2,2},
            {2,2,1,1,1,2,2},
            {2,1,1,2,1,2,2},
            {2,2,1,1,1,2,2},
            {0,0,0,0,0,0,0},
    };

    private static int[][] gridForLevel15 = {
            {0,0,0,0,0,0,0},
            {2,2,2,2,2,2,2},
            {2,2,1,1,1,2,2},
            {1,1,2,1,2,2,2},
            {2,2,1,1,1,2,2},
            {2,2,2,2,2,2,2},
    };

    private static int[][] gridForLevel16 = {
            {0,0,0,0,0,0,0},
            {2,2,2,2,2,2,2},
            {2,2,1,1,1,2,2},
            {2,1,1,2,1,1,2},
            {2,2,1,2,1,2,2},
            {2,2,2,2,2,2,2},
    };

    private static int[][] gridForLevel17 = {
            {0,0,0,1,0,0,0},
            {2,2,2,1,2,2,2},
            {2,2,2,1,2,2,2},
            {2,2,2,1,1,2,2},
            {2,2,1,1,2,2,2},
            {0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0},
    };

    private static int[][] gridForLevel18 = {
            {0,0,0,0,0,0,0},
            {2,2,2,1,2,2,2},
            {2,2,1,1,1,2,2},
            {2,2,1,1,1,2,2},
            {2,2,1,1,1,2,2},
            {2,2,2,2,2,2,2},
    };

    private static int[][] gridForLevel19 = {
            {0,0,0,0,0,0,0},
            {2,2,2,1,2,2,2},
            {2,2,1,1,1,2,2},
            {2,2,2,1,1,2,2},
            {2,2,1,1,1,2,2},
            {0,0,0,1,0,0,0},
    };

    private static int[][] gridForLevel20 = {
            {2,2,2,2,2,2,2},
            {2,2,2,1,2,2,2},
            {2,2,1,1,1,2,2},
            {2,2,2,1,2,2,2},
            {2,1,1,1,1,1,2},
            {2,2,2,2,2,2,2},
    };


    private static int[][] gridForLevel21 = {
            {0,0,0,0,0,0},
            {0,1,1,2,0,0},
            {0,2,1,1,0,0},
            {0,0,0,2,0,0},
            {0,0,0,1,0,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel22 = {
            {0,0,0,0,0,0},
            {0,1,1,1,0,0},
            {0,1,0,0,0,0},
            {0,2,1,2,1,0},
            {0,1,0,0,0,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel23 = {
            {0,0,0,0,0,0},
            {0,2,2,2,2,0},
            {0,2,1,1,2,0},
            {0,2,1,2,2,0},
            {0,1,1,2,2,0},
            {0,2,2,1,2,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel24 = {
            {0,0,0,0,0,0},
            {0,2,1,2,0,0},
            {0,1,2,1,0,0},
            {0,1,2,2,0,0},
            {0,2,1,2,0,0},
            {0,2,2,1,0,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel25 = {
            {0,0,0,0,0,0},
            {0,2,2,2,1,0},
            {0,2,2,2,1,0},
            {0,2,2,1,2,0},
            {0,1,1,2,1,0},
            {0,0,0,0,0,0}
    };

    private static int[][] gridForLevel26 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,2,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},

    };

    private static int[][] gridForLevel27 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,1,2,0,0,0},
            {0,0,2,2,1,1,0,0,0},
            {0,0,1,2,2,1,0,0,0},
            {0,0,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel28 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,1,1,1,1,1,1,1,0},
            {0,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel29 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,2,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel30 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,1,1,1,2,0,0,0},
            {0,0,1,1,1,1,0,0,0},
            {0,0,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel31 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,1,1,2,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,1,2,1,2,2,0,0},
            {0,0,2,1,2,1,2,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,2,2,2,1,2,0,0},
            {0,0,2,2,2,1,2,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel32 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,1,1,2,2,1,1,0},
            {0,0,2,1,1,1,1,2,0},
            {0,0,2,1,2,2,2,2,0},
            {0,0,2,2,1,2,2,2,0},
            {0,0,2,2,1,2,2,2,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel33 = {
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,2,2,1,2,2,2,0},
            {0,2,2,2,1,2,2,2,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
    };

    private static int[][] gridForLevel34 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,2,2,2,1,1,2,2,0},
            {0,2,2,1,1,2,2,2,0},
            {0,2,2,2,1,2,2,2,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel35 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,2,1,1,1,2,2,0},
            {0,2,2,1,1,1,2,2,0},
            {0,2,2,1,2,1,2,2,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel36 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,2,2,1,1,1,2,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,2,1,1,1,2,2,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0},
    };

    private static int[][] gridForLevel37 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,1,2,2,2,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0},
    };

    private static int[][] gridForLevel38 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,1,1,1,1,1,1,1,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,2,1,1,1,2,2,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,0,0,0,0,0,0},

    };

    private static int[][] gridForLevel39 = {
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,1,2,1,0,0,0},
            {2,2,1,1,1,2,1,2,2},
            {2,1,2,1,2,1,2,1,2},
            {2,2,1,2,1,2,1,2,2},
            {0,0,0,1,2,1,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,2,2,2,0,0,0}
    };

    private static int[][] gridForLevel40 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,1,2,2,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,1,2,1,1,2,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel41 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,1,0,0},
            {0,1,2,1,1,1,2,0,0},
            {0,2,1,2,2,1,2,0,0},
            {0,2,1,1,1,1,2,0,0},
            {0,2,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel42 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel43 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,1,2,1,0,0},
            {0,0,2,1,1,2,2,0,0},
            {0,0,1,2,1,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel44 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,1,1,2,1,2,0,0},
            {0,0,2,1,1,2,1,0,0},
            {0,0,2,1,1,2,1,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,2,2,2,1,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel45 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,2,1,1,2,1,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel46 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,1,1,0,0,0},
            {0,0,1,2,1,1,0,0,0},
            {0,0,1,1,2,1,0,0,0},
            {0,0,2,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel47 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,2,1,2,1,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel48 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,2,1,2,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel49 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,2,1,2,0,0},
            {0,0,2,2,1,1,2,0,0},
            {0,0,1,2,1,1,1,0,0},
            {0,0,1,1,2,1,2,0,0},
            {0,0,2,2,1,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel50 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel51 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,1,1,1,1,2,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,2,1,2,1,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel52 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,1,1,1,1,2,0,0},
            {0,0,2,2,1,1,2,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel53 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,1,1,1,1,2,0,0},
            {0,0,1,2,1,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,2,1,1,1,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel54 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,1,2,1,1,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,0,2,1,1,2,1,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel55 = {
            {0,0,0,0,0,0,0,0,0},
            {0,2,1,2,1,2,1,2,0},
            {0,2,1,2,1,2,1,2,0},
            {0,2,2,1,2,1,2,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,2,1,1,2,1,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel56 = {
            {0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,1,1,0},
            {0,1,1,1,1,2,1,1,0},
            {0,1,1,2,1,1,2,2,0},
            {0,2,1,1,1,2,1,2,0},
            {0,1,1,1,1,1,2,1,0},
            {0,2,2,1,1,2,2,1,0},
            {0,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel57 = {
            {0,0,0,0,0,0,0,0,0},
            {0,2,2,2,1,2,1,2,0},
            {0,2,2,1,2,1,2,1,0},
            {0,1,1,1,2,2,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,2,1,1,2,1,2,2,0},
            {0,1,1,2,1,1,1,2,0},
            {0,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel58 = {
            {0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,1,1,2,0},
            {0,2,2,1,2,1,1,1,0},
            {0,2,2,2,1,1,2,2,0},
            {0,2,1,1,1,1,1,1,0},
            {0,2,1,1,2,1,2,1,0},
            {0,2,1,1,1,2,2,2,0},
            {0,2,1,1,2,1,2,1,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel59 = {
            {0,0,0,0,0,0,0,0,0},
            {0,2,2,1,1,2,1,1,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,2,1,1,2,2,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,1,2,2,2,1,2,0},
            {0,1,1,2,1,1,1,2,0},
            {0,2,2,2,2,1,1,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel60 = {
            {0,0,0,0,0,0,0,0,0},
            {0,1,1,2,1,2,2,2,0},
            {0,2,1,2,1,2,2,2,0},
            {0,2,2,1,1,1,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,1,1,1,2,1,2,0},
            {0,2,1,1,1,1,2,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel61 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,2,2,2,2,2,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel62 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,2,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel63 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,2,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel64 = {
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {1,1,1,1,1,1,1,1,1},
            {1,1,1,1,2,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0}
    };

    private static int[][] gridForLevel65 = {
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,1,1,1,1,1,1,1,1},
            {0,1,1,1,2,1,1,1,1},
            {0,1,1,1,1,1,1,1,1},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel66 = {
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,2,1,1,1,1,1,1,0},
            {1,1,1,1,2,1,1,1,1},
            {0,1,1,1,1,1,1,1,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,0,0}
    };

    private static int[][] gridForLevel67 = {
            {2,1,2,1,2,2,2,1,2},
            {1,2,1,2,1,2,1,2,1},
            {1,1,2,1,2,1,2,1,2},
            {1,2,1,2,1,2,1,2,2},
            {2,1,2,1,2,1,2,1,2},
            {2,2,1,2,1,2,1,2,1},
            {2,1,2,1,2,1,2,2,2},
            {1,2,1,2,1,2,2,2,1},
            {2,1,2,2,2,1,2,1,2}
    };

    private static int[][] gridForLevel68 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,1,1,1,1,1,1,1,0},
            {0,2,2,2,2,2,2,2,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel69 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel70 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,2,1,1,1,2,2,2,0},
            {0,2,1,1,1,1,2,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel71 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel72 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel73 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,1,1,1,2,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,2,1,1,1,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,2,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel74 = {
            {0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,2,1,0,0},
            {0,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel75 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,1,0},
            {0,0,1,1,2,1,1,1,0},
            {0,0,1,1,1,1,1,1,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel76 = {
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,2,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0}
    };

    private static int[][] gridForLevel77 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,1,1,1,2,1,1,1,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel78 = {
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,1,1,1,1,1,1,1,0},
            {1,1,1,1,2,1,1,1,1},
            {0,1,1,1,1,1,1,1,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,0,0}
    };

    private static int[][] gridForLevel79 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,0,0},
            {0,1,1,1,2,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,0,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel80 = {
            {0,0,0,1,2,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {1,1,1,1,2,1,1,1,1},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel81 = {
            {0,0,0,1,1,1,2,1,1},
            {0,0,0,1,1,1,1,1,1},
            {0,0,0,1,1,1,1,1,1},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {1,1,1,1,1,1,0,0,0},
            {1,1,1,1,1,1,0,0,0},
            {1,1,1,1,1,1,0,0,0}
    };

    private static int[][] gridForLevel82 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,1,1,1,2,1,1,1,0},
            {0,2,1,1,1,1,1,2,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel83 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,2,2,1,2,2,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,1,1,2,1,1,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel84 = {
            {0,0,0,0,0,0,0,0,0},
            {0,1,1,2,2,2,1,1,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,1,2,1,2,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,1,2,1,2,1,2,0},
            {0,2,1,1,2,1,1,2,0},
            {0,2,2,2,1,2,2,2,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel85 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,2,1,1,1,2,2,0},
            {0,2,2,1,1,1,2,2,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private static int[][] gridForLevel86 = {
            {0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,2,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0},
    };

    private static int[][] gridForLevel87 = {
            {0,0,0,0,0,0,0,0,0},
            {0,1,1,2,2,2,1,1,0},
            {0,1,1,1,2,1,1,1,0},
            {0,2,1,1,1,1,1,2,0},
            {0,2,2,1,1,1,2,2,0},
            {0,2,1,1,1,1,1,2,0},
            {0,1,1,1,2,1,1,1,0},
            {0,1,1,2,2,2,1,1,0},
            {0,0,0,0,0,0,0,0,0},
    };


    private static GameLevels myObj;

    private GameLevels(){

    }
    public static GameLevels getInstance(){
        if(myObj == null){
            myObj = new GameLevels();
        }
        return myObj;
    }

    public static int[][] setGameBoard(int Level){
        switch (Level) {
            case GameRules.Level.LEVEL1:{
                ROWS = gridForLevel1.length;
                COLS =  gridForLevel1[0].length;
                return gridForLevel1;
            }
            case GameRules.Level.LEVEL2:{
                ROWS = gridForLevel2.length;
                COLS =  gridForLevel2[0].length;
                return gridForLevel2;
            }
            case GameRules.Level.LEVEL3:{
                ROWS = gridForLevel3.length;
                COLS =  gridForLevel3[0].length;
                return gridForLevel3;
            }
            case GameRules.Level.LEVEL4:{
                ROWS = gridForLevel4.length;
                COLS =  gridForLevel4[0].length;
                return gridForLevel4;
            }
            case GameRules.Level.LEVEL5:{
                ROWS = gridForLevel5.length;
                COLS =  gridForLevel5[0].length;
                return gridForLevel5;
            }
            case GameRules.Level.LEVEL6:{
                ROWS = gridForLevel6.length;
                COLS =  gridForLevel6[0].length;
                return gridForLevel6;
            }
            case GameRules.Level.LEVEL7:{
                ROWS = gridForLevel7.length;
                COLS =  gridForLevel7[0].length;
                return gridForLevel7;
            }
            case GameRules.Level.LEVEL8:{
                ROWS = gridForLevel8.length;
                COLS =  gridForLevel8[0].length;
                return gridForLevel8;
            }
            case GameRules.Level.LEVEL9:{
                ROWS = gridForLevel9.length;
                COLS =  gridForLevel9[0].length;
                return gridForLevel9;
            }
            case GameRules.Level.LEVEL10:{
                ROWS = gridForLevel10.length;
                COLS =  gridForLevel10[0].length;
                return gridForLevel10;
            }
            case GameRules.Level.LEVEL11:{
                ROWS = gridForLevel11.length;
                COLS =  gridForLevel11[0].length;
                return gridForLevel11;
            }
            case GameRules.Level.LEVEL12:{
                ROWS = gridForLevel12.length;
                COLS =  gridForLevel12[0].length;
                return gridForLevel12;
            }
            case GameRules.Level.LEVEL13:{
                ROWS = gridForLevel13.length;
                COLS =  gridForLevel13[0].length;
                return gridForLevel13;
            }
            case GameRules.Level.LEVEL14:{
                ROWS = gridForLevel14.length;
                COLS =  gridForLevel14[0].length;
                return gridForLevel14;
            }
            case GameRules.Level.LEVEL15:{
                ROWS = gridForLevel15.length;
                COLS =  gridForLevel15[0].length;
                return gridForLevel15;
            }
            case GameRules.Level.LEVEL16:{
                ROWS = gridForLevel16.length;
                COLS =  gridForLevel16[0].length;
                return gridForLevel16;
            }
            case GameRules.Level.LEVEL17:{
                ROWS = gridForLevel17.length;
                COLS =  gridForLevel17[0].length;
                return gridForLevel17;
            }
            case GameRules.Level.LEVEL18:{
                ROWS = gridForLevel18.length;
                COLS =  gridForLevel18[0].length;
                return gridForLevel18;
            }
            case GameRules.Level.LEVEL19:{
                ROWS = gridForLevel19.length;
                COLS =  gridForLevel19[0].length;
                return gridForLevel19;
            }
            case GameRules.Level.LEVEL20: {
                ROWS = gridForLevel20.length;
                COLS = gridForLevel20[0].length;
                return gridForLevel20;
            }
            case GameRules.Level.LEVEL21:{
                ROWS = gridForLevel21.length;
                COLS =  gridForLevel21[0].length;
                return gridForLevel21;
            }
            case GameRules.Level.LEVEL22:{
                ROWS = gridForLevel22.length;
                COLS =  gridForLevel22[0].length;
                return gridForLevel22;
            }
            case GameRules.Level.LEVEL23:{
                ROWS = gridForLevel23.length;
                COLS =  gridForLevel23[0].length;
                return gridForLevel23;
            }
            case GameRules.Level.LEVEL24:{
                ROWS = gridForLevel24.length;
                COLS =  gridForLevel24[0].length;
                return gridForLevel24;
            }
            case GameRules.Level.LEVEL25:{
                ROWS = gridForLevel25.length;
                COLS =  gridForLevel25[0].length;
                return gridForLevel25;
            }
            case GameRules.Level.LEVEL26:{
                ROWS = gridForLevel26.length;
                COLS =  gridForLevel26[0].length;
                return gridForLevel26;
            }
            case GameRules.Level.LEVEL27:{
                ROWS = gridForLevel27.length;
                COLS =  gridForLevel27[0].length;
                return gridForLevel27;
            }
            case GameRules.Level.LEVEL28:{
                ROWS = gridForLevel28.length;
                COLS =  gridForLevel28[0].length;
                return gridForLevel28;
            }
            case GameRules.Level.LEVEL29:{
                ROWS = gridForLevel29.length;
                COLS =  gridForLevel29[0].length;
                return gridForLevel29;
            }
            case GameRules.Level.LEVEL30:{
                ROWS = gridForLevel30.length;
                COLS =  gridForLevel30[0].length;
                return gridForLevel30;
            }
            case GameRules.Level.LEVEL31:{
                ROWS = gridForLevel31.length;
                COLS =  gridForLevel31[0].length;
                return gridForLevel31;
            }
            case GameRules.Level.LEVEL32:{
                ROWS = gridForLevel32.length;
                COLS =  gridForLevel32[0].length;
                return gridForLevel32;
            }
            case GameRules.Level.LEVEL33:{
                ROWS = gridForLevel33.length;
                COLS =  gridForLevel33[0].length;
                return gridForLevel33;
            }
            case GameRules.Level.LEVEL34:{
                ROWS = gridForLevel34.length;
                COLS =  gridForLevel34[0].length;
                return gridForLevel34;
            }
            case GameRules.Level.LEVEL35:{
                ROWS = gridForLevel35.length;
                COLS =  gridForLevel35[0].length;
                return gridForLevel35;
            }
            case GameRules.Level.LEVEL36:{
                ROWS = gridForLevel36.length;
                COLS =  gridForLevel36[0].length;
                return gridForLevel36;
            }
            case GameRules.Level.LEVEL37:{
                ROWS = gridForLevel37.length;
                COLS =  gridForLevel37[0].length;
                return gridForLevel37;
            }
            case GameRules.Level.LEVEL38:{
                ROWS = gridForLevel38.length;
                COLS =  gridForLevel38[0].length;
                return gridForLevel38;
            }
            case GameRules.Level.LEVEL39:{
                ROWS = gridForLevel39.length;
                COLS =  gridForLevel39[0].length;
                return gridForLevel39;
            }
            case GameRules.Level.LEVEL40: {
                ROWS = gridForLevel40.length;
                COLS = gridForLevel40[0].length;
                return gridForLevel40;
            }
            case GameRules.Level.LEVEL41:{
                ROWS = gridForLevel41.length;
                COLS =  gridForLevel41[0].length;
                return gridForLevel41;
            }
            case GameRules.Level.LEVEL42:{
                ROWS = gridForLevel42.length;
                COLS =  gridForLevel42[0].length;
                return gridForLevel42;
            }
            case GameRules.Level.LEVEL43:{
                ROWS = gridForLevel43.length;
                COLS =  gridForLevel43[0].length;
                return gridForLevel43;
            }
            case GameRules.Level.LEVEL44:{
                ROWS = gridForLevel44.length;
                COLS =  gridForLevel44[0].length;
                return gridForLevel44;
            }
            case GameRules.Level.LEVEL45:{
                ROWS = gridForLevel45.length;
                COLS =  gridForLevel45[0].length;
                return gridForLevel45;
            }
            case GameRules.Level.LEVEL46:{
                ROWS = gridForLevel46.length;
                COLS =  gridForLevel46[0].length;
                return gridForLevel46;
            }
            case GameRules.Level.LEVEL47:{
                ROWS = gridForLevel47.length;
                COLS =  gridForLevel47[0].length;
                return gridForLevel47;
            }
            case GameRules.Level.LEVEL48:{
                ROWS = gridForLevel48.length;
                COLS =  gridForLevel48[0].length;
                return gridForLevel48;
            }
            case GameRules.Level.LEVEL49:{
                ROWS = gridForLevel49.length;
                COLS =  gridForLevel49[0].length;
                return gridForLevel49;
            }
            case GameRules.Level.LEVEL50:{
                ROWS = gridForLevel50.length;
                COLS =  gridForLevel50[0].length;
                return gridForLevel50;
            }
            case GameRules.Level.LEVEL51:{
                ROWS = gridForLevel51.length;
                COLS =  gridForLevel51[0].length;
                return gridForLevel51;
            }
            case GameRules.Level.LEVEL52:{
                ROWS = gridForLevel52.length;
                COLS =  gridForLevel52[0].length;
                return gridForLevel52;
            }
            case GameRules.Level.LEVEL53:{
                ROWS = gridForLevel53.length;
                COLS =  gridForLevel53[0].length;
                return gridForLevel53;
            }
            case GameRules.Level.LEVEL54:{
                ROWS = gridForLevel54.length;
                COLS =  gridForLevel54[0].length;
                return gridForLevel54;
            }
            case GameRules.Level.LEVEL55:{
                ROWS = gridForLevel55.length;
                COLS =  gridForLevel55[0].length;
                return gridForLevel55;
            }
            case GameRules.Level.LEVEL56:{
                ROWS = gridForLevel56.length;
                COLS =  gridForLevel56[0].length;
                return gridForLevel56;
            }
            case GameRules.Level.LEVEL57:{
                ROWS = gridForLevel57.length;
                COLS =  gridForLevel57[0].length;
                return gridForLevel57;
            }
            case GameRules.Level.LEVEL58:{
                ROWS = gridForLevel58.length;
                COLS =  gridForLevel58[0].length;
                return gridForLevel58;
            }
            case GameRules.Level.LEVEL59:{
                ROWS = gridForLevel59.length;
                COLS =  gridForLevel59[0].length;
                return gridForLevel59;
            }
            case GameRules.Level.LEVEL60:{
                ROWS = gridForLevel60.length;
                COLS =  gridForLevel60[0].length;
                return gridForLevel60;
            }
            case GameRules.Level.LEVEL61:{
                ROWS = gridForLevel61.length;
                COLS =  gridForLevel61[0].length;
                return gridForLevel61;
            }
            case GameRules.Level.LEVEL62:{
                ROWS = gridForLevel62.length;
                COLS =  gridForLevel62[0].length;
                return gridForLevel62;
            }
            case GameRules.Level.LEVEL63:{
                ROWS = gridForLevel63.length;
                COLS =  gridForLevel63[0].length;
                return gridForLevel63;
            }
            case GameRules.Level.LEVEL64:{
                ROWS = gridForLevel64.length;
                COLS =  gridForLevel64[0].length;
                return gridForLevel64;
            }
            case GameRules.Level.LEVEL65:{
                ROWS = gridForLevel65.length;
                COLS =  gridForLevel65[0].length;
                return gridForLevel65;
            }
            case GameRules.Level.LEVEL66:{
                ROWS = gridForLevel66.length;
                COLS =  gridForLevel66[0].length;
                return gridForLevel66;
            }
            case GameRules.Level.LEVEL67:{
                ROWS = gridForLevel67.length;
                COLS =  gridForLevel67[0].length;
                return gridForLevel67;
            }
            case GameRules.Level.LEVEL68:{
                ROWS = gridForLevel68.length;
                COLS =  gridForLevel68[0].length;
                return gridForLevel68;
            }
            case GameRules.Level.LEVEL69:{
                ROWS = gridForLevel69.length;
                COLS =  gridForLevel69[0].length;
                return gridForLevel69;
            }
            case GameRules.Level.LEVEL70:{
                ROWS = gridForLevel70.length;
                COLS =  gridForLevel70[0].length;
                return gridForLevel70;
            }
            case GameRules.Level.LEVEL71:{
                ROWS = gridForLevel71.length;
                COLS =  gridForLevel71[0].length;
                return gridForLevel71;
            }
            case GameRules.Level.LEVEL72:{
                ROWS = gridForLevel72.length;
                COLS =  gridForLevel72[0].length;
                return gridForLevel72;
            }
            case GameRules.Level.LEVEL73:{
                ROWS = gridForLevel73.length;
                COLS =  gridForLevel73[0].length;
                return gridForLevel73;
            }
            case GameRules.Level.LEVEL74:{
                ROWS = gridForLevel74.length;
                COLS =  gridForLevel74[0].length;
                return gridForLevel74;
            }
            case GameRules.Level.LEVEL75:{
                ROWS = gridForLevel75.length;
                COLS =  gridForLevel75[0].length;
                return gridForLevel75;
            }
            case GameRules.Level.LEVEL76:{
                ROWS = gridForLevel76.length;
                COLS =  gridForLevel76[0].length;
                return gridForLevel76;
            }
            case GameRules.Level.LEVEL77:{
                ROWS = gridForLevel77.length;
                COLS =  gridForLevel77[0].length;
                return gridForLevel77;
            }
            case GameRules.Level.LEVEL78:{
                ROWS = gridForLevel78.length;
                COLS =  gridForLevel78[0].length;
                return gridForLevel78;
            }
            case GameRules.Level.LEVEL79:{
                ROWS = gridForLevel79.length;
                COLS =  gridForLevel79[0].length;
                return gridForLevel79;
            }
            case GameRules.Level.LEVEL80:{
                ROWS = gridForLevel80.length;
                COLS =  gridForLevel80[0].length;
                return gridForLevel80;
            }
            default:break;
        }
        return new int[0][];
    }

    public int getTotalNumberOfLevels(){
        return totalNumberOfLevels;
    }

    //updating status of levels
    public void updateLevelStatus(Context mContext){
        saveToPrefs(mContext, Constants.KEY_LEVEL,levelToPlay);
    }

    public int getGameLevelToPlay(Context mContext){
        if(gameTour){
            return 0;
        }else if(fromMenu) {
            levelToPlay = getHighestLevelCrossed(mContext) ;
        }
        return levelToPlay;
    }

    public void setGameLevelToPlay(int level){
            levelToPlay = level;
    }

    public int getHighestLevelCrossed(Context mContext){
      return getFromPrefs(mContext, Constants.KEY_LEVEL, 1);
    }

    public int getLastLevel(){
        return totalNumberOfLevels;
    }
}
