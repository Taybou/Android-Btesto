package com.example.nourelislamsaidi.utils;

import android.content.Context;

import com.example.nourelislamsaidi.numbertowords.R;

public class Utils {
    private static final String SPACE_SEPARATOR = " ";
    private static final String FRENCH_AND_SEPARATOR = " et ";
    private static final String DASH_SEPARATOR = "-";
    private static final String POINT_SEPARATOR = "\\.";
    private static final String S_CHAR = "s";
    private static final String FRENCH_CENTS = "CENTIMES";

    private static final String ARABIC_CENTS = "سنت";
    private static final String ARABIC_AND_SEPARATOR = " و";

    private static final int TWO_ELEMENTS = 2;
    private static final int LENGTH_MAX_VALUE = 15;

    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
    }

    // ---------- ARABIC -------------
    public static String getArabicWord(String number, String currency) {
        String word = "";
        if (!number.equals(".")) {
            String beforePoint = number.split(POINT_SEPARATOR)[0];

            if (beforePoint.length() > LENGTH_MAX_VALUE)
                return mContext.getResources().getString(R.string.error_text);

            if (!beforePoint.isEmpty()) {
                word = getArabicWordFromNumber(beforePoint) + SPACE_SEPARATOR + currency;
            }

            if (number.contains(".")) {
                String afterPoint = (number.split(POINT_SEPARATOR).length < TWO_ELEMENTS) ? "" : number.split(POINT_SEPARATOR)[1];
                if (!afterPoint.isEmpty()) {
                    if (afterPoint.length() > LENGTH_MAX_VALUE)
                        return mContext.getResources().getString(R.string.error_text);

                    word += (!beforePoint.isEmpty() ? ARABIC_AND_SEPARATOR : "")
                            + getArabicWordFromNumber(afterPoint) + SPACE_SEPARATOR + ARABIC_CENTS;
                }
            }
        }
        return word;
    }

    private static String getArabicWordFromNumber(String number) {
        String word = "";
        long thousands;
        long mNumber = Long.valueOf(number);

        if (mNumber == 0) {
            word = NumberToWordArabic.ARRAY_ARABIC.get(mNumber);
        } else {
            // Number greater than TRILLION
            if ((mNumber > 999999999999L)) {
                thousands = mNumber / 1000000000000L;
                mNumber = mNumber - (1000000000000L * thousands);

                word += getArabicWordOfDigit(thousands, 1000000000000L, true);
            }

            // Number less than TRILLION
            if ((mNumber > 999999999) && (mNumber < 1000000000000L)) {
                word += (word == "") ? "" : SPACE_SEPARATOR;
                thousands = mNumber / 1000000000;
                mNumber = mNumber - (1000000000 * thousands);

                word += getArabicWordOfDigit(thousands, 1000000000L, true);
            }

            // Number less than BILLION
            if (mNumber > 999999 && mNumber < 1000000000) {
                word += (word == "") ? "" : SPACE_SEPARATOR;
                thousands = mNumber / 1000000;
                mNumber = mNumber - (1000000 * thousands);

                word += getArabicWordOfDigit(thousands, 1000000L, true);
            }

            // Number between 1000 and 999999
            if (mNumber > 999 && mNumber < 1000000) {
                word += (word == "") ? "" : SPACE_SEPARATOR;
                thousands = (mNumber / 1000);
                mNumber = mNumber - (thousands * 1000);

                word += getArabicWordOfDigit(thousands, 1000L, false);
            }

            word += ((word == "" || getArabicWordOfLowerDigit(mNumber) == "") ? "" : ARABIC_AND_SEPARATOR) + getArabicWordOfLowerDigit(mNumber);
        }
        return word;
    }

    private static String getArabicWordOfLowerDigit(long number) {
        String word = "";
        long hundreds;
        long tens;
        long units;

        // Number between 100 and 999
        if (number > 99 && number < 1000) {
            hundreds = ((number / 100) * 100);
            word += NumberToWordArabic.ARRAY_ARABIC.get(hundreds);

            number = number - (hundreds);
        }

        // Number between 1 and 99
        if (number > 0 && number < 100) {
            word += (word == "") ? "" : ARABIC_AND_SEPARATOR;
            tens = number / 10;
            units = number % 10;

            if (number < 20) {
                word += NumberToWordArabic.ARRAY_ARABIC.get(number);
            } else {
                word += ((units == 0) ? "" : NumberToWordArabic.ARRAY_ARABIC.get(units) + ARABIC_AND_SEPARATOR)
                        + NumberToWordArabic.ARRAY_ARABIC.get(tens * 10);
            }
        }

        return word;
    }

    private static String getArabicWordOfDigit(long digit, long digitUsed, boolean isSingularWord) {
        String word = (digit == 1 || digit == 2)
                ? NumberToWordArabic.ARRAY_ARABIC.get(digit * digitUsed)
                :
                getArabicWordOfLowerDigit((digit < 10 && isSingularWord) ? -digit : digit)
                        + SPACE_SEPARATOR +
                        (
                                (digit < 11)
                                        ? NumberToWordArabic.ARRAY_ARABIC.get(digitUsed * 10)
                                        : NumberToWordArabic.ARRAY_ARABIC.get(digitUsed * 100)
                        );
        return word;
    }

    // ---------- FRENCH -------------
    public static String getFrenchWord(String number, String currency) {
        String word = "";
        if (!number.equals(".")) {
            String beforePoint = number.split(POINT_SEPARATOR)[0];

            if (beforePoint.length() > LENGTH_MAX_VALUE)
                return mContext.getResources().getString(R.string.error_text);

            if (!beforePoint.isEmpty()) {
                word = getFrenchWordFromNumber(beforePoint) + SPACE_SEPARATOR + currency;
            }

            if (number.contains(".")) {
                String afterPoint = (number.split(POINT_SEPARATOR).length < TWO_ELEMENTS) ? "" : number.split(POINT_SEPARATOR)[1];
                if (!afterPoint.isEmpty()) {
                    if (afterPoint.length() > LENGTH_MAX_VALUE)
                        return mContext.getResources().getString(R.string.error_text);

                    word += (!beforePoint.isEmpty() ? FRENCH_AND_SEPARATOR : "")
                            + getFrenchWordFromNumber(afterPoint) + SPACE_SEPARATOR + FRENCH_CENTS;
                }
            }
        }

        return word;
    }

    private static String getFrenchWordFromNumber(String number) {
        String word = "";
        long thousands;
        long mNumber = Long.valueOf(number);

        if (mNumber == 0) {
            word += NumberToWordFrench.ARRAY_FRENCH.get(mNumber);
        } else {

            // Number greater than TRILLION
            if ((mNumber > 999999999999L)) {
                thousands = mNumber / 1000000000000L;
                mNumber = mNumber - (1000000000000L * thousands);

                word += getWordOfDigit(thousands, 1000000000000L, false);
            }

            // Number less than TRILLION
            if ((mNumber > 999999999) && (mNumber < 1000000000000L)) {
                word += (word == "") ? "" : SPACE_SEPARATOR;
                thousands = mNumber / 1000000000;
                mNumber = mNumber - (1000000000 * thousands);

                word += getWordOfDigit(thousands, 1000000000L, false);
            }

            // Number less than BILLION
            if (mNumber > 999999 && mNumber < 1000000000) {
                word += (word == "") ? "" : SPACE_SEPARATOR;
                thousands = mNumber / 1000000;
                mNumber = mNumber - (1000000 * thousands);

                word += getWordOfDigit(thousands, 1000000L, false);
            }

            // Number between 1000 and 999999
            if (mNumber > 999 && mNumber < 1000000) {
                word += (word == "") ? "" : SPACE_SEPARATOR;
                thousands = mNumber / 1000;
                mNumber = mNumber - (1000 * thousands);

                word += getWordOfDigit(thousands, 1000L, true);
            }

            word += ((word == "" || getWordOfLowerDigit(mNumber) == "") ? "" : SPACE_SEPARATOR) + getWordOfLowerDigit(mNumber);
        }
        return word;
    }

    private static String getWordOfDigit(long digit, long digitUsed, boolean onlyFourThousands) {
        String word = (digit == 1)
                ?
                (
                        onlyFourThousands
                                ? NumberToWordFrench.ARRAY_FRENCH.get(1000L)
                                : NumberToWordFrench.ARRAY_FRENCH.get(1L) + SPACE_SEPARATOR + NumberToWordFrench.ARRAY_FRENCH.get(digitUsed)
                )
                :
                (
                        getWordOfLowerDigit(digit).equals("deux cents")
                                ? getWordOfLowerDigit(digit).substring(0, getWordOfLowerDigit(digit).length() - 1)
                                : getWordOfLowerDigit(digit)
                )
                        + SPACE_SEPARATOR
                        + NumberToWordFrench.ARRAY_FRENCH.get(digitUsed)
                        + (onlyFourThousands ? "" : S_CHAR);
        return word;
    }

    private static String getWordOfLowerDigit(long number) {
        String word = "";
        long hundreds;
        long tens;
        long units;


        // Number between 100 and 999
        if (number > 99 && number < 1000) {
            hundreds = number / 100;
            units = number % 10;

            word = (hundreds == 1)
                    ? NumberToWordFrench.ARRAY_FRENCH.get(100L)
                    : NumberToWordFrench.ARRAY_FRENCH.get(hundreds)
                    + SPACE_SEPARATOR
                    + NumberToWordFrench.ARRAY_FRENCH.get(100L)
                    + ((units == 0) ? S_CHAR : "");
            number = number - (100 * hundreds);
        }

        // Number between 1 and 99
        if (number > 0 && number < 100) {
            word += (word == "") ? "" : SPACE_SEPARATOR;
            tens = number / 10;
            units = number % 10;

            // Tens & Units
            if (number > 16) {
                if (tens != 7 && tens != 9) {
                    word += NumberToWordFrench.ARRAY_FRENCH.get(tens * 10L) + ((tens == 8 && units == 0) ? S_CHAR : "");
                    // Units
                    if (units != 0) {
                        word += (units == 1 & tens != 8) ? FRENCH_AND_SEPARATOR : DASH_SEPARATOR;
                        word += NumberToWordFrench.ARRAY_FRENCH.get(units);
                    }
                } else {
                    if (units != 0) {
                        word += NumberToWordFrench.ARRAY_FRENCH.get((tens == 7) ? 60L : 80L)
                                + ((units == 1 && tens == 7) ? FRENCH_AND_SEPARATOR : DASH_SEPARATOR)
                                + ((units > 6)
                                ? NumberToWordFrench.ARRAY_FRENCH.get(10L) + DASH_SEPARATOR + NumberToWordFrench.ARRAY_FRENCH.get(units)
                                : NumberToWordFrench.ARRAY_FRENCH.get(units + 10L));
                    } else {
                        word += NumberToWordFrench.ARRAY_FRENCH.get((tens == 7) ? 60L : 80L) + DASH_SEPARATOR + NumberToWordFrench.ARRAY_FRENCH.get(10L);
                    }
                }
            } else {
                word += NumberToWordFrench.ARRAY_FRENCH.get(number);
            }
        }

        return word;
    }
}
