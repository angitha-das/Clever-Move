package com.example.angitha.mygame.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.rules.GameRules;

import static com.example.angitha.mygame.controller.GamePlayController.COLS;
import static com.example.angitha.mygame.controller.GamePlayController.ROWS;

/**
 * Created by angitha on 1/7/17.
 */

public class BoardView extends RelativeLayout {

    private static final String TAG = GamePlayController.class.getName();
    private GameRules mGameRules;
    private GamePlayController mListener;
    private int[][] mBoardGrid;

    /**
     * Array to hold all discs dropped
     */
    private ImageView[][] mCells;

    private View mBoardView;

    private Context mContext;

    public ImageView[][] getCells() {
        return mCells;
    }


    public BoardView(Context context) {
        super(context);
        init(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        inflate(context, R.layout.game_board, this);
        mBoardView = findViewById(R.id.game_board);
    }

    public void initialize(GamePlayController gamePlayController, @NonNull GameRules gameRules, int[][] boardGrid) {
        this.mGameRules = gameRules;
        this.mListener = gamePlayController;
        this.mBoardGrid = boardGrid;
        buildCells();
    }

    /**
     * build and clear board mCells
     */
    private void buildCells() {
        mCells = new ImageView[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            ViewGroup row = (ViewGroup) ((ViewGroup) mBoardView).getChildAt(r);
            row.setClipChildren(false);
            for (int c = 0; c < COLS; c++) {
                ImageView imageView = (ImageView) row.getChildAt(c);
                if(mBoardGrid[r][c] == 0){
                    imageView.setImageResource(R.color.red);
                }else if(mBoardGrid[r][c] == 1){
                    imageView.setImageResource(R.drawable.peg);
                }else{
                    imageView.setImageResource(android.R.color.transparent);
                }
                imageView.setOnDragListener(new GamePlayController.PegDragListener);
                imageView.setOnTouchListener(new GamePlayController.PegTouchListener);
                mCells[r][c] = imageView;
            }
        }
    }

    /**
     * Reset board for new game same level
     */
    public void resetBoard() {
        buildCells();
    }
}
