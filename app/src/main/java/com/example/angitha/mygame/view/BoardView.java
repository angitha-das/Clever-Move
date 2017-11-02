package com.example.angitha.mygame.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.ThemePak;
import com.example.angitha.mygame.controller.GamePlayController;

import static com.example.angitha.mygame.levels.GameLevels.COLS;
import static com.example.angitha.mygame.levels.GameLevels.ROWS;

/**
 * Created by angitha on 1/7/17.
 */

public class BoardView extends TableLayout {

	private int[][] mBoardMatrix;
	private TableRow[] row;
	private PegLayout[][] squares;
	private PegView[][] pieces ;

	private Drawable emptySquare;
	private LayerDrawable cellDrawable;

	private Context mContext;


	private GamePlayController gamePlayController;

	private GamePlayController.SquareDragListener squareDragListener;
	private GamePlayController.PegTouchListener pegTouchListener;


	public BoardView(Context context) {
		super(context);
		this.mContext = context;
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}


	private void init() {
		ThemePak mPak = ThemePak.getInstance();
		emptySquare = mPak.getSquareDrawable();
		cellDrawable = (LayerDrawable) mPak.getCellDrawable();
	}

	public void initialize(GamePlayController gamePlayController, int[][] boardMatrix,
						    GamePlayController.SquareDragListener squareDragListener, GamePlayController.PegTouchListener pegTouchListener) {
		this.squareDragListener = squareDragListener;
		this.pegTouchListener = pegTouchListener;
		this.gamePlayController = gamePlayController;
		buildCells(boardMatrix);
	}

	/*
	* Loops through rows and columns creating TableRows and the views inside them,
	* Sets rows and columns of PegLayouts and PegViews
	*/

	public void buildCells(int[][] boardMatrix) {
		this.mBoardMatrix = boardMatrix;
		this.squares = gamePlayController.getSquares();
		init();
		removeAllViewsInLayout();
		int height = (((Activity) getContext()).getWindowManager()
				.getDefaultDisplay().getWidth())/COLS;
		int width = (((Activity) getContext()).getWindowManager()
				.getDefaultDisplay().getWidth())/COLS;

		row  = new TableRow[ROWS];
		pieces = new PegView[ROWS][COLS];


		for (int r = 0; r < ROWS; r++) {
			row[r] = new TableRow(mContext);
			for (int c = 0; c < COLS; c++) {
				if (!(mBoardMatrix[r][c] == 0)) {
					squares[r][c] = new PegLayout(mContext, r, c);
					squares[r][c].setBackgroundDrawable(emptySquare);
					squares[r][c].setPadding(0,0,0,0);
					squares[r][c].setOnDragListener(squareDragListener);
					if (mBoardMatrix[r][c] == 1) {
						pieces[r][c] = new PegView(mContext, r, c);
						pieces[r][c].setBackgroundDrawable(cellDrawable);
						pieces[r][c].setPadding(0,0,0,0);
						pieces[r][c].setLayoutParams(new ViewGroup.LayoutParams(width,height));
						pieces[r][c].setOnTouchListener(pegTouchListener);
						squares[r][c].addView(pieces[r][c]);
					}
					row[r].addView(squares[r][c]);
					TableRow.LayoutParams params = (TableRow.LayoutParams)squares[r][c].getLayoutParams();
					params.column = c;
					params.setMargins(2,2,2,2);
					params.height = height;
					params.width = width;
					squares[r][c].setLayoutParams(params);
				}
			}
			addView(row[r], new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				PegView pegView = pieces[r][c];
				if(pegView !=null){
					if(gamePlayController.hideAnimationInUndo()){
						Animation animation1 =
								AnimationUtils.loadAnimation(getContext(),R.anim.cell_popup);
						pegView.startAnimation(animation1);
					}
				}
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
		float scale = getResources().getDisplayMetrics().density;
		int pixels = (int) (dps * scale + 0.5f);
		return pixels;
	}

}
