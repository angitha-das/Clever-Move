package com.example.angitha.mygame.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.angitha.mygame.BuildConfig;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.board.BoardLogic;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.BoardView;


/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayController implements View.OnClickListener {

    private static final String TAG = GamePlayController.class.getName();
    /**
     * number of columns
     */
    public static final int COLS = 9;

    /**
     * number of rows
     */
    public static final int ROWS = 9;

    /**
     * mGrid, contains 0 for empty cell or player ID
     */
    private final int[][] mGrid = new int[ROWS][COLS];

    /**
     * mFree cells in every column
     */
    private final int[][] mFree = new int[ROWS][COLS];

    /**
     * board mBoardLogic (winning check)
     */
    private final BoardLogic mBoardLogic = new BoardLogic(mGrid, mFree);

    /**
     * current status
     */
    @NonNull
    private BoardLogic.Outcome mOutcome = BoardLogic.Outcome.NOTHING;

    /**
     * if the game is mFinished
     */
    private boolean mFinished = true;

    private final Context mContext;

    private final BoardView mBoardView;

    /**
     * Game rules
     */
    @NonNull
    private final GameRules mGameRules;

    public GamePlayController(Context context, BoardView boardView, @NonNull GameRules mGameRules) {
        this.mContext = context;
        this.mGameRules = mGameRules;
        this.mBoardView = boardView;
        initialize();
        if (mBoardView != null) {
            mBoardView.initialize(this, mGameRules);
        }
    }


    /**
     * initialize game board with default values and player turn
     */
    private void initialize() {
        // unfinished the game
        mFinished = false;
        mOutcome = BoardLogic.Outcome.NOTHING;

        // create levels
//        if (mGameRules.getRule(GameRules.LEVEL) == GameRules.LEVEL) {
//            switch (mGameRules.getRule(GameRules.LEVEL)) {
//                case GameRules.Level.LEVEL1:
//                    break;
//                case GameRules.Level.LEVEL2:
//                    break;
//                case GameRules.Level.LEVEL3:
//                    break;
//                default:
//                    break;
//            }
//        }

        /* Set mGrid as per level
        Level 1:
        0 invisible,1 empty,2 filled
    */
        int[][] gridForLevel1 = {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mGrid[i][j] = gridForLevel1[i][j];
            }
        }
    }


    @Override
    public void onClick(@NonNull View view) {
        if (mFinished) return;
        int row = mBoardView.gridAt_x(view.getX());
        int col = mBoardView.gridAt_y(view.getY());
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "Selected row,column: " + row + "," + col);
        }
        selectRowColumn(col);
    }

    /**
     * drop disc into a row , column
     *
     * @param column column to drop disc
     */
    private void selectRowColumn(int column) {

//        if (mFree[row][column] == 0) {
//            if (BuildConfig.DEBUG) {
//                Log.e(TAG, "full column or game is mFinished");
//            }
//            return;
//        }
//
//        mBoardLogic.placeMove(column);
//
//        // put disc
//        mBoardView.dropDisc(mFree[column], column);
//
//        // check if someone has won
//        checkForWin();
//    }
}


    /**
     * execute board mBoardLogic for win check and update ui
     */
    private void checkForWin() {
//        mOutcome = mBoardLogic.checkWin();
//
//        if (mOutcome != BoardLogic.Outcome.NOTHING) {
//            mFinished = true;
//            ArrayList<ImageView> winDiscs = mBoardLogic.getWinDiscs(mBoardView.getCells());
//            mBoardView.showWinStatus(mOutcome, winDiscs);
    }


    public void exitGame() {
        ((GamePlayActivity) mContext).finish();
    }

    /**
     * restart game by resetting values and UI
     */
    public void restartGame() {
        initialize();
        mBoardView.resetBoard();
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "Game restarted");
        }
    }


}