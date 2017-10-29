package com.example.angitha.mygame.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.angitha.mygame.R;
import com.example.angitha.mygame.controller.GamePlayController;
import com.example.angitha.mygame.levels.GameLevels;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class IntroActivity extends MaterialIntroActivity {

    String value = "notFromMenu";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableLastSlideAlphaExitTransition(false);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            value = extras.getString("isFromMenu");
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("first_time", false) || value.equalsIgnoreCase("yesFromMenu")) {

            GameLevels.getInstance().gameTour = true;

            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.first_slide_background)
                    .buttonsColor(R.color.first_slide_buttons)
                    .image(R.drawable.logo)
                    .title("Brainvita")
                    .description("A solo strategy game for age group 5" +
                            " and above and an ideal game for adults too")
                    .build());

            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.second_slide_background)
                    .buttonsColor(R.color.second_slide_buttons)
                    .title("How To Play?")
                    .description("Start by jumping the grids either horizontally or vertically " +
                            "over an adjacent grid to an empty hole.Continue until you are left with 1 grid over an adjacent grid to an empty hole.Continue until you are left with 1 grid over an adjacent grid to an empty hole.Continue until you are left with 1 grid over an adjacent grid to an empty hole.Continue until you are left with 1 grid")
                    .build());

            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.fourth_slide_background)
                    .buttonsColor(R.color.fourth_slide_buttons)
                    .title("That's it")
                    .description("Would you join us?")
                    .build());

            addSlide(new CustomSlide());

        }else{
            GameLevels.getInstance().gameTour=false;
            Intent i = new Intent(IntroActivity.this, GameMenuActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onFinish() {
        GameLevels.getInstance().gameTour=false;
        Intent i = new Intent(IntroActivity.this, GameMenuActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameLevels.getInstance().gameTour=false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("first_time", true);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if(value.equalsIgnoreCase("yesFromMenu")){
            Intent i = new Intent(IntroActivity.this, GameMenuActivity.class);
            startActivity(i);
        }
        finish();
    }
}
