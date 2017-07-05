package com.example.angitha.mygame.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableRow;

import com.example.angitha.mygame.BuildConfig;
import com.example.angitha.mygame.R;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.board.BoardLogic;
import com.example.angitha.mygame.view.BoardView;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.PegView;

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

    private final BoardView mTableLayout;


    private int score = 32;

    int height = dpToPixels(45);
    int width = dpToPixels(45);

    private BoardView[][] squares = new BoardView[7][7];
    private TableRow[] row  = new TableRow[7];
    private PegView[][] pieces = new PegView[7][7];


    /**
     * Game rules
     */
    @NonNull
    private final GameRules mGameRules;

    public GamePlayController(Context context, BoardView gameTableLayout, @NonNull GameRules mGameRules) {
        this.mContext = context;
        this.mGameRules = mGameRules;
        this.mTableLayout = gameTableLayout;
        initialize();
        if (mTableLayout != null) {
            mTableLayout.initialize(this, mGameRules,mGrid);
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


    public void exitGame() {
        ((GamePlayActivity) mContext).finish();
    }

    /**
     * restart game by resetting values and UI
     */
    public void restartGame() {
        initialize();
        mTableLayout.resetBoard();
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "Game restarted");
        }
    }

    /**
     * DragListener for PegLayouts in board, waits until something has been dragged over it
     *
     * @author chris
     *
     */
    public class SquareDragListener implements View.OnDragListener {

        Drawable defaultSquare = mContext.getResources().getDrawable(R.drawable.square);
        Drawable hoverSquare = mContext.getResources().getDrawable(R.drawable.square_hover);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            PegView view;
            BoardView oldSquare;
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
                    view = (PegView) event.getLocalState();
                    BoardView newSquare = (BoardView) v;
                    oldSquare = (BoardView) view.getParent();
                    if (view.move(oldSquare, newSquare, getSquares())) {
                        int score = getScore();
                        score--;
                        setScore(score);
                        updateTextViewScore();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    view = (PegView) event.getLocalState();
                    view.setVisibility(View.VISIBLE);
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
     * Can't set height and width as device independent pixels, so have to convert
     *
     * @param dps
     * @return pixels
     */
    public int dpToPixels(int dps) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    /**
     * Getter for array of PegLayouts which make up the board
     *
     * @return squares
     */
    public BoardView[][] getSquares() {
        return squares;

    }

    /**
     * Setter for score
     *
     * @param s score to be set to
     */
    public void setScore(int s) {
        score = s;
    }

    /**
     * Getter for score
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates score TextView and opens dialog if 1 Peg remaining
     *
     */
    public void updateTextViewScore() {

        int score = getScore();
        if (score == 1) {;
        }
    }
}