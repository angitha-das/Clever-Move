package com.example.angitha.mygame.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.Pair;
import com.example.angitha.mygame.R;
import com.example.angitha.mygame.ThemePak;
import com.example.angitha.mygame.activity.GameCompleted;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.view.BoardView;
import com.example.angitha.mygame.view.PegLayout;
import com.example.angitha.mygame.view.PegView;

import java.util.Random;

import static com.example.angitha.mygame.levels.GameLevels.setGameBoard;


/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayController{

    private static final String TAG = GamePlayController.class.getName();
    /**
     * number of columns
     */
    private static int COLS =0;

    /**
     * number of rows
     */
    private static int ROWS =0;

    /**
     * mGrid, contains 0 for empty cell or player ID
     */
    private int[][] mGrid;

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
    private ConstraintLayout mGameBackground;
    private  ImageView step1;
    private  ImageView step2;
    private  ImageView step3;
    private TextView textView;
    private boolean undo = false;
    private boolean undoAnim = true;
    private Drawable emptySquare;
    private Drawable hoverSquare;
    private LayerDrawable cellDrawable;


    /**
     * current status
     */
    private final Context mContext;
    private final BoardView mBoardView;

    private PegLayout[][] squares = new PegLayout[ROWS][COLS] ;

    private GameLevels mGameLevels = GameLevels.getInstance();

    public GamePlayController(Context context, BoardView boardView
            , TextView levelIndicator, ImageView previousLevel, ImageView nextLevel, ImageView undoMove,ConstraintLayout gameBackground) {
        this.mContext = context;
        this.mBoardView = boardView;
        this.mLevelIndicator = levelIndicator;
        this.mPreviousLevel = previousLevel;
        this.mNextLevel = nextLevel;
        this.mUndoMove = undoMove;
        this.mGameBackground = gameBackground;

        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            if (mBoardView != null) {
                mBoardView.initialize(this, mGrid, new SquareDragListener(), new PegTouchListener());
            }
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
        }
    }

    public GamePlayController(Context context, BoardView boardView,ImageView step1, ImageView step2, ImageView step3,TextView textView,ConstraintLayout gameBackground) {
        this.mContext = context;
        this.mBoardView = boardView;
        this.mGameBackground = gameBackground;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.textView = textView;

        if(initialize()){
            setScore(mTotalScore);
            if (mBoardView != null) {
                mBoardView.initialize(this,mGrid,new SquareDragListener(),new PegTouchListener());
            }
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
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
     * initialize game board with default values and player turn
     */
    private boolean initialize() {
        // unfinished the game
        mTotalScore = 0;
        // initialize board as per level
        if(undo){
            ROWS = mGridCopy.length;
            COLS =  mGridCopy[0].length;
            mGrid = new int[ROWS][COLS];
            undoAnim = false;
            mLevelIndicator.setText(String.format(" %d ", mGameLevels.getGameLevelToPlay(mContext)+1));
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    mGrid[r][c] = mGridCopy[r][c];
                    if (mGridCopy[r][c] == 1) {
                        ++mTotalScore;
                    }
                }
            }
        }else {
            applyGameTheme();
            undoAnim = true;
            if (mGameLevels.getGameLevelToPlay(mContext) < mGameLevels.getLastLevel()) {
                int mLevelGrid[][] = setGameBoard(mGameLevels.getGameLevelToPlay(mContext));
                ROWS = mLevelGrid.length;
                COLS =  mLevelGrid[0].length;
                mGrid = new int[ROWS][COLS];
                if (!mGameLevels.gameTour) {
                    mLevelIndicator.setText(String.format(" %d ", mGameLevels.getGameLevelToPlay(mContext) + 1));
                }
                for (int r = 0; r < ROWS; r++) {
                    for (int c = 0; c < COLS; c++) {
                        mGrid[r][c] = mLevelGrid[r][c];
                        if (mLevelGrid[r][c] == 1) {
                            ++mTotalScore;
                        }
                    }
                }
            }else{
                mGameLevels.fromMenu=false;
                return false;
            }
        }
        return true;
    }

    private void applyGameTheme() {
        Random rand = new Random();
        int themeId = rand.nextInt(3);
        ThemePak mPak = ThemePak.getInstance();

        mGameBackground.setBackgroundColor(ContextCompat.getColor(mContext, mPak.getBackground(themeId)));
        hoverSquare = ThemePak.createSquareDrawable(ContextCompat.getColor(mContext,mPak.getHoverCellColor(themeId)),dpToPixels(2),dpToPixels(5));
        emptySquare = ThemePak.createSquareDrawable(ContextCompat.getColor(mContext, mPak.getEmptyCellColor(themeId)),dpToPixels(2),dpToPixels(5));
        cellDrawable =mPak.createDrawable(mContext,dpToPixels(5), ContextCompat.getColor(mContext, mPak.getPrimaryCellColor(themeId)),ContextCompat.getColor(mContext,  mPak.getSecondaryCellColor(themeId)));

    }

    public void exitGame() {
        ((GamePlayActivity) mContext).finish();
    }

    public void playPreviousGameLevel() {
        undo = false;
        mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)-1);
        mGameLevels.fromMenu = false;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.resetBoard();
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
        }
    }

    public void playNextGameLevel() {
        undo = false;
        mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)+1);
        mGameLevels.fromMenu = false;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.resetBoard();
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
        }
    }

    /**
     * restart game by resetting values and UI
     */
    public void restartGame() {
        undo=false;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.resetBoard();
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
        }
    }

    public void undoPreviousMove() {
        undo=true;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.resetBoard();
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
        }
    }

    private void setScore(int s) {
        mScore = s;
    }

    public int getScore() {
        return mScore;
    }

    /**
     * Updates score TextView and opens dialog if 1 Peg remaining
     *
     */
    private void updateTextViewScore() {
        if (!mGameLevels.gameTour) {
            if (getScore() == 1) {
                if (mGameLevels.levelToPlay == mGameLevels.getHighestLevelCrossed(mContext)) {
                    mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext) + 1);
                    mGameLevels.updateLevelStatus(mContext);
                } else {
                    mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext) + 1);
                    mGameLevels.levelToPlay = mGameLevels.getGameLevelToPlay(mContext);
                }
                saveGameLevelCompleted();
            }
        }
    }

    private void saveGameLevelCompleted(){
        undo = false;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.resetBoard();
        }else{
            mContext.startActivity(new Intent(mContext, GameCompleted.class));
            exitGame();
        }
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

        @Override
        public boolean onDrag(View v, DragEvent event) {
            PegView view;
            PegLayout oldSquare;
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                 case DragEvent.ACTION_DROP:
				    /*
				    * When Peg is dropped move method is called and score is updated
				    */
                    view = (PegView) event.getLocalState();
                    PegLayout newSquare = (PegLayout) v;
                    //if(view.getparent()!=null){}
                    oldSquare = (PegLayout) view.getParent();
                    if (view.move(oldSquare, newSquare, getSquares(),mGrid,mGridCopy)) {
                        mScore = getScore();
                        --mScore;
                        setScore(mScore);
                        if(mGameLevels.gameTour) {
                            if (getScore() == 3) {
                                step1.setVisibility(View.INVISIBLE);
                                step2.setVisibility(View.VISIBLE);
                            } else if (getScore() == 2) {
                                step1.setVisibility(View.INVISIBLE);
                                step2.setVisibility(View.INVISIBLE);
                                step3.setVisibility(View.VISIBLE);
                            } else {
                                step1.setVisibility(View.INVISIBLE);
                                step2.setVisibility(View.INVISIBLE);
                                step3.setVisibility(View.INVISIBLE);
                                textView.setVisibility(View.VISIBLE);
                            }
                        }
                        if(!mGameLevels.gameTour && mUndoMove !=null){
                            if(getScore() <= mTotalScore && getScore()>=2 && view.anyMoreMovesPossible(mGrid)){
                                mUndoMove.setEnabled(true);
                                mUndoMove.setVisibility(View.VISIBLE);
                            }else{
                                mUndoMove.setEnabled(false);
                                mUndoMove.setVisibility(View.INVISIBLE);
                            }
                        }
                        updateTextViewScore();
                    }
                    if(!view.anyMoreMovesPossible(mGrid)){
                        if(mScore>1){
                            alertProceedToNextLevel(R.string.sorry_you_lost,R.string.yes);
                        }
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    view = (PegView) event.getLocalState();
                    view.setVisibility(View.VISIBLE);
                    v.setBackgroundDrawable(emptySquare);

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
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                PegView chosenSquare = (PegView) v;
                PegLayout[][] squares = getSquares();
                Pair[] allPredictions  = chosenSquare.predict(chosenSquare, mGrid);
                for (Pair allPrediction : allPredictions) {
                    if (allPrediction != null) {
                        int x = allPrediction.getI();
                        int y = allPrediction.getJ();
                        squares[x][y].setBackgroundDrawable(hoverSquare);
                    }
                }

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