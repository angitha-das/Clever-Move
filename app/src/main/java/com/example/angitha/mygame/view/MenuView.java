package com.example.angitha.mygame.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GameMenuController;
import com.example.angitha.mygame.rules.GameRules;

/**
 * Created by angitha on 1/7/17.
 */

public class MenuView extends RelativeLayout {
    public MenuView(Context context) {
        super(context);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * Listener for menu events
     * @param gameMenuController game menu controller instance
     */
    public void setListeners(GameMenuController gameMenuController) {

        findViewById(R.id.play).setOnClickListener(gameMenuController);
        findViewById(R.id.levels).setVisibility(VISIBLE);
    }

    /**
     * change difficulty ui
     * @param difficulty game rule - difficulty value
     */
    public void setDifficulty(int difficulty){
        ((SeekBar) findViewById(R.id.difficulty)).setProgress(difficulty);
    }

    /**
     * set menu with default rules
     * @param defaultGameRules default game rule
     */
    public void setupMenu(@NonNull GameRules defaultGameRules) {
        setDifficulty(defaultGameRules.getRule(GameRules.LEVEL));
    }
}
