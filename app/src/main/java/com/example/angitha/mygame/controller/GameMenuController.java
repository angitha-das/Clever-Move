package com.example.angitha.mygame.controller;

import android.view.View;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.rules.GameRules;
import com.example.angitha.mygame.view.MenuView;

/**
 * Created by angitha on 1/7/17.
 */

public class GameMenuController implements View.OnClickListener{
    private final MenuView mMenuView;
    private final MenuControllerListener mListener;
    private final GameRules mGameRules = new GameRules();

    public GameMenuController(MenuControllerListener mListener, MenuView mMenuView) {
        this.mMenuView = mMenuView;
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.play:mListener.onPlay(mGameRules);
                break;
            case R.id.levels:mListener.showAllLevels();
                break;
            case R.id.achievements:
                break;
            case R.id.like:
                break;
            case R.id.share:
                break;
            case R.id.quit:mListener.quitGame();
                break;
            default:break;
        }
    }

    public interface MenuControllerListener {
        /**
         * The method is called by menu controller to inform the
         * menu Activity about to start game
         */
        void onPlay(GameRules gameRules);
        void quitGame();
        void showAllLevels();
    }
}
