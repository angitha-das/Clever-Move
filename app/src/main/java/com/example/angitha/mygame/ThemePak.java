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

    private int[] game_background = {R.color.colorPrimary,R.color.colorPrimary,R.color.colorPrimary,R.color.alizirin,R.color.mid_purple,R.color.carrot,R.color.emerald,R.color.colorPrimary,R.color.light_yellow,R.color.tile_primary_green,R.color.yellow,R.color.mid_purple,R.color.yellow};
    private int[] empty_cell_color = {R.color.belize_hole,R.color.belize_hole,R.color.belize_hole,R.color.pomegranate,R.color.wisteria,R.color.pumpkin,R.color.nephtiris,R.color.belize_hole,R.color.dark_yellow,R.color.tile_secondaary_green,R.color.dark_yellow,R.color.bg_violet,R.color.dark_yellow};
    private int[] hover_cell_color = {R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite,R.color.colorWhite};
    private int[] primary_cell_color = {R.color.pink_dark,R.color.orange,R.color.tile_primary_yellow,R.color.wetsalphet,R.color.tile_primary_green,R.color.colorPrimary,R.color.amethyst,R.color.alizirin,R.color.colorPrimary,R.color.bg_violet,R.color.pink_dark,R.color.yellow,R.color.mid_purple};
    private int[] secondary_cell_color = {R.color.pink,R.color.lightOrange,R.color.tile_secondary_yellow,R.color.midnightblue,R.color.tile_secondaary_green,R.color.belize_hole,R.color.wisteria,R.color.pomegranate,R.color.belize_hole,R.color.mid_purple,R.color.pink,R.color.dark_yellow,R.color.bg_violet};


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
        return hover_cell_color[theme_id];
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
