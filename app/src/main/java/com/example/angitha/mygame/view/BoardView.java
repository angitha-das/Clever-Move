package com.example.angitha.mygame.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.rules.GameRules;

public class BoardView extends LinearLayout {

	private static final String TAG = GamePlayController.class.getName();

	private int Row;
	private int Col;

	private TableRow[] row  = new TableRow[0];
	private BoardView[][] squares = new BoardView[0][];
	private PegView[][] pieces = new PegView[0][];
	private Drawable defaultSquare;


	private GameRules mGameRules;
	private GamePlayController mListener;
	private int[][] mBoardMatrix;

	private View mBoardView;

	private Context mContext;

	/**
	 * Takes Row and column as well as superclass constructor
	 *
	 * @param context
	 * @param r
	 * @param c
	 */
	public BoardView(Context context, int r, int c) {
		super(context);
		Row = r;
		Col = c;
		init(context);

	}

	/**
	 * Takes Row and column as well as superclass constructor
	 *
	 * @param context
	 * @param attrs
	 * @param r
	 * @param c
	 */
	public BoardView(Context context, AttributeSet attrs, int r, int c) {
		super(context, attrs);
		Row = r;
		Col = c;
		init(context);

	}

	/**
	 * Takes Row and column as well as superclass constructor
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle
	 * @param r
	 * @param c
	 */
	public BoardView(Context context, AttributeSet attrs, int defStyle, int r, int c) {
		super(context, attrs, defStyle);
		Row = r;
		Col = c;
		init(context);

	}

	/**
	 * Setter for Row in table
	 *
	 * @param r
	 */
	public void setRow(int r) {
		Row = r;
	}

	/**
	 * Getter for Row in table
	 *
	 * @return Row
	 */
	public int getRow() {
		return Row;
	}

	/**
	 * Setter for column in table
	 *
	 * @param c
	 */
	public void setColumn(int c) {
		Col = c;
	}

	/**
	 * Getter for column in table
	 *
	 * @return column
	 */
	public int getColumn() {
		return Col;
	}

	/**
	 * Checks whether it has any children
	 *
	 * @return bool
	 */
	public boolean isEmpty() {
		if (this.getChildCount() == 0) {
			return true;
		}
		return false;

	}

	private void init(Context context) {
		this.mContext = context;
		inflate(context, R.layout.activity_game_play, this);
		mBoardView = findViewById(R.id.game_table_layout);
	}

	public void initialize(GamePlayController gamePlayController, @NonNull GameRules gameRules, int[][] boardMatrix) {
		this.mGameRules = gameRules;
		this.mListener = gamePlayController;
		this.mBoardMatrix = boardMatrix;
		buildCells();
	}

	/*
	* Loops through rows and columns creating TableRows and the views inside them,
	* Sets rows and columns of PegLayouts and PegViews
	*/

	private void buildCells() {
		defaultSquare = getResources().getDrawable(R.drawable.square);
		int height = dpToPixels(45);
		int width = dpToPixels(45);
		for (int r = 0; r < Row; r++) {
			row[r] = new TableRow(mContext);
			for (int c = 0; c < Col; c++) {
				if( r == 0 && c == 0) {
					squares[r][c] = new BoardView(this, r, c);
					squares[r][c].setBackgroundDrawable(defaultSquare);
                    squares[r][c].setOnDragListener(new GamePlayController.SquareDragListener());
					if (r == 1 && c == 1) {
						pieces[r][c] = new PegView(this, r, c);
						pieces[r][c].setImageResource(R.drawable.peg);
						pieces[r][c].setLayoutParams(new ViewGroup.LayoutParams(height, width));
                        pieces[r][c].setOnTouchListener(new GamePlayController.PegTouchListener());
						squares[r][c].addView(pieces[r][c]);
					}
					row[r].addView(squares[r][c]);
					TableRow.LayoutParams params = (TableRow.LayoutParams)squares[r][c].getLayoutParams();
					params.column = c;
					params.height = height;
					params.width = width;
					squares[r][c].setLayoutParams(params);
				}

			}
			gameTableLayout.addView(row[r], new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
	 * Reset board for new game same level
	 */
	public void resetBoard() {
		buildCells();
	}
}
