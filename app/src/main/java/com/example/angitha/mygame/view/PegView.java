package com.example.angitha.mygame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.angitha.mygame.Pair;

import static com.example.angitha.mygame.levels.GameLevels.COLS;
import static com.example.angitha.mygame.levels.GameLevels.ROWS;


/**
 * PegView is a subclassed ImageView for storing the Pegs
 * Created by angitha on 1/7/17.
 *
 */
public class PegView extends android.support.v7.widget.AppCompatImageView {

    public boolean youHaveLost;
	private int row;
	private int col;

	public static int[][]  mGridCopy;
	/**
	 * Takes row and column as well as superclass constructor
	 *
	 * @param context
	 * @param r
	 * @param c
	 */
	public PegView(Context context, int r, int c) {
		super(context);
		row = r;
		col = c;
	}

	/**
	 * Takes row and column as well as superclass constructor
	 *
	 * @param context
	 * @param attrs
	 * @param r
	 * @param c
	 */
	public PegView(Context context, AttributeSet attrs, int r, int c) {
		super(context, attrs);
		row = r;
		col = c;
	}

	/**
	 * Takes row and column as well as superclass constructor
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle
	 * @param r
	 * @param c
	 */
	public PegView(Context context, AttributeSet attrs, int defStyle, int r, int c) {
		super(context, attrs, defStyle);
		row = r;
		col = c;
	}


	/**
	 * Setter for row of Peg in table
	 *
	 * @param r
	 */
	public void setRow(int r) {
		row = r;
	}

	/**
	 * Getter for row of Peg in table
	 *
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Setter for column of Peg in table
	 *
	 * @param c
	 */
	public void setColumn(int c) {
		col = c;
	}

	/**
	 * Getter for column of Peg in table
	 *
	 * @return col
	 */
	public int getColumn() {
		return col;
	}

	/**
	 * Move method, takes original square and new square to go to as well as array of PegLayouts in board
	 * Checks whether square to move to is empty and in right place as well as whether there is a Peg to jump over
	 * Sets new row and col values for PegView
	 *
	 * @param oldSquare
	 * @param newSquare
	 * @param squares
	 * @param mGrid
	 * @return bool
	 */
	public boolean move(PegLayout oldSquare, PegLayout newSquare, PegLayout[][] squares, int[][] mGrid) {
		int newRow = newSquare.getRow();
		int newCol = newSquare.getColumn();

		int oldRow = oldSquare.getRow();
		int oldCol = oldSquare.getColumn();


		if (newSquare.isEmpty()) {
			if (((Math.abs(newRow - oldRow) == 2) && (newCol == oldCol)) ||
					(Math.abs(newCol - oldCol) == 2) && (newRow == oldRow)) {
				if ((oldCol - newCol == -2) && (squares[newRow][newCol - 1])!=null && (!squares[newRow][newCol - 1].isEmpty()) ) {
					copyBoardStatusBeforeNextMove(mGrid);
					squares[newRow][newCol - 1].removeAllViews();
					mGrid[newRow][newCol - 1] = 2;
				} else if ((oldCol - newCol == 2) && (squares[newRow][newCol + 1])!=null && (!squares[newRow][newCol + 1].isEmpty())) {
					copyBoardStatusBeforeNextMove(mGrid);
					squares[newRow][newCol + 1].removeAllViews();
					mGrid[newRow][newCol + 1] = 2;
				} else if ((oldRow - newRow == -2)  && (squares[newRow - 1][newCol])!=null && (!squares[newRow - 1][newCol].isEmpty())) {
					copyBoardStatusBeforeNextMove(mGrid);
					squares[newRow - 1][newCol].removeAllViews();
					mGrid[newRow - 1][newCol] = 2;
				} else if ((oldRow - newRow == 2) && (squares[newRow + 1][newCol])!=null && (!squares[newRow + 1][newCol].isEmpty())) {
					copyBoardStatusBeforeNextMove(mGrid);
					squares[newRow + 1][newCol].removeAllViews();
					mGrid[newRow + 1][newCol] = 2;
				} else {
					return false;
				}
				oldSquare.removeView(this);
				mGrid[oldRow][oldCol] =2;
				newSquare.addView(this);
				mGrid[newRow][newCol] =1;
				this.row = newRow;
				this.col = newCol;
				this.setVisibility(View.VISIBLE);
				return true;
			}
		}
		return false;
	}

