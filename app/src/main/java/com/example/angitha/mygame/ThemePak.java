package com.example.angitha.mygame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * Created by angitha on 20/10/17.
 */

public class ThemePak {


    private Context context;
    private static ThemePak themePakObj;
    private static GradientDrawable emptyDrawable;
    private static LayerDrawable cellDrawable;


    public static ThemePak getInstance(){
        if(themePakObj == null){
            themePakObj = new ThemePak();
        }
        return themePakObj;
    }

    private int[] game_background = {R.color.colorPrimary,R.color.mid_purple,R.color.pink_dark};
    private int[] empty_cell_color = {R.color.belize_hole,R.color.light_purple,R.color.pink};
    private int[] hover_cell_color = {R.color.colorWhite,R.color.colorAccent,R.color.colorPrimary};
    private int[] primary_cell_color = {R.color.pink_dark,R.color.colorPrimary,R.color.belize_hole_dark};
    private int[] secondary_cell_color = {R.color.pink,R.color.belize_hole,R.color.belize_hole};


    public int[] getThemeSet(int theme_id){
        int[] theme_set = {game_background[theme_id],empty_cell_color[theme_id],hover_cell_color[theme_id],
                primary_cell_color[theme_id],secondary_cell_color[theme_id]};
        return theme_set;
    }

    public int getBackground(int theme_id){
        return game_background[theme_id];
    }

    public int getEmptyCellColor(int theme_id){
        return empty_cell_color[theme_id];
    }

    public int getHoverCellColor(int theme_id){
        return empty_cell_color[theme_id];
    }

    public int getPrimaryCellColor(int theme_id){
        return primary_cell_color[theme_id];
    }

    public int getSecondaryCellColor(int theme_id){
        return secondary_cell_color[theme_id];
    }

    public static Drawable createSquareDrawable(int strokeColor, float strokeWidthPx, float cornerRadiusPx) {
        emptyDrawable = new GradientDrawable();
        emptyDrawable.setColor(Color.TRANSPARENT);
        emptyDrawable.setCornerRadius(cornerRadiusPx);
        emptyDrawable.setStroke((int) strokeWidthPx, strokeColor);
        return emptyDrawable;
    }

    public static Drawable getSquareDrawable(){
        return emptyDrawable;
    }
    public static Drawable getCellDrawable(){
        return cellDrawable;
    }


    public  LayerDrawable createDrawable(Context context,int radius, int topColor, int bottomColor) {
        this.context = context;
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
        cellDrawable = new LayerDrawable(drawArray);
        cellDrawable.setLayerInset(1, 0, 0, 0, dpToPixels(3));  /*index, left, top, right, bottom*/

        return cellDrawable;
    }

    /**
     * Can't set height and width as device independent pixels, so have to convert
     *
     * @param dps
     * @return pixels
     */
    public int dpToPixels(int dps) {
        float scale =  context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }


}
