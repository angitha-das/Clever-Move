package com.example.angitha.mygame.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.ThemePak;
import com.example.angitha.mygame.controller.GamePlayController;

/**
 * Created by angitha on 1/7/17.
 */

public class BoardView extends TableLayout {

	private int Row = 0;
	private int Col = 0;

	private TableRow[] row;
	private PegLayout[][] squares;
	private PegView[][] pieces ;

	private Drawable emptySquare;
	private LayerDrawable cellDrawable;

	private Context mContext;

	private int[][] mBoardMatrix;
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
		this.mBoardMatrix = boardMatrix;
		this.squares = gamePlayController.getSquares();
		this.gamePlayController = gamePlayController;
		this.Row = mBoardMatrix.length;
		this.Col = mBoardMatrix[0].length;
		buildCells();
	}

	/*
	* Loops through rows and columns creating TableRows and the views inside them,
	* Sets rows and columns of PegLayouts and PegViews
	*/

	private void buildCells() {
		init();
		removeAllViewsInLayout();
		int height = (((Activity) getContext()).getWindowManager()
				.getDefaultDisplay().getWidth())/mBoardMatrix.length;
		int width = (((Activity) getContext()).getWindowManager()
				.getDefaultDisplay().getWidth())/mBoardMatrix.length;

		row  = new TableRow[Row];
		pieces = new PegView[Row][Col];


		for (int r = 0; r < Row; r++) {
			row[r] = new TableRow(mContext);
			for (int c = 0; c < Col; c++) {
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
		for (int r = 0; r < Row; r++) {
			for (int c = 0; c < Col; c++) {
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

	/**
	 * Reset board for new game same level
	 */
	public void resetBoard() {
		buildCells();
	}
}
