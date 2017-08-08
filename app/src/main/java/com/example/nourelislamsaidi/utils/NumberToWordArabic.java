package com.example.nourelislamsaidi.utils;

import java.util.HashMap;

public class NumberToWordArabic {
    public static final String ZERO = "صفر";
    public static final String ONE = "واحد";
    public static final String TWO = "اثنان";
    public static final String THREE = "ثلاثة";
    public static final String FOUR = "أربعة";
    public static final String FIVE = "خمسة";
    public static final String SIX = "ستة ";
    public static final String SEVEN = "سبعة";
    public static final String EIGHT = "ثمانية";
    public static final String NINE = "تسعة";
    public static final String TEN = "عشرة";

    public static final String THREE_WITHOUT_TA = "ثلاث";
    public static final String FOUR_WITHOUT_TA = "أربع";
    public static final String FIVE_WITHOUT_TA = "خمس";
    public static final String SIX_WITHOUT_TA = "ست ";
    public static final String SEVEN_WITHOUT_TA = "سبع";
    public static final String EIGHT_WITHOUT_TA = "ثمان";
    public static final String NINE_WITHOUT_TA = "تسع";

    public static final String ELEVEN = "أحد عشر";
    public static final String TWELVE = "اثنا عشر";
    public static final String THIRTEEN = "ثلاثة عشر";
    public static final String FOURTEEN = "أربعة عشر";
    public static final String FIFTEEN = "خمسة عشر";
    public static final String SIXTEEN = "ستة عشر";
    public static final String SEVENTEEN = "سبعة عشر";
    public static final String EIGHTEEN = "ثمانية عشر";
    public static final String NINETEEN = "تسعة عشر";

    public static final String TWENTY = "عشرون";
    public static final String THIRTY = "ثلاثون";
    public static final String FORTY = "أربعون";
    public static final String FIFTY = "خمسون";
    public static final String SIXTY = "ستون";
    public static final String SEVENTY = "سبعون";
    public static final String EIGHTY = "ثمانون";
    public static final String NINETY = "تسعون";

    public static final String ONE_HUNDRED = "مئة";
    public static final String TWO_HUNDRED = "مئتان";
    public static final String THREE_HUNDRED = "ثلاثمائة";
    public static final String FOUR_HUNDRED = "أربعمائة";
    public static final String FIVE_HUNDRED = "خمسمائة";
    public static final String SIX_HUNDRED = "ستمائة";
    public static final String SEVEN_HUNDRED = "سبعمائة";
    public static final String EIGHT_HUNDRED = "ثمانمائة";
    public static final String NINE_HUNDRED = "تسعمائة";

    public static final String THOUSANDS = "ألف";
    public static final String TWO_THOUSANDS = "ألفان";
    public static final String TEN_THOUSANDS = "آلاف";
    public static final String ALL_THOUSANDS = "ألفاً";

    public static final String MILLION = "مليون";
    public static final String TWO_MILLION = "مليونان";
    public static final String TEN_MILLION = "ملايين";
    public static final String ALL_MILLION = "مليونا";

    public static final String BILLION = "مليار";
    public static final String TWO_BILLION = "ملياران";
    public static final String TEN_BILLION = "مليارات";
    public static final String ALL_BILLION = "مليارا";


    public static final String TRILLION = "تريليون";
    public static final String TWO_TRILLION = "تريليونان";
    public static final String TEN_TRILLION = "تريليونات";
    public static final String ALL_TRILLION = "تريليونا";

    // public static final String  ="";

    //Init Map
    public static final HashMap<Long, String> ARRAY_ARABIC = new HashMap<Long, String>() {
        {
            put(0L, ZERO);
            put(1L, ONE);
            put(2L, TWO);
            put(3L, THREE);
            put(4L, FOUR);
            put(5L, FIVE);
            put(6L, SIX);
            put(7L, SEVEN);
            put(8L, EIGHT);
            put(9L, NINE);

            put(-3L, THREE_WITHOUT_TA);
            put(-4L, FOUR_WITHOUT_TA);
            put(-5L, FIVE_WITHOUT_TA);
            put(-6L, SIX_WITHOUT_TA);
            put(-7L, SEVEN_WITHOUT_TA);
            put(-8L, EIGHT_WITHOUT_TA);
            put(-9L, NINE_WITHOUT_TA);

            put(10L, TEN);
            put(11L, ELEVEN);
            put(12L, TWELVE);
            put(13L, THIRTEEN);
            put(14L, FOURTEEN);
            put(15L, FIFTEEN);
            put(16L, SIXTEEN);
            put(17L, SEVENTEEN);
            put(18L, EIGHTEEN);
            put(19L, NINETEEN);
            put(20L, TWENTY);
            put(30L, THIRTY);
            put(40L, FORTY);
            put(50L, FIFTY);
            put(60L, SIXTY);
            put(70L, SEVENTY);
            put(80L, EIGHTY);
            put(90L, NINETY);

            put(100L, ONE_HUNDRED);
            put(200L, TWO_HUNDRED);
            put(300L, THREE_HUNDRED);
            put(400L, FOUR_HUNDRED);
            put(500L, FIVE_HUNDRED);
            put(600L, SIX_HUNDRED);
            put(700L, SEVEN_HUNDRED);
            put(800L, EIGHT_HUNDRED);
            put(900L, NINE_HUNDRED);

            put(1000L, THOUSANDS);
            put(2000L, TWO_THOUSANDS);
            put(10000L, TEN_THOUSANDS);
            put(100000L, ALL_THOUSANDS);

            put(1000000L, MILLION);
            put(2000000L, TWO_MILLION);
            put(10000000L, TEN_MILLION);
            put(100000000L, ALL_MILLION);

            put(1000000000L, BILLION);
            put(2000000000L, TWO_BILLION);
            put(10000000000L, TEN_BILLION);
            put(100000000000L, ALL_BILLION);

            put(1000000000000L, TRILLION);
            put(2000000000000L, TWO_TRILLION);
            put(10000000000000L, TEN_TRILLION);
            put(100000000000000L, ALL_TRILLION);
        }
    };


}
