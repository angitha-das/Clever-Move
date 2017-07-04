package com.example.angitha.mygame.levels;

import com.example.angitha.mygame.rules.GameRules;

/**
 * Created by angitha on 4/7/17.
 */

public class gameLevels {
    /*
    0 invisible
    1 filled
    2 empty
     */
    public static int[][] gridForLevel1 = {
            {0,0,2,1,1,2,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},

    };
    public static int[][] gridForLevel2 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,2,1,1,0,0,0},
            {0,0,0,1,0,0,0,0,0},
            {0,0,0,2,1,2,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},

    };
    public static int[][] gridForLevel3 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,1,0,0,0,0,0},
            {0,0,0,2,1,2,1,0,0},
            {0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},

    };
    public static int[][] setGameBoard(int Level){
        switch (Level) {
            case GameRules.Level.LEVEL1:return gridForLevel1;
            case GameRules.Level.LEVEL2:return gridForLevel2;
            case GameRules.Level.LEVEL3:return gridForLevel3;
            default:break;
        }
        return new int[0][];
    }
}
