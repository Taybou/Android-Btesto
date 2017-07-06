package com.example.nourelislamsaidi.utils;

import java.util.HashMap;

/**
 * Created by MOHAMEDTAYEBBenterki on 04/06/2017.
 */

public class NumberToWordFrench {
    public static final String ONE = "un";
    public static final String TWO = "deux";
    public static final String THREE = "trois";
    public static final String FOUR = "quatre";
    public static final String FIVE = "cinq";
    public static final String SIX = "six";
    public static final String SEVEN = "sept";
    public static final String EIGHT = "huit";
    public static final String NINE = "neuf";
    public static final String TEN = "dix";
    public static final String ELEVEN = "onze";
    public static final String TWELVE = "douze";
    public static final String THIRTEEN = "treize";
    public static final String FOURTEEN = "quatorze";
    public static final String FIFTEEN = "quinze";
    public static final String SIXTEEN = "seize";

    public static final String TWENTY = "vingt";
    public static final String THIRTY = "trente";
    public static final String FORTY = "quarante";
    public static final String FIFTY = "cinquante";
    public static final String SIXTY = "soixante";
    public static final String EIGHTY = "quatre-vingt";

    public static final String ONE_HUNDRED = "cent";
    public static final String THOUSANDS = "mille";
    public static final String MILLION = "million";
    public static final String BILLION = "milliard";
    public static final String trillion = "billion";
    // public static final String  ="";


    //Init Map
    public static final HashMap<Long, String> ARRAY_FRENCH = new HashMap<Long, String>() {
        {
            put(1L, ONE);
            put(2L, TWO);
            put(3L, THREE);
            put(4L, FOUR);
            put(5L, FIVE);
            put(6L, SIX);
            put(7L, SEVEN);
            put(8L, EIGHT);
            put(9L, NINE);
            put(10L, TEN);
            put(11L, ELEVEN);
            put(12L, TWELVE);
            put(13L, THIRTEEN);
            put(14L, FOURTEEN);
            put(15L, FIFTEEN);
            put(16L, SIXTEEN);
            put(20L, TWENTY);
            put(30L, THIRTY);
            put(40L, FORTY);
            put(50L, FIFTY);
            put(60L, SIXTY);
            put(80L, EIGHTY);
            put(100L, ONE_HUNDRED);
            put(1000L, THOUSANDS);
            put(1000000L, MILLION);
            put(1000000000L, BILLION);
            put(1000000000000L, trillion);
        }
    };


}
