package com.example.angitha.mygame.levels;

import android.content.Context;

import com.example.angitha.mygame.rules.GameRules;

import static com.example.angitha.mygame.utils.PrefUtils.getFromPrefs;
import static com.example.angitha.mygame.utils.PrefUtils.saveToPrefs;

/**
 * Created by angitha on 4/7/17.
 */

public class GameLevels {
    private int totalNumberOfLevels = 5;
    private static final String KEY_LEVEL = "levelCrossed";

    //clicked from levels activity.disable unlocked levels from click
    public int levelToPlay;
    public boolean fromMenu = false;
    public boolean gameTour = false;
    public static int ROWS;
    public static int COLS;


    /*
    0 invisible
    ic_launcher filled
    2 empty
     */
    private static int[][] gridForLevel1 = {
            {0,0,0,0,0,0},
            {0,2,1,1,2,0},
            {0,0,0,0,0,0}

    };
    private static int[][] gridForLevel2 = {

            {0,0,0,0,0,0},
            {0,2,1,1,0,0},
            {0,1,0,0,0,0},
            {0,2,1,2,0,0},
            {0,0,0,0,0,0}

    };
    private static int[][] gridForLevel3 = {
            {0,0,0,0,0,0},
            {0,1,1,1,0,0},
            {0,1,0,0,0,0},
            {0,2,1,2,1,0},
            {0,1,0,0,0,0},
            {0,0,0,0,0,0}

    };

    private static int[][] gridForLevel4 = {
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

    private static int[][] gridForLevel5 = {
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

    private static int[][] gridForLevel6 = {
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

    private static int[][] gridForLevel7 = {
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

    private static int[][] gridForLevel8 = {
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

    private static int[][] gridForLevel9 = {
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

    private static int[][] gridForLevel10 = {
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

    private static int[][] gridForLevel11 = {
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

    private static int[][] gridForLevel12 = {
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
//            case GameRules.Level.LEVEL6:{
//                ROWS = gridForLevel6.length;
//                COLS =  gridForLevel6[0].length;
//                return gridForLevel6;
//            }
//            case GameRules.Level.LEVEL7:{
//                ROWS = gridForLevel7.length;
//                COLS =  gridForLevel7[0].length;
//                return gridForLevel7;
//            }
//            case GameRules.Level.LEVEL8:{
//                ROWS = gridForLevel8.length;
//                COLS =  gridForLevel8[0].length;
//                return gridForLevel8;
//            }
//            case GameRules.Level.LEVEL9:{
//                ROWS = gridForLevel9.length;
//                COLS =  gridForLevel9[0].length;
//                return gridForLevel9;
//            }
//            case GameRules.Level.LEVEL10:{
//                ROWS = gridForLevel10.length;
//                COLS =  gridForLevel10[0].length;
//                return gridForLevel10;
//            }
//            case GameRules.Level.LEVEL11:{
//                ROWS = gridForLevel11.length;
//                COLS =  gridForLevel11[0].length;
//                return gridForLevel11;
//            }
//            case GameRules.Level.LEVEL12:{
//                ROWS = gridForLevel12.length;
//                COLS =  gridForLevel12[0].length;
//                return gridForLevel12;
//            }
            default:break;
        }
        return new int[0][];
    }

    public int getTotalNumberOfLevels(){
        return totalNumberOfLevels;
    }

    //updating status of levels
    public void updateLevelStatus(Context mContext){
        saveToPrefs(mContext,KEY_LEVEL,levelToPlay);
    }

    public int getGameLevelToPlay(Context mContext){
        if(gameTour){
            return 1;
        }else if(fromMenu && !gameTour) {
            levelToPlay = getHighestLevelCrossed(mContext) ;
        }
        return levelToPlay;
    }

    public void setGameLevelToPlay(int level){
            levelToPlay = level;
    }

    public int getHighestLevelCrossed(Context mContext){
      return getFromPrefs(mContext, KEY_LEVEL, 0);
    }

    public int getLastLevel(){
        return 5;
    }
}
