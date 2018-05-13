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
        public static final int LEVEL19 = 18;
        public static final int LEVEL20 = 19;
        public static final int LEVEL21 = 20;
        public static final int LEVEL22 = 21;
        public static final int LEVEL23 = 22;
        public static final int LEVEL24 = 23;
        public static final int LEVEL25 = 24;
        public static final int LEVEL26 = 25;
        public static final int LEVEL27 = 26;
        public static final int LEVEL28 = 27;
        public static final int LEVEL29 = 28;
        public static final int LEVEL30 = 29;
        public static final int LEVEL31 = 30;
        public static final int LEVEL32 = 31;
        public static final int LEVEL33 = 32;
        public static final int LEVEL34 = 33;
        public static final int LEVEL35 = 34;
        public static final int LEVEL36 = 35;
        public static final int LEVEL37 = 36;
        public static final int LEVEL38 = 37;
        public static final int LEVEL39 = 38;
        public static final int LEVEL40 = 39;
        public static final int LEVEL41 = 40;
        public static final int LEVEL42 = 41;
        public static final int LEVEL43 = 42;
        public static final int LEVEL44 = 43;
        public static final int LEVEL45 = 44;
        public static final int LEVEL46 = 45;
        public static final int LEVEL47 = 46;
        public static final int LEVEL48 = 47;
        public static final int LEVEL49 = 48;
        public static final int LEVEL50 = 49;
        public static final int LEVEL51 = 50;
        public static final int LEVEL52 = 51;
        public static final int LEVEL53 = 52;
        public static final int LEVEL54 = 53;
        public static final int LEVEL55 = 54;
        public static final int LEVEL56 = 55;
        public static final int LEVEL57 = 56;
        public static final int LEVEL58 = 57;
        public static final int LEVEL59 = 58;
        public static final int LEVEL60 = 59;
        public static final int LEVEL61 = 60;
        public static final int LEVEL62 = 61;
        public static final int LEVEL63 = 62;
        public static final int LEVEL64 = 63;
        public static final int LEVEL65 = 64;
        public static final int LEVEL66 = 65;
        public static final int LEVEL67 = 66;
        public static final int LEVEL68 = 67;
        public static final int LEVEL69 = 68;
        public static final int LEVEL70 = 69;
        public static final int LEVEL71 = 70;
        public static final int LEVEL72 = 71;
        public static final int LEVEL73 = 72;
        public static final int LEVEL74 = 73;
        public static final int LEVEL75 = 74;
        public static final int LEVEL76 = 75;
        public static final int LEVEL77 = 76;
        public static final int LEVEL78 = 77;
        public static final int LEVEL79 = 78;
        public static final int LEVEL80 = 79;
        public static final int LEVEL81 = 80;
        public static final int LEVEL82 = 81;
        public static final int LEVEL83 = 82;
        public static final int LEVEL84 = 83;
        public static final int LEVEL85 = 84;
        public static final int LEVEL86 = 85;
        public static final int LEVEL87 = 86;
        public static final int LEVEL88 = 87;
        public static final int LEVEL89 = 88;
        public static final int LEVEL90 = 89;
        public static final int LEVEL91 = 90;
        public static final int LEVEL92 = 91;
        public static final int LEVEL93 = 92;
        public static final int LEVEL94 = 93;
        public static final int LEVEL95 = 94;
        public static final int LEVEL96 = 95;
        public static final int LEVEL97 = 96;
        public static final int LEVEL98 = 97;
        public static final int LEVEL99 = 98;
        public static final int LEVEL100 = 99;
        public static final int LEVEL101 = 100;
        public static final int LEVEL102 = 101;

        Level() {
            super(new int[]{LEVEL1,LEVEL2,LEVEL3,LEVEL4,LEVEL5,
                    LEVEL6,LEVEL7,LEVEL8,LEVEL9,LEVEL10,LEVEL11,LEVEL12,LEVEL13,LEVEL14,LEVEL15,
                    LEVEL16,LEVEL17,LEVEL18,LEVEL19,LEVEL20,LEVEL21,LEVEL22,LEVEL23,LEVEL24,LEVEL25,
                    LEVEL26,LEVEL27,LEVEL28,LEVEL29,LEVEL30,LEVEL31,LEVEL32,LEVEL33,LEVEL34,LEVEL35,
                    LEVEL36,LEVEL37,LEVEL38,LEVEL39,LEVEL40,LEVEL41,LEVEL42,LEVEL43,LEVEL44,LEVEL45,
                    LEVEL46,LEVEL47,LEVEL48,LEVEL49,LEVEL50,LEVEL51,LEVEL52,LEVEL53,LEVEL54,LEVEL55,
                    LEVEL56,LEVEL57,LEVEL58,LEVEL59,LEVEL60,LEVEL61,LEVEL62,LEVEL63,LEVEL64,LEVEL65,
                    LEVEL66,LEVEL67,LEVEL68,LEVEL69,LEVEL70,LEVEL71,LEVEL72,LEVEL73,LEVEL74,LEVEL75,
                    LEVEL76,LEVEL77,LEVEL78,LEVEL79,LEVEL80,LEVEL81,LEVEL82,LEVEL83,LEVEL84,LEVEL85,
                    LEVEL86,LEVEL87,LEVEL88,LEVEL89,LEVEL90,LEVEL91,LEVEL92,LEVEL93,LEVEL94,LEVEL95,
                    LEVEL96,LEVEL97,LEVEL98,LEVEL99,LEVEL100,LEVEL101,LEVEL102});
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
