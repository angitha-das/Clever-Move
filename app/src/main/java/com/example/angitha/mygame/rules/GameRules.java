package com.example.angitha.mygame.rules;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by angitha on 1/7/17.
 */

public class GameRules {

    public static final int DISC = 3;

    /**
     * Levels of the Game
     */
    public class Level extends Rule {

        public static final int LEVEL1 = 0;
        public static final int LEVEL2 = 1;
        public static final int LEVEL3 = 2;

        Level() {
            super(new int[]{LEVEL1, LEVEL2,LEVEL3});
        }
    }

    /**
     * All possible rules
     */
    public static final int LEVEL = 0;

    /**
     * rules
     */
    @NonNull
    private final Rule[] rules;

    /**
     * Creates Game rules
     */
    public GameRules() {
        rules = new Rule[]{
                new Level()
        };
    }

    /**
     * Returns current rule state
     *
     * @param rule rule to get selected value
     * @return return selected value
     */
    public int getRule(int rule) {
        return rules[rule].getSelectedId();
    }

    /**
     * Sets new rule state
     *
     * @param rule game rule to set value
     * @param value rule value
     */
    public void setRule(int rule, int value) {
        rules[rule].setId(value);
    }

    @NonNull
    public Bundle exportTo(@NonNull Bundle bundle) {
        int[] bundleRules = new int[rules.length];
        for(int i = 0; i < rules.length; ++i) {
            bundleRules[i] = rules[i].getSelectedId();
        }

        bundle.putIntArray("rules", bundleRules);
        return bundle;
    }

    public void importFrom(@NonNull Bundle bundle) {
        int[] bundleRules = bundle.getIntArray("rules");
        for(int i = 0; i < (bundleRules != null ? bundleRules.length : 0); ++i) {
            rules[i].setId(bundleRules[i]);
        }
    }
}
