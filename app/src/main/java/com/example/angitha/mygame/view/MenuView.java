package com.example.angitha.mygame.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GameMenuController;

/**
 * Created by angitha on 1/7/17.
 */

public class MenuView extends ConstraintLayout {

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
        findViewById(R.id.levels).setOnClickListener(gameMenuController);
        findViewById(R.id.muteSound).setOnClickListener(gameMenuController);
        findViewById(R.id.gameTour).setOnClickListener(gameMenuController);
        findViewById(R.id.like).setOnClickListener(gameMenuController);
        findViewById(R.id.share).setOnClickListener(gameMenuController);
    }
}
