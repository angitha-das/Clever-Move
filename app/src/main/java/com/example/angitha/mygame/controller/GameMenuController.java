package com.example.angitha.mygame.controller;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.SeekBar;

import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.MenuView;

/**
 * Created by angitha on 1/7/17.
 */

public class GameMenuController implements View.OnClickListener ,SeekBar.OnSeekBarChangeListener {
    private final MenuView mMenuView;
    private final MenuControllerListener mListener;
    private final GameRules mGameRules = new GameRules();

    public GameMenuController(MenuControllerListener mListener, MenuView mMenuView) {
        this.mMenuView = mMenuView;
        this.mListener = mListener;
        this.mMenuView.setupMenu(getDefaultGameRules());
    }

    @Override
    public void onClick(View v) {
        mListener.onPlay(mGameRules);
    }

    @NonNull
    private GameRules getDefaultGameRules() {
        mGameRules.setRule(GameRules.LEVEL, GameRules.Level.LEVEL1);
        return mGameRules;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mGameRules.setRule(GameRules.LEVEL, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public interface MenuControllerListener {
        /**
         * The method is called by menu controller to inform the
         * menu Activity about to start game
         */
        void onPlay(GameRules gameRules);
    }
}
