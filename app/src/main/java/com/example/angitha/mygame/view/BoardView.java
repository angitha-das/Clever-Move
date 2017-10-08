package com.example.angitha.mygame.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.rules.GameRules;

/**
 * Created by angitha on 1/7/17.
 */

public class BoardView extends TableLayout {

	private static final String TAG = GamePlayController.class.getName();

	private int Row = 9;
	private int Col = 9;

	private TableRow[] row  = new TableRow[9];
	private PegLayout[][] squares;
	private PegView[][] pieces = new PegView[9][9];
	private Drawable defaultSquare;

	private Context mContext;

	private int[][] mBoardMatrix;

	private GamePlayController gamePlayController;

	private GamePlayController.SquareDragListener squareDragListener;
	private GamePlayController.PegTouchListener pegTouchListener;

	public BoardView(Context context) {
		super(context);
		this.mContext = context;
		init();
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init();
	}


	private void init() {
		defaultSquare = getResources().getDrawable(R.drawable.red_disc);
	}

	public void initialize(GamePlayController gamePlayController, int[][] boardMatrix,
						    GamePlayController.SquareDragListener squareDragListener, GamePlayController.PegTouchListener pegTouchListener) {
		this.squareDragListener = squareDragListener;
		this.pegTouchListener = pegTouchListener;
		this.mBoardMatrix = boardMatrix;
		this.squares = gamePlayController.getSquares();
		this.gamePlayController = gamePlayController;
		buildCells();
	}

	/*
	* Loops through rows and columns creating TableRows and the views inside them,
	* Sets rows and columns of PegLayouts and PegViews
	*/

	private void buildCells() {
		removeAllViewsInLayout();
		int height = dpToPixels(45);
		int width = dpToPixels(45);
		int height_peg = dpToPixels(40);
		int width_peg = dpToPixels(40);
		for (int r = 0; r < Row; r++) {
			row[r] = new TableRow(mContext);
			for (int c = 0; c < Col; c++) {
				if (!(mBoardMatrix[r][c] == 0)) {
					squares[r][c] = new PegLayout(mContext, r, c);
					squares[r][c].setBackgroundDrawable(defaultSquare);
					squares[r][c].setPadding(2,2,2,2);
					squares[r][c].setOnDragListener(squareDragListener);
					if (mBoardMatrix[r][c] == 1) {
						pieces[r][c] = new PegView(mContext, r, c);
						pieces[r][c].setImageResource(R.drawable.yellow_disc);
						pieces[r][c].setPadding(8,8,0,0);
						pieces[r][c].setLayoutParams(new ViewGroup.LayoutParams(height_peg,width_peg));
						pieces[r][c].setOnTouchListener(pegTouchListener);
						squares[r][c].addView(pieces[r][c]);
					}
					row[r].addView(squares[r][c]);
					TableRow.LayoutParams params = (TableRow.LayoutParams)squares[r][c].getLayoutParams();
					params.column = c;
					params.setMargins(0,0,2,2);
					params.height = height;
					params.width = width;
					squares[r][c].setLayoutParams(params);
				}
			}
			addView(row[r], new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
