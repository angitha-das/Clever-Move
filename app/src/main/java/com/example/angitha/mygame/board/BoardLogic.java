package com.example.angitha.mygame.board;

import android.support.annotation.NonNull;

import com.example.angitha.mygame.controller.GamePlayController;

/**
 * Created by angitha on 1/7/17.
 */

public class BoardLogic {

    private static final String TAG = GamePlayController.class.getName();

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
}
