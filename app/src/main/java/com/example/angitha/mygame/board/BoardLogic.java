package com.example.angitha.mygame.board;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.example.angitha.mygame.controller.GamePlayController;

import java.util.ArrayList;

/**
 * Created by angitha on 1/7/17.
 */

public class BoardLogic {

    private static final String TAG = GamePlayController.class.getName();

    /**
     * Possible outcomes
     */
    public enum Outcome {
        NOTHING,WIN,LOSE
    }

    /**
     * number of columns in the mGrid
     */
    public final int numCols;

    /**
     * number of rows in the mGrid
     */
    private final int numRows;

    /**
     * Reference to a main mGrid
     */
    @NonNull
    private final int[][] mGrid;


    /**
     * reference to mFree cells in every column
     */
    private final int[][] mFree;

//    /**
//     * player win starting index
//     */
//    private int p, q;

//    /**
//     * winner direction
//     */
//    private int WIN_X = 0;
//    private int WIN_Y = 0;

    /**
     * win counter
     */
    private static final int COUNTERS_IN_MATCH = 1;


    /**
     * Initialise members
     *
     * @param grid reference to board grid
     * @param free reference to column height
     */
    public BoardLogic(@NonNull int[][] grid, int[][] free) {
        mGrid = grid;
        numRows = grid.length;
        numCols = grid[0].length;
        this.mFree = free;
    }

//    @NonNull
//    public Outcome checkWin() {
//        mDraw = true;
//        mCellValue = 0;
//        if (horizontalCheck() || verticalCheck()) {
//            return mCellValue == Player.PLAYER1 ? Outcome.PLAYER1_WINS : Outcome.PLAYER2_WINS;
//        }
//        // nobody won, return mDraw if it is, nothing if it's not
//        return mDraw ? Outcome.DRAW : Outcome.NOTHING;
//    }

//    private boolean horizontalCheck() {
//        // horizontalCheck
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numCols - 3; j++) {
//                mCellValue = mGrid[i][j];
//                if (mCellValue == 0) mDraw = false;
//                if (mCellValue != 0 && mGrid[i][j + 1] == mCellValue && mGrid[i][j + 2] == mCellValue && mGrid[i][j + 3] == mCellValue) {
//                    if (BuildConfig.DEBUG) {
//                        Log.e(TAG, "Horizontal check pass");
//                    }
//                    p = i;
//                    q = j;
//                    WIN_X = 1;
//                    WIN_Y = 0;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean verticalCheck() {
//        // verticalCheck
//        for (int j = 0; j < numCols; j++) {
//            for (int i = 0; i < numRows - 3; i++) {
//                mCellValue = mGrid[i][j];
//                if (mCellValue == 0) mDraw = false;
//                if (mCellValue != 0 && mGrid[i + 1][j] == mCellValue && mGrid[i + 2][j] == mCellValue && mGrid[i + 3][j] == mCellValue) {
//                    if (BuildConfig.DEBUG) {
//                        Log.e(TAG, "Vertical check pass");
//                    }
//                    p = i;
//                    q = j;
//                    WIN_X = 0;
//                    WIN_Y = 1;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    //#1
    /**
     * Returns sprites of a winning combination
     *
     * @param cells cell mGrid
     * @return winning move marble
     */
//    @NonNull
//    public ArrayList<ImageView> getWinMarble(ImageView[][] cells) {
//        ArrayList<ImageView> combination = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            combination.add(cells[p + WIN_Y * i][q + WIN_X * i]);
//        }
//        return combination;
//    }


    //#2
    /**
     * Returns sprites of a lose combination
     *
     * @param cells cell mGrid
     * @return lose move marbles
     */
//    @NonNull
//    public ArrayList<ImageView> getLoseMarble(ImageView[][] cells) {
//        ArrayList<ImageView> combination = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            combination.add(cells[p + WIN_Y * i][q + WIN_X * i]);
//        }
//        return combination;
//    }

    /**
     * placing a Move on the mGrid
     */
//    public void placeMove(int column, int player) {
//        if (mFree[column] > 0) {
//            mGrid[mFree[column] - 1][column] = player;
//            mFree[column]--;
//        }
//    }

    //#3
    /**
     * undo previous move
     *
     * @param column column to undo move
     */
//    public void undoMove(int column) {
//        if (mFree[column] < numRows) {
//            mFree[column]++;
//            mGrid[mFree[column] - 1][column] = 0;
//
//        }
//    }

    /**
     * Get the height the counters in a specific column
     *
     * @param index index of the column to check
     * @return the height of the column
     */
//    public int columnHeight(int index) {
//        return mFree[index];
//    }

    /**
     * display board status
     */
    public void displayBoard() {
        System.out.println();
        for (int i = 0; i <= 9; ++i) {
            for (int j = 0; j <= 9; ++j) {
                System.out.print(mGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
