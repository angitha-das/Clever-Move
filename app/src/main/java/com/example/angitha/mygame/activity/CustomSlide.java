package com.example.angitha.mygame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.levels.GameLevels;
import com.example.angitha.mygame.view.BoardView;

import agency.tango.materialintroscreen.SlideFragment;

/**
 * Created by angitha on 17/10/17.
 */

public class CustomSlide extends SlideFragment {
    private GamePlayController mGameController;
    GameLevels gameLevels = GameLevels.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_custom_slide, container, false);
        BoardView boardView = view.findViewById(R.id.game_tour_table_layout);
        ConstraintLayout gameBackground = view.findViewById(R.id.game_background);
        ImageView step1 = view.findViewById(R.id.step1_img);
        ImageView step2 = view.findViewById(R.id.step2_img);
        ImageView step3 = view.findViewById(R.id.step3_img);
        TextView textView = view.findViewById(R.id.tour_completed);


        gameLevels.gameTour = true;
        mGameController = new GamePlayController(getContext(), boardView,step1,step2,step3,textView,gameBackground);
        return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.custom_slide_background;
    }

    @Override
    public int buttonsColor() {
        return R.color.custom_slide_buttons;
    }

    @Override
    public boolean canMoveFurther() {
        if(mGameController.getScore() == 1){
            gameLevels.gameTour=false;
            return true;
        }
        return false;
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return getString(R.string.error_message);
    }
}