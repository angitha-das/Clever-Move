package com.example.angitha.mygame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.utils.Utils;

import agency.tango.materialintroscreen.SlideFragment;

/**
 * Created by angitha on 17/10/17.
 */

public class HowToPlayCustomSlideFragment extends SlideFragment {
    int unicodeHappy = 0x1F60A;
    int unicodeSad = 0x1F61E;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.how_to_play_custom_fragment, container, false);
      TextView partOne = view.findViewById(R.id.part_one);
      partOne.setText(String.format("%s%s\n%s%s", getResources().getString(R.string.goalPartOne), Utils.getEmojiByUnicode(unicodeHappy), getResources().getString(R.string.goalPartTwo), Utils.getEmojiByUnicode(unicodeSad)));
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