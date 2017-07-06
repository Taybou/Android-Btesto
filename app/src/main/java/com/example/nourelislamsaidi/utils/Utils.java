package com.example.nourelislamsaidi.utils;

/**
 * Created by MOHAMEDTAYEBBenterki on 03/06/2017.
 */

public class Utils {

    private static final String SPACE_SEPARATOR = " ";
    private static final String AND_SEPARATOR = " et ";
    private static final String DASH_SEPARATOR = "-";
    private static final String POINT_SEPARATOR = "\\.";
    private static final String S_CHAR = "s";

    public String getFrenchWord(String number, String currency) {
        String word;
        String beforePoint = number.split(POINT_SEPARATOR)[0];
        word = getWordFromNumber(beforePoint) + SPACE_SEPARATOR + currency;
        if (number.contains(".")) {
            String afterPoint = number.split(POINT_SEPARATOR)[1];
            word += AND_SEPARATOR + getWordFromNumber(afterPoint);
        }

        return word;
    }

    private String getWordFromNumber(String number) {
        String word = "";
        long thousands;
        long mNumber = Long.valueOf(number);

        // Number greater than TRILLION
        if ((mNumber > 999999999999L)) {
            thousands = mNumber / 1000000000000L;

            word += (thousands == 1)
                    ? NumberToWordFrench.ARRAY_FRENCH.get(1L) + SPACE_SEPARATOR + NumberToWordFrench.ARRAY_FRENCH.get(1000000000000L)
                    :
                    (
                            (getWordOfLowerDigit(thousands).equals("deux cents"))
                                    ? getWordOfLowerDigit(thousands).substring(0, getWordOfLowerDigit(thousands).length() - 1)
                                    : getWordOfLowerDigit(thousands)
                    )
                            + SPACE_SEPARATOR
                            + NumberToWordFrench.ARRAY_FRENCH.get(1000000000000L)
                            + S_CHAR;

            mNumber = mNumber - (1000000000000L * thousands);
        }

        // Number less than TRILLION
        if ((mNumber > 999999999) && (mNumber < 1000000000000L)) {
            word += (word == "") ? "" : SPACE_SEPARATOR;
            thousands = mNumber / 1000000000;

            word += (thousands == 1)
                    ? NumberToWordFrench.ARRAY_FRENCH.get(1L) + SPACE_SEPARATOR + NumberToWordFrench.ARRAY_FRENCH.get(1000000000L)
                    :
                    (
                            (getWordOfLowerDigit(thousands).equals("deux cents"))
                                    ? getWordOfLowerDigit(thousands).substring(0, getWordOfLowerDigit(thousands).length() - 1)
                                    : getWordOfLowerDigit(thousands)
                    )
                            + SPACE_SEPARATOR
                            + NumberToWordFrench.ARRAY_FRENCH.get(1000000000L)
                            + S_CHAR;

            mNumber = mNumber - (1000000000 * thousands);
        }

        // Number less than BILLION
        if (mNumber > 999999 && mNumber < 1000000000) {
            word += (word == "") ? "" : SPACE_SEPARATOR;
            thousands = mNumber / 1000000;

            word += (thousands == 1)
                    ? NumberToWordFrench.ARRAY_FRENCH.get(1L) + SPACE_SEPARATOR + NumberToWordFrench.ARRAY_FRENCH.get(1000000L)
                    :
                    (
                            (getWordOfLowerDigit(thousands).equals("deux cents"))
                                    ? getWordOfLowerDigit(thousands).substring(0, getWordOfLowerDigit(thousands).length() - 1)
                                    : getWordOfLowerDigit(thousands)
                    )
                            + SPACE_SEPARATOR
                            + NumberToWordFrench.ARRAY_FRENCH.get(1000000L)
                            + S_CHAR;

            mNumber = mNumber - (1000000 * thousands);
        }

        // Number between 1000 and 999999
        if (mNumber > 999 && mNumber < 1000000) {
            word += (word == "") ? "" : SPACE_SEPARATOR;
            thousands = mNumber / 1000;

            word += (thousands == 1)
                    ? NumberToWordFrench.ARRAY_FRENCH.get(1000L)
                    :
                    (
                            (getWordOfLowerDigit(thousands).equals("deux cents"))
                                    ? getWordOfLowerDigit(thousands).substring(0, getWordOfLowerDigit(thousands).length() - 1)
                                    : getWordOfLowerDigit(thousands)
                    )
                            + SPACE_SEPARATOR
                            + NumberToWordFrench.ARRAY_FRENCH.get(1000L);

            mNumber = mNumber - (1000 * thousands);
        }

        word += ((word == "" || getWordOfLowerDigit(mNumber) == "") ? "" : SPACE_SEPARATOR) + getWordOfLowerDigit(mNumber);
        return word;
    }

    // Number low than 1000
    private String getWordOfLowerDigit(long number) {
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
                        word += (units == 1 & tens != 8) ? AND_SEPARATOR : DASH_SEPARATOR;
                        word += NumberToWordFrench.ARRAY_FRENCH.get(units);
                    }
                } else {
                    if (units != 0) {
                        word += NumberToWordFrench.ARRAY_FRENCH.get((tens == 7) ? 60L : 80L)
                                + ((units == 1 && tens == 7) ? AND_SEPARATOR : DASH_SEPARATOR)
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
