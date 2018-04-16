package com.example.angitha.mygame.rules;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by angitha on 1/7/17.
 */

public class GameRules {

    /**
     * Levels of the Game
     */
    public class Level extends Rule {

        public static final int LEVEL1 = 0;
        public static final int LEVEL2 = 1;
        public static final int LEVEL3 = 2;
        public static final int LEVEL4 = 3;
        public static final int LEVEL5 = 4;
        public static final int LEVEL6 = 5;
        public static final int LEVEL7 = 6;
        public static final int LEVEL8 = 7;
        public static final int LEVEL9 = 8;
        public static final int LEVEL10 = 9;
        public static final int LEVEL11 = 10;
        public static final int LEVEL12 = 11;
        public static final int LEVEL13 = 12;
        public static final int LEVEL14 = 13;
        public static final int LEVEL15 = 14;
        public static final int LEVEL16 = 15;
        public static final int LEVEL17 = 16;
        public static final int LEVEL18 = 17;
        Level() {
            super(new int[]{LEVEL1,LEVEL2,LEVEL3,LEVEL4,LEVEL5,
                    LEVEL6,LEVEL7,LEVEL8,LEVEL9,LEVEL10,LEVEL11,LEVEL12,LEVEL13,LEVEL14,LEVEL15,
                    LEVEL16,LEVEL17,LEVEL18});
        }
    }

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
