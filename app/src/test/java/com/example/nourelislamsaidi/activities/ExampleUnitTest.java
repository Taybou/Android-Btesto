package com.example.nourelislamsaidi.activities;

import com.example.nourelislamsaidi.utils.Utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Utils utils;

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
    }

    @Test
    public void testDigit() throws Exception {
        // TEST ANY

        assertEquals("vingt et un EURO et vingt-six", utils.getFrenchWord("21.26", "EURO"));

        assertEquals("vingt et un EURO", utils.getFrenchWord("21", "EURO"));
        assertEquals("vingt-six EURO", utils.getFrenchWord("26", "EURO"));

        // TEST 70
        assertEquals("soixante-dix EURO", utils.getFrenchWord("70", "EURO"));
        assertEquals("soixante et onze EURO", utils.getFrenchWord("71", "EURO"));
        assertEquals("soixante-douze EURO", utils.getFrenchWord("72", "EURO"));
        assertEquals("soixante-treize EURO", utils.getFrenchWord("73", "EURO"));
        assertEquals("soixante-quatorze EURO", utils.getFrenchWord("74", "EURO"));
        assertEquals("soixante-dix-huit EURO", utils.getFrenchWord("78", "EURO"));

        // TEST 80
        assertEquals("quatre-vingts EURO", utils.getFrenchWord("80", "EURO"));
        assertEquals("quatre-vingt-un EURO", utils.getFrenchWord("81", "EURO"));
        assertEquals("quatre-vingt-deux EURO", utils.getFrenchWord("82", "EURO"));

        // TEST 90
        assertEquals("quatre-vingt-dix EURO", utils.getFrenchWord("90", "EURO"));
        assertEquals("quatre-vingt-onze EURO", utils.getFrenchWord("91", "EURO"));
        assertEquals("quatre-vingt-douze EURO", utils.getFrenchWord("92", "EURO"));
        assertEquals("quatre-vingt-dix-neuf EURO", utils.getFrenchWord("99", "EURO"));


        // TEST 100
        assertEquals("cent EURO", utils.getFrenchWord("100", "EURO"));
        assertEquals("cent cinq EURO", utils.getFrenchWord("105", "EURO"));
        assertEquals("cent soixante et onze EURO", utils.getFrenchWord("171", "EURO"));
        assertEquals("cent soixante-dix-huit EURO", utils.getFrenchWord("178", "EURO"));

        // TEST 300
        assertEquals("trois cents EURO", utils.getFrenchWord("300", "EURO"));
        assertEquals("trois cent trente-trois EURO", utils.getFrenchWord("333", "EURO"));

        //TEST 1000
        assertEquals("mille EURO", utils.getFrenchWord("1000", "EURO"));
        assertEquals("mille quatorze EURO", utils.getFrenchWord("1014", "EURO"));
        assertEquals("trois mille EURO", utils.getFrenchWord("3000", "EURO"));
        assertEquals("douze mille EURO", utils.getFrenchWord("12000", "EURO"));
        assertEquals("dix-huit mille EURO", utils.getFrenchWord("18000", "EURO"));
        assertEquals("cent mille EURO", utils.getFrenchWord("100000", "EURO"));
        assertEquals("deux cent vingt-deux mille deux cent vingt-deux EURO", utils.getFrenchWord("222222", "EURO"));
        assertEquals("neuf cent quatre-vingt-dix-neuf mille neuf cent quatre-vingt-dix-neuf EURO", utils.getFrenchWord("999999", "EURO"));
        assertEquals("deux cent mille EURO", utils.getFrenchWord("200000", "EURO"));

        // >Million
        assertEquals("deux millions deux cent vingt-deux mille deux cent vingt-deux EURO", utils.getFrenchWord("2222222", "EURO"));
        assertEquals("neuf millions EURO", utils.getFrenchWord("9000000", "EURO"));
        assertEquals("douze millions EURO", utils.getFrenchWord("12000000", "EURO"));


        // BILLION
        assertEquals("un milliard trois EURO", utils.getFrenchWord("1000000003", "EURO"));
        assertEquals("six milliards six cent soixante-six millions six cent soixante-six mille six cent soixante-six EURO", utils.getFrenchWord("6666666666", "EURO"));

        // TRILLION
        assertEquals("un billion EURO", utils.getFrenchWord("1000000000000", "EURO"));
        assertEquals("six cent soixante-six billions six cent soixante-six milliards six cent soixante-six millions six cent soixante-six mille six cent soixante-six EURO",
                utils.getFrenchWord("666666666666666", "EURO"));

        // IF WE NEED ANOTHER USE CASE
        // assertEquals(" EURO", utils.getFrenchWord("", "EURO"));
    }
}