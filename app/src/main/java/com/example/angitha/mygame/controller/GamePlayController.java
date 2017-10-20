package com.example.angitha.mygame.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.BuildConfig;
import com.example.angitha.mygame.R;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.board.BoardLogic;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.view.BoardView;
import com.example.angitha.mygame.view.PegLayout;
import com.example.angitha.mygame.view.PegView;

import static com.example.angitha.mygame.levels.GameLevels.setGameBoard;


/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayController{

    private static final String TAG = GamePlayController.class.getName();
    /**
     * number of columns
     */
    private static final int COLS = 9;

    /**
     * number of rows
     */
    private static final int ROWS = 9;

    /**
     * mGrid, contains 0 for empty cell or player ID
     */
    private final int[][] mGrid = new int[ROWS][COLS];

    private final int[][] mGridCopy = new int[ROWS][COLS];
    /**
     * mScore, contains number of Pegs in the game level
     */
    private int mScore;
    private int mTotalScore;
    private TextView mLevelIndicator;
    private ImageView mPreviousLevel;
    private ImageView mNextLevel;
    private ImageView mUndoMove;
    private boolean undo = false;
    private boolean undoAnim = true;

    /**
     * current status
     */
    @NonNull
    private BoardLogic.Outcome mOutcome = BoardLogic.Outcome.NOTHING;

    private final Context mContext;
    private final BoardView mBoardView;

    private PegLayout[][] squares = new PegLayout[9][9] ;

    private GameLevels mGameLevels = GameLevels.getInstance();

    public GamePlayController(Context context, BoardView boardView
            , TextView levelIndicator, ImageView previousLevel,ImageView nextLevel,ImageView undoMove) {
        this.mContext = context;
        this.mBoardView = boardView;
        this.mLevelIndicator = levelIndicator;
        this.mPreviousLevel = previousLevel;
        this.mNextLevel = nextLevel;
        this.mUndoMove = undoMove;
        initialize();
        previousNextLevelSetup();
        setScore(mTotalScore);
        updateTextViewScore();
        if (mBoardView != null) {
            mBoardView.initialize(this,mGrid,new SquareDragListener(),new PegTouchListener());
        }
    }

    public boolean hideAnimationInUndo(){
        return undoAnim;
    }

    private void previousNextLevelSetup(){
        //Initialize Previous and next level icon
        mUndoMove.setEnabled(false);
        mUndoMove.setVisibility(View.INVISIBLE);
        mPreviousLevel.setVisibility((mGameLevels.getGameLevelToPlay(mContext) > 0)?View.VISIBLE:View.INVISIBLE);
        mNextLevel.setVisibility((mGameLevels.getGameLevelToPlay(mContext) < mGameLevels.getHighestLevelCrossed(mContext))?View.VISIBLE:View.INVISIBLE);
    }

    /**
     * initialize game board with default values and player turn
     */
    private void initialize() {
        // unfinished the game
        mTotalScore = 0;
        mOutcome = BoardLogic.Outcome.NOTHING;
        // initialize board as per level
        if(undo){
            undoAnim = false;
            mLevelIndicator.setText(String.format(" %d ", mGameLevels.getGameLevelToPlay(mContext)+1));
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    mGrid[r][c] = mGridCopy[r][c];
                    if (mGridCopy[r][c] == 1) {
                        ++mTotalScore;
                    }
                }
            }
        }else{
            undoAnim = true;
            int mLevelGrid[][] = setGameBoard(mGameLevels.getGameLevelToPlay(mContext));
            mLevelIndicator.setText(String.format(" %d ", mGameLevels.getGameLevelToPlay(mContext)+1));
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    mGrid[r][c] = mLevelGrid[r][c];
                    if (mLevelGrid[r][c] == 1) {
                        ++mTotalScore;
                    }
                }
            }
        }
    }

    public void exitGame() {
        ((GamePlayActivity) mContext).finish();
    }

    public void playPreviousGameLevel() {
        undo = false;
        mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)-1);
        mGameLevels.fromMenu = false;
        initialize();
        previousNextLevelSetup();
        setScore(mTotalScore);
        updateTextViewScore();
        mBoardView.resetBoard();
    }

    public void playNextGameLevel() {
        undo = false;
        mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)+1);
        mGameLevels.fromMenu = false;
        initialize();
        previousNextLevelSetup();
        setScore(mTotalScore);
        updateTextViewScore();
        mBoardView.resetBoard();
    }

    /**
     * restart game by resetting values and UI
     */
    public void restartGame() {
        undo=false;
        initialize();
        previousNextLevelSetup();
        setScore(mTotalScore);
        updateTextViewScore();
        mBoardView.resetBoard();
    }

    public void undoPreviousMove() {
        undo=true;
        initialize();
        previousNextLevelSetup();
        setScore(mTotalScore);
        updateTextViewScore();
        mBoardView.resetBoard();
    }

    private void setScore(int s) {
        mScore = s;
    }

    private int getScore() {
        return mScore;
    }

    /**
     * Updates score TextView and opens dialog if 1 Peg remaining
     *
     */
    private void updateTextViewScore() {
            if(getScore() == 1){
                if(mGameLevels.levelToPlay == mGameLevels.getHighestLevelCrossed(mContext)){
                    mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)+1);
                    mGameLevels.updateLevelStatus(mContext);
                }else{
                    mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)+1);
                    mGameLevels.levelToPlay = mGameLevels.getGameLevelToPlay(mContext);
                }
                saveGameLevelCompleted();
            }
    }
    private void saveGameLevelCompleted(){
        initialize();
        previousNextLevelSetup();
        setScore(mTotalScore);
        updateTextViewScore();
        mBoardView.resetBoard();
    }

    private void alertProceedToNextLevel(final int msgId, final int nowWhat) {
        new AlertDialog.Builder(mContext)
                .setTitle(msgId)
                .setCancelable(true)
                .setNeutralButton(nowWhat,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                        if(msgId == R.string.sorry_you_lost){
                            restartGame();
                        }
                    }
                }).show();
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
            PegLayout oldSquare;
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
                    PegLayout newSquare = (PegLayout) v;
                    oldSquare = (PegLayout) view.getParent();
                    if (view.move(oldSquare, newSquare, getSquares(),mGrid,mGridCopy)) {
                        mScore = getScore();
                        --mScore;
                        setScore(mScore);
                        updateTextViewScore();
                    }
                    if(!view.anyMoreMovesPossible(mGrid)){
                        if(mScore>1){
                            alertProceedToNextLevel(R.string.sorry_you_lost,R.string.yes);
                        }
                    }
                    if(getScore() < mTotalScore && getScore()>=2 && view.anyMoreMovesPossible(mGrid)){
                            mUndoMove.setEnabled(true);
                            mUndoMove.setVisibility(View.VISIBLE);
                    }else{
                        mUndoMove.setEnabled(false);
                        mUndoMove.setVisibility(View.INVISIBLE);
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
     * Getter for array of PegLayouts which make up the board
     *
     * @return squares
     */
    public PegLayout[][] getSquares() {
        return squares;
    }


}