package com.abhi.rampal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DateDifferenceTest {

    @ParameterizedTest
    @CsvSource( {
            "13 11 2018, 13 11 2018, 0",
            "13 11 2018, 14 11 2018, 1",
            "13 11 2018, 15 11 2018, 2",
            "13 11 2018, 13 12 2018, 30", //test difference where month has 30 days
            "13 10 2018, 13 11 2018, 31", //test difference where month has 31 days
            "13 02 2018, 13 03 2018, 28", //test difference where Feb has 28 days
            "13 02 2020, 13 03 2020, 29",  //test difference where Feb has 29 days
            "13 02 2019, 13 02 2020, 365", //1 non leap year
            "13 03 2019, 13 03 2020, 366", //1 leap year
            "13 02 2018, 13 02 2020, 730", //2 non leap year
            "13 03 2018, 13 03 2020, 731", //1 non leap year + leap year
            "13 03 2017, 13 03 2020, 1096", //2 non leap year + leap year
            "13 03 2016, 13 03 2020, 1461", //3 non leap year + leap year
            "13 03 2019, 13 04 2020, 397", //leap year + 1 month of 31 days
            "13 04 2019, 13 05 2020, 396", //leap year + 1 month of 30 days
            "13 03 2018, 13 04 2019, 396", //non leap year + 1 month of 31 days
            "13 04 2018, 13 05 2019, 395", //non leap year + 1 month of 30 days
            "13 02 1900, 13 03 1900, 28", //non leap month of 28 days
            "13 02 1904, 13 03 1904, 29", //leap month of 28 days
            "13 02 1900, 13 03 1901, 393", //non leap year + non leap month of 28 days
            "13 02 1904, 13 03 1905, 394", //leap year + month of 28 days
            "01 01 1900, 31 12 2020, 44194", //extreme end of the date range
    })
    void testDateDifference(String earlierDate, String laterDate, int difference) throws InvalidDateException {
        assertEquals(difference, DateDifference.dateDifference(earlierDate, laterDate));
    }


    @ParameterizedTest
    @CsvSource( {
            "32 02 2020, 31 12 2020",
            "02 13 2020, 31 12 2020",
            "02 02 1899, 31 12 2020",
    })
    void testExceptionThrownWhenInvalidDate(String earlierDate, String laterDate) {
        assertThrows(InvalidDateException.class, () -> DateDifference.dateDifference(earlierDate, laterDate));
    }

}