package com.example.angitha.mygame;
/**
 * Created by angitha on 20/10/17.
 */

public class ThemePak {

    public ThemePak() {
        this.game_background = game_background;
        this.empty_cell_color = empty_cell_color;
        this.primary_cell_color = primary_cell_color;
        this.secondary_cell_color = secondary_cell_color;
    }

    int[] game_background = {R.color.colorPrimary,R.color.mid_purple,R.color.pink_dark};
    int[] empty_cell_color = {R.color.belize_hole,R.color.light_purple,R.color.pink};
    int[] primary_cell_color = {R.color.pink_dark,R.color.colorPrimary,R.color.belize_hole_dark};
    int[] secondary_cell_color = {R.color.pink,R.color.belize_hole,R.color.belize_hole};

    public int[] getThemeSet(int theme_id){
        int[] theme_set = {game_background[theme_id],empty_cell_color[theme_id],
                primary_cell_color[theme_id],secondary_cell_color[theme_id]};
        return theme_set;
    }

    public int getBackground(int theme_id){
        return game_background[theme_id];
    }
    public int getEmptyCellColor(int theme_id){
        return empty_cell_color[theme_id];
    }
    public int getPrimaryCellColor(int theme_id){
        return primary_cell_color[theme_id];
    }
    public int getSecondaryCellColor(int theme_id){
        return secondary_cell_color[theme_id];
    }
}
