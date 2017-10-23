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

import java.util.Random;

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
//	private Drawable blankSquare;

	private Context mContext;

	private int[][] mBoardMatrix;

	private GamePlayController gamePlayController;

	private GamePlayController.SquareDragListener squareDragListener;
	private GamePlayController.PegTouchListener pegTouchListener;
	private LayerDrawable cellDrawable;

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
//		defaultSquare = getResources().getDrawable(R.drawable.square);
//		blankSquare =  getResources().getDrawable(R.color.peter_river);
		defaultSquare = createSquareDrawable(ContextCompat.getColor(mContext, R.color.colorWhite),dpToPixels(2),dpToPixels(5));
		cellDrawable = createDrawable(dpToPixels(5), ContextCompat.getColor(mContext, R.color.pink_dark),ContextCompat.getColor(mContext, R.color.pink));

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
		int height = (((Activity) getContext()).getWindowManager()
				.getDefaultDisplay().getWidth())/10;
		int width = (((Activity) getContext()).getWindowManager()
				.getDefaultDisplay().getWidth())/10;
		for (int r = 0; r < Row; r++) {
			row[r] = new TableRow(mContext);
			for (int c = 0; c < Col; c++) {
				if (!(mBoardMatrix[r][c] == 0)) {
					squares[r][c] = new PegLayout(mContext, r, c);
					squares[r][c].setBackgroundDrawable(defaultSquare);
					squares[r][c].setPadding(0,0,0,0);
					squares[r][c].setOnDragListener(squareDragListener);
					if (mBoardMatrix[r][c] == 1) {
						pieces[r][c] = new PegView(mContext, r, c);
//						pieces[r][c].setImageResource(R.drawable.cell);
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

	public static Drawable createSquareDrawable( int strokeColor, float strokeWidthPx, float cornerRadiusPx) {
		GradientDrawable fill = new GradientDrawable();
		fill.setColor(Color.TRANSPARENT);
		fill.setCornerRadius(cornerRadiusPx);
		fill.setStroke((int) strokeWidthPx, strokeColor);
		return fill;
	}

	private LayerDrawable createDrawable(int radius, int topColor, int bottomColor) {
		float[] outerRadius = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
		//Top
		RoundRectShape topRoundRect = new RoundRectShape(outerRadius, null, null);
		ShapeDrawable topShapeDrawable = new ShapeDrawable(topRoundRect);
		topShapeDrawable.getPaint().setColor(topColor);
		//Bottom
		RoundRectShape roundRectShape = new RoundRectShape(outerRadius, null, null);
		ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
		bottomShapeDrawable.getPaint().setColor(bottomColor);
		//Create array
		Drawable[] drawArray = { bottomShapeDrawable, topShapeDrawable };
		LayerDrawable layerDrawable = new LayerDrawable(drawArray);
		layerDrawable.setLayerInset(1, 0, 0, 0, dpToPixels(3));  /*index, left, top, right, bottom*/

		return layerDrawable;
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