	private void copyBoardStatusBeforeNextMove(int[][] mGrid) {
		mGridCopy = new int[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				mGridCopy[r][c] = mGrid[r][c];
			}
		}
	}

	public boolean anyMoreMovesPossible(int[][] inputArr) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (inputArr[i][j] == 1) {
						youHaveLost = (((j-1)>0 && (j-1)<COLS && (j-2)>0 && (j-2)<COLS && (inputArr[i][j - 1] == 1 && inputArr[i][j - 2] == 2)) ||
								((j+1)>0 && (j+1)<COLS && (j+2)>0 && (j+2)<COLS && (inputArr[i][j + 1] == 1 && inputArr[i][j + 2] == 2))||
								((i+1)>0 && (i+1)<ROWS && (i+2)>0 && (i+2)<ROWS && (inputArr[i + 1][j] == 1 && inputArr[i + 2][j] == 2))||
								((i-1)>0 && (i-1)<ROWS && (i-2)>0 && (i-2)<ROWS && (inputArr[i - 1][j] == 1 && inputArr[i - 2][j] == 2)));
						if(youHaveLost){
							return true;//quit,you lost
						}
					}
				}
			}
		return false;
	}

	public Pair[] predict(PegView chosenSquare, int[][] mGrid) {
		Pair[] predictMoves = new Pair[4];
		{
			if(checkLeftMovePossibility(chosenSquare,mGrid)!=null){
			predictMoves[0]=checkLeftMovePossibility(chosenSquare,mGrid);
			}
			if(checkRightMovePossibility(chosenSquare,mGrid)!=null){
				predictMoves[1]=checkRightMovePossibility(chosenSquare,mGrid);
			}
			if(checkTopMovePossibility(chosenSquare,mGrid)!=null){
				predictMoves[2]=checkTopMovePossibility(chosenSquare,mGrid);
			}
			if(checkBottomMovePossibility(chosenSquare,mGrid)!=null){
				predictMoves[3]=checkBottomMovePossibility(chosenSquare,mGrid);
			}
		}
		return predictMoves;
	}

	private Pair checkBottomMovePossibility(PegView chosenSquare,int[][] mGrid) {
		int i = chosenSquare.getRow();
		int j = chosenSquare.getColumn();
				if (mGrid[i][j] == 1) {
					if(((j-1)>0 && (j-1)<COLS && (j-2)>0 && (j-2)<COLS && (mGrid[i][j - 1] == 1 && mGrid[i][j - 2] == 2))){
						return new Pair(i,j-2);
					}
				}
				return null;
			}


	private Pair checkTopMovePossibility(PegView chosenSquare,int[][] mGrid) {
		int i = chosenSquare.getRow();
		int j = chosenSquare.getColumn();
		if (mGrid[i][j] == 1) {
			if(((j+1)>0 && (j+1)<COLS && (j+2)>0 && (j+2)<COLS && (mGrid[i][j + 1] == 1 && mGrid[i][j + 2] == 2))){
				return new Pair(i,j+2);
			}
		}
		return null;

	}

	private Pair checkRightMovePossibility(PegView chosenSquare,int[][] mGrid) {
		int i = chosenSquare.getRow();
		int j = chosenSquare.getColumn();
		if (mGrid[i][j] == 1) {
			if(((i+1)>0 && (i+1)<ROWS && (i+2)>0 && (i+2)<ROWS && (mGrid[i + 1][j] == 1 && mGrid[i + 2][j] == 2))){
				return new Pair(i+2,j);
			}
		}
		return null;
	}

	private Pair checkLeftMovePossibility(PegView chosenSquare,int[][] mGrid) {
		int i = chosenSquare.getRow();
		int j = chosenSquare.getColumn();
		if (mGrid[i][j] == 1) {
			if(((i-1)>0 && (i-1)<ROWS && (i-2)>0 && (i-2)<ROWS && (mGrid[i - 1][j] == 1 && mGrid[i - 2][j] == 2))){
				return new Pair(i-2,j);
			}
		}
		return null;
	}
}