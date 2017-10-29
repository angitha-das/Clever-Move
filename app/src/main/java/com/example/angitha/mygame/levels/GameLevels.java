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

    /*
    0 invisible
    1 filled
    2 empty
     */
    private static int[][] gridForLevel1 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,2,1,1,2,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},

    };
    private static int[][] gridForLevel2 = {
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
    private static int[][] gridForLevel3 = {
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

    private static int[][] gridForLevel4 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,2,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,2,1,1,1,1,1,2,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
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
            case GameRules.Level.LEVEL1:return gridForLevel1;
            case GameRules.Level.LEVEL2:return gridForLevel2;
            case GameRules.Level.LEVEL3:return gridForLevel3;
            case GameRules.Level.LEVEL4:return gridForLevel4;
            case GameRules.Level.LEVEL5:return gridForLevel5;
//            case GameRules.Level.LEVEL6:return gridForLevel6;
//            case GameRules.Level.LEVEL7:return gridForLevel7;
//            case GameRules.Level.LEVEL8:return gridForLevel8;
//            case GameRules.Level.LEVEL9:return gridForLevel9;
//            case GameRules.Level.LEVEL10:return gridForLevel10;
//            case GameRules.Level.LEVEL11:return gridForLevel11;
//            case GameRules.Level.LEVEL12:return gridForLevel12;
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
        if(fromMenu) {
            levelToPlay = getHighestLevelCrossed(mContext) ;
        }else if(gameTour){
            return 1;
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
