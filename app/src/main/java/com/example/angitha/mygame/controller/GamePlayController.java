package com.example.angitha.mygame.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.Pair;
import com.example.angitha.mygame.R;
import com.example.angitha.mygame.ThemePak;
import com.example.angitha.mygame.activity.GamePlayActivity;
import com.example.angitha.mygame.activity.LevelsRecyclerActivity;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.utils.Constants;
import com.example.angitha.mygame.utils.PrefUtils;
import com.example.angitha.mygame.utils.Utils;
import com.example.angitha.mygame.view.BoardView;
import com.example.angitha.mygame.view.PegLayout;
import com.example.angitha.mygame.view.PegView;

import java.util.Random;

import static com.example.angitha.mygame.levels.GameLevels.COLS;
import static com.example.angitha.mygame.levels.GameLevels.ROWS;
import static com.example.angitha.mygame.levels.GameLevels.setGameBoard;
import static com.example.angitha.mygame.view.PegView.mGridCopy;


/**
 * Created by angitha on 1/7/17.
 */

public class GamePlayController{

    /**
     * mGrid, contains 0 for empty cell or player ID
     */
    private int[][] mGrid;

    /**
     * mScore, contains number of Pegs in the game level
     */
    private int mScore;
    private int mTotalScore;
    private TextView mLevelIndicator;
    private ImageView mPreviousLevel;
    private ImageView mNextLevel;
    private ImageView mUndoMove;
    private ImageView mCloseButton;
    private ImageView mRefresh;
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

    MediaPlayer successMove,failMove,levelCompleted,levelLost;



    /**
     * current status
     */
    private final Context mContext;
    private final BoardView mBoardView;
    private PegLayout[][] squares;
    private GameLevels mGameLevels = GameLevels.getInstance();

    public GamePlayController(Context context, BoardView boardView
            , TextView levelIndicator, ImageView previousLevel, ImageView nextLevel, ImageView undoMove,ImageView close, ImageView refresh,ConstraintLayout gameBackground) {
        this.mContext = context;
        this.mBoardView = boardView;
        this.mLevelIndicator = levelIndicator;
        this.mPreviousLevel = previousLevel;
        this.mNextLevel = nextLevel;
        this.mUndoMove = undoMove;
        this.mCloseButton = close;
        this.mRefresh = refresh;
        this.mGameBackground = gameBackground;
        initializeTunes();

        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            if (mBoardView != null) {
                mBoardView.initialize(this, mGrid, new SquareDragListener(), new PegTouchListener());
            }
        }else{
            completedAllLevels();
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
        initializeTunes();

        if(initialize()){
            setScore(mTotalScore);
            if (mBoardView != null) {
                mBoardView.initialize(this,mGrid,new SquareDragListener(),new PegTouchListener());
            }
        }else{
            completedAllLevels();
        }

    }

    public void manageSound(){
        if(PrefUtils.getMuteStatus(mContext, Constants.MUTE_SOUND,false)){
            successMove.setVolume(0,0);
            failMove.setVolume(0,0);
            levelCompleted.setVolume(0,0);
            levelLost.setVolume(0,0);
        }else{
            successMove.setVolume(1,1);
            failMove.setVolume(1,1);
            levelCompleted.setVolume(1,1);
            levelLost.setVolume(1,1);
        }
    }


    private void initializeTunes(){
        successMove= MediaPlayer.create(mContext,R.raw.success_move);
        failMove= MediaPlayer.create(mContext,R.raw.fail_move);
        levelCompleted= MediaPlayer.create(mContext,R.raw.level_complete);
        levelLost = MediaPlayer.create(mContext,R.raw.lost_game);
    }

    public boolean hideAnimationInUndo(){
        return undoAnim;
    }

    private void previousNextLevelSetup(){
        //Initialize Previous and next level icon
        showAllViewsInBackground();
        mUndoMove.setEnabled(false);
        mUndoMove.setVisibility(View.INVISIBLE);
        mPreviousLevel.setVisibility((mGameLevels.getGameLevelToPlay(mContext) > 0)?View.VISIBLE:View.INVISIBLE);
        mNextLevel.setVisibility((mGameLevels.getGameLevelToPlay(mContext) < mGameLevels.getHighestLevelCrossed(mContext) && (mGameLevels.getGameLevelToPlay(mContext) != mGameLevels.getLastLevel()))?View.VISIBLE:View.INVISIBLE);
    }

