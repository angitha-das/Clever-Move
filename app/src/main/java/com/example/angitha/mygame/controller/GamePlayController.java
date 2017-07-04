package com.example.angitha.mygame.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.angitha.mygame.BuildConfig;
import com.example.angitha.mygame.R;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.board.BoardLogic;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.BoardView;

import static com.example.angitha.mygame.levels.gameLevels.gridForLevel1;
import static com.example.angitha.mygame.levels.gameLevels.setGameBoard;


/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayController{

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
    public final int[][] mGrid = new int[ROWS][COLS];

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
            mBoardView.initialize(this, mGameRules,mGrid);
        }
    }


    /**
     * initialize game board with default values and player turn
     */
    private void initialize() {
        // unfinished the game
        mFinished = false;
        mOutcome = BoardLogic.Outcome.NOTHING;

        // initialize board as per level
        int mLevelGrid[][] = setGameBoard(mGameRules.getRule(GameRules.LEVEL));

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                mGrid[r][c] = mLevelGrid[r][c];
            }
        }
    }



    /**
     * Drop a disc of the current player at available row of selected column
     *
     * @param col column to drop disc
     * @param row row of the column
     */
    public void moveMarble(int row, int col, final int playerTurn) {
//        final ImageView cell = mCells[row][col];
//        float move = -(cell.getHeight() * row + cell.getHeight() + 15);
//        cell.setY(move);
//        cell.setImageResource(mGameRules.getRule(GameRules.DISC));
//        cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
    }

    public static class PegDragListener implements View.OnDragListener {
        Drawable defaultSquare = mContext.getResources().getDrawable(R.drawable.square);
        Drawable hoverSquare = mContext.getResources().getDrawable(R.drawable.square_hover);
        @Override
        public boolean onDrag(View v, DragEvent event) {
            BoardView boardView;
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(hoverSquare);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(defaultSquare);
                    break;
                case DragEvent.ACTION_DROP:
				/*
				 * When Peg is dropped move method is called and score is updated
				 */
                    boardView = (BoardView) event.getLocalState();
//                    PegLayout newSquare = (PegLayout) v;
//                    oldSquare = (PegLayout) view.getParent();
//                    if (boardView.move(oldSquare, newSquare, getSquares())) {
//                        int score = getScore();
//                        score--;
//                        setScore(score);
//                        updateTextViewScore();
//                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    boardView = (BoardView) event.getLocalState();
                    boardView.setVisibility(View.VISIBLE);
                    v.setBackgroundDrawable(defaultSquare);
                default:
                    break;
            }
            return true;
        }

    }

    /**
     * Touch listener for dragging of PegViews
     * Calls startDrag() which is used in the DragListener
     * Pegs are dragged into PegLayouts which are assigned to DragListeners
     *
     * @author chris
     *
     */
    public final class PegTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == event.ACTION_DOWN) {
                //ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(null, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * Move method, takes original square and new square to go to as well as array of PegLayouts in board
     * Checks whether square to move to is empty and in right place as well as whether there is a Peg to jump over
     * Sets new row and col values for PegView
     *
     * @param oldSquare
     * @param newSquare
     * @param squares
     * @return bool
     */
//    public boolean move(PegLayout oldSquare, PegLayout newSquare, PegLayout[][] squares) {
//
//        int newRow = newSquare.getRow();
//        int newCol = newSquare.getColumn();
//
//        int oldRow = oldSquare.getRow();
//        int oldCol = oldSquare.getColumn();
//
//
//        if (newSquare.isEmpty()) {
//            if (((Math.abs(newRow - oldRow) == 2) && (newCol == oldCol)) ||
//                    (Math.abs(newCol - oldCol) == 2) && (newRow == oldRow)) {
//                if ((oldCol - newCol == -2) && (!squares[newRow][newCol - 1].isEmpty())) {
//                    squares[newRow][newCol - 1].removeAllViews();
//                } else if ((oldCol - newCol == 2) && (!squares[newRow][newCol + 1].isEmpty())) {
//                    squares[newRow][newCol + 1].removeAllViews();
//                } else if ((oldRow - newRow == -2) && (!squares[newRow - 1][newCol].isEmpty())) {
//                    squares[newRow - 1][newCol].removeAllViews();
//                } else if ((oldRow - newRow == 2) && (!squares[newRow + 1][newCol].isEmpty())) {
//                    squares[newRow + 1][newCol].removeAllViews();
//                } else {
//                    return false;
//                }
//                oldSquare.removeView(this);
//                newSquare.addView(this);
//                this.row = newRow;
//                this.col = newCol;
//                this.setVisibility(View.VISIBLE);
//                return true;
//            }
//
//        }
//        return false;
//    }



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