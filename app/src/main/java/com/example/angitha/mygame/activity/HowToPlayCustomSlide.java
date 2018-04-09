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

public class HowToPlayCustomSlide extends SlideFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_custom_slide, container, false);
      return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.second_slide_background;
    }

    @Override
    public int buttonsColor() {
        return R.color.second_slide_buttons;
    }

    @Override
    public boolean canMoveFurther() {
        return true;
    }
}