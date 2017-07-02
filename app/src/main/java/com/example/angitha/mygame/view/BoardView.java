package com.example.angitha.mygame.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
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

    public void initialize(GamePlayController gamePlayController, @NonNull GameRules gameRules) {
        this.mGameRules = gameRules;
        this.mListener = gamePlayController;
        buildCells();
    }

    /**
     * build and clear board mCells
     */
    private void buildCells() {
        mCells = new ImageView[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            ViewGroup row = (ViewGroup) ((ViewGroup) mBoardView).getChildAt(r);
            //row.setClipChildren(false);
            for (int c = 0; c < COLS; c++) {
                ImageView imageView = (ImageView) row.getChildAt(c);
                imageView.setImageResource(android.R.color.transparent);
                imageView.setOnClickListener(mListener);
                mCells[r][c] = imageView;
            }
        }
    }

    /**
     * get row from touch
     *
     * @param x touch location
     * @return row from  the location(0..8)
     */
    public int gridAt_x(float x) {
        float rowHeight = mCells[0][0].getHeight();
        int row = (int) x / (int) rowHeight;
        if (row < 0)
            return 0;
        if (row > 9)
            return 9;
        return row;
    }

    /**
     * get column from touch
     *
     * @param y touch location
     * @return column from  the location(0..8)
     */
    public int gridAt_y(float y) {
        float colWidth = mCells[0][0].getWidth();
        int col = (int) y / (int) colWidth;
        if (col < 0)
            return 0;
        if (col > 9)
            return 9;
        return col;
    }


    /**
     * Reset boar for new game
     */
    public void resetBoard() {
        //clear board mCells
        for (ImageView[] cell : mCells) {
            for (ImageView imageView : cell) {
                imageView.setImageResource(android.R.color.transparent);
            }
        }
//        showWinStatus(BoardLogic.Outcome.NOTHING, null);
    }

    /**
     * Drop a disc of the current player at available row of selected column
     *
     * @param col column to drop disc
     * @param row row of the column
     */
    public void moveMarble(int row, int col, final int playerTurn) {
        final ImageView cell = mCells[row][col];
        float move = -(cell.getHeight() * row + cell.getHeight() + 15);
        cell.setY(move);
        cell.setImageResource(mGameRules.getRule(GameRules.DISC));
        cell.animate().translationY(0).setInterpolator(new BounceInterpolator()).start();
    }


//    /**
//     * Update UI with winning status
//     *
//     * @param outcome  winning status
//     * @param winDiscs winning move discs
//     */
//    public void showWinStatus(@NonNull BoardLogic.Outcome outcome, @NonNull ArrayList<ImageView> winDiscs) {
//        if (BuildConfig.DEBUG) {
//            Log.e(TAG, outcome.name());
//        }
//        if (outcome != BoardLogic.Outcome.NOTHING) {
//            switch (outcome) {
//                case WIN:
//                    break;
//                case LOSE:
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
}