    /**
     * Can't set height and width as device independent pixels, so have to convert
     *
     * @param dps
     * @return pixels
     */
    private int dpToPixels(int dps) {
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
            mGrid = new int[ROWS][COLS];
            squares = new PegLayout[ROWS][COLS];

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
            if (!mGameLevels.gameTour) {
                applyGameTheme();
            }else{
                applyGameTourBackground();
            }
            undoAnim = true;
            if (mGameLevels.getGameLevelToPlay(mContext) < mGameLevels.getLastLevel()) {
                int mLevelGrid[][] = setGameBoard(mGameLevels.getGameLevelToPlay(mContext));

                mGrid = new int[ROWS][COLS];
                squares = new PegLayout[ROWS][COLS];

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

    private void applyGameTourBackground(){
        ThemePak mPak = ThemePak.getInstance();
        hoverSquare = ThemePak.createSquareDrawable(ContextCompat.getColor(mContext,R.color.colorWhite),dpToPixels(2),dpToPixels(5));
        emptySquare = ThemePak.createSquareDrawable(ContextCompat.getColor(mContext, R.color.lightBlue),dpToPixels(2),dpToPixels(5));
        cellDrawable =mPak.createDrawable(mContext,dpToPixels(5), ContextCompat.getColor(mContext, R.color.pink_dark),ContextCompat.getColor(mContext,  R.color.pink));
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

    private void hideAllViewsInBackground(){
        mBoardView.setVisibility(View.INVISIBLE);
        mCloseButton.setVisibility(View.INVISIBLE);
        mUndoMove.setVisibility(View.INVISIBLE);
        mRefresh.setVisibility(View.INVISIBLE);
        mLevelIndicator.setVisibility(View.INVISIBLE);
        mPreviousLevel.setVisibility(View.INVISIBLE);
        mNextLevel.setVisibility(View.INVISIBLE);
    }

    private void showAllViewsInBackground(){
        mBoardView.setVisibility(View.VISIBLE);
        mCloseButton.setVisibility(View.VISIBLE);
        mUndoMove.setVisibility(View.VISIBLE);
        mRefresh.setVisibility(View.VISIBLE);
        mLevelIndicator.setVisibility(View.VISIBLE);
        mPreviousLevel.setVisibility(View.VISIBLE);
        mNextLevel.setVisibility(View.VISIBLE);
    }

    public void playPreviousGameLevel() {
        undo = false;
        mGameLevels.setGameLevelToPlay(mGameLevels.getGameLevelToPlay(mContext)-1);
        mGameLevels.fromMenu = false;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.buildCells(mGrid);
        }else{
            completedAllLevels();
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
            mBoardView.buildCells(mGrid);
        }else{
            completedAllLevels();
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
            mBoardView.buildCells(mGrid);
        }else{
            completedAllLevels();
        }
    }

    public void undoPreviousMove() {
        undo=true;
        if(initialize()) {
            previousNextLevelSetup();
            setScore(mTotalScore);
            updateTextViewScore();
            mBoardView.buildCells(mGrid);
        }else{
            completedAllLevels();
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
            mBoardView.buildCells(mGrid);
        }else{
            completedAllLevels();
        }
    }

    private void completedAllLevels() {
        levelCompleted.start();
        hideAllViewsInBackground();
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.game_over_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button share =  dialog.findViewById(R.id.share);
        Button close = dialog.findViewById(R.id.close);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitGame();
                dialog.dismiss();
                Utils.shareApp(view.getContext());

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitGame();
                dialog.dismiss();
                Intent intent = new Intent(mContext, LevelsRecyclerActivity.class);
                mContext.startActivity(intent);
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void alertRetryLevel() {
        hideAllViewsInBackground();
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.alert_retry_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView retry =  dialog.findViewById(R.id.retry);
        ImageView close = dialog.findViewById(R.id.close);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                restartGame();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                exitGame();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
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
                    oldSquare = (PegLayout) view.getParent();
                    if (view.move(oldSquare, newSquare, getSquares(),mGrid)) {
                        mScore = getScore();
                        --mScore;
                        if(mScore >1){
                            successMove.start();
                        }else{
                            levelCompleted.start();
                        }
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
                            levelLost.start();
                            alertRetryLevel();
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
                if(chosenSquare.cannotPredict(allPredictions)){
                    failMove.start();
                }
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