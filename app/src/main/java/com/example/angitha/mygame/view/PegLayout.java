package com.example.angitha.mygame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * PegLayout is subclassed LinearLayout for holding PegViews
 * Are also assigned DragListeners for drag and drop
 * Created by angitha on 1/7/17.
 */
public class PegLayout extends LinearLayout {

	private int row;
	private int col;
	
	/**
	 * Takes row and column as well as superclass constructor
	 * 
	 * @param context
	 * @param r
	 * @param c
	 */
	public PegLayout(Context context, int r, int c) {
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
	public PegLayout(Context context, AttributeSet attrs, int r, int c) {
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
	public PegLayout(Context context, AttributeSet attrs, int defStyle, int r, int c) {
		super(context, attrs, defStyle);
		row = r;
		col = c;
	}
	
	/**
	 * Setter for row in table
	 * 
	 * @param r
	 */
	public void setRow(int r) {
		row = r;
	}
	
	/**
	 * Getter for row in table
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Setter for column in table
	 * 
	 * @param c
	 */
	public void setColumn(int c) {
		col = c;
	}
	
	/**
	 * Getter for column in table
	 * 
	 * @return column
	 */
	public int getColumn() {
		return col;
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
}
