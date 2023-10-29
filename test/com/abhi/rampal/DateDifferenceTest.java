package com.abhi.rampal;

import com.abhi.rampal.datediff.DateDifference;
import com.abhi.rampal.exception.InvalidDateException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DateDifferenceTest {

    @ParameterizedTest
    @CsvSource( {
            "13 11 2018, 13 11 2018, 0",
            "01 01 1900, 01 01 1900, 0",
            "13 11 2018, 14 11 2018, 1",
            "01 01 1900, 02 01 1900, 1",
            "13 11 2018, 15 11 2018, 2",
            "13 11 2018, 13 12 2018, 30", //test difference where month has 30 days
            "13 10 2018, 13 11 2018, 31", //test difference where month has 31 days
            "13 02 2018, 13 03 2018, 28", //test difference where Feb has 28 days
            "13 02 2020, 13 03 2020, 29",  //test difference where Feb has 29 days
            "13 01 2020, 13 03 2020, 60",  // 2months
            "13 12 2019, 13 03 2020, 91",  // ~3months
            "13 11 2019, 13 03 2020, 121",  // ~4months
            "13 10 2019, 13 03 2020, 152",  // ~5months
            "13 09 2019, 13 03 2020, 182",  // ~6months
            "13 08 2019, 13 03 2020, 213",  // ~7months
            "13 07 2019, 13 03 2020, 244",  // ~8months
            "13 06 2019, 13 03 2020, 274",  // ~9months
            "13 05 2019, 13 03 2020, 305",  // ~10months
            "13 04 2019, 13 03 2020, 335",  // ~11months
            "13 03 2019, 13 03 2020, 366",  // ~12months
            "13 02 2019, 13 02 2020, 365", //1 non leap year
            "13 03 2019, 13 03 2020, 366", //1 leap year
            "13 02 2018, 13 02 2020, 730", //2 non leap year
            "03 01 2018, 28 02 2020, 786", //2 non leap year + 1 month + few days
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
        assertEquals(difference, DateDifference.findDaysBetween(earlierDate, laterDate));
    }


    @ParameterizedTest
    @CsvSource( {
            "32 02 2020, 31 12 2020, INVALID_DAY, Invalid day. Day should be between 1 and 31", //day after  acceptable range
            "03 02 2020, 00 12 2020, INVALID_DAY, Invalid day. Day should be between 1 and 31", //day before acceptable range
            "02 13 2020, 31 12 2020, INVALID_MONTH, Invalid month. Month should be between 1 and 12", //month is higher than acceptable range
            "02 12 2020, 31 -1 2020, INVALID_MONTH, Invalid month. Month should be between 1 and 12", //month is lower than acceptable range
            "02 02 1899, 31 12 2020, INVALID_YEAR, Invalid year. Year should be between 1900 and 2020", //year is lower than acceptable range
            "02 02 1899, 31 12 2025, INVALID_YEAR, Invalid year. Year should be between 1900 and 2020", //year is higher than acceptable range
            "02 02 2020, 02 02 2019, FIRST_LATER_THAN_SECOND, Invalid dates. First date is later than second date", //first date later than second date
            "02 022020, 02 02 2019, INVALID_DATE_FORMAT, Invalid date format. Expected format is DD MM YYYY", //invalid date format
    })
    void testExceptionThrownWhenInvalidDate(String earlierDate, String laterDate, String errorCode, String errorMessage) {
        InvalidDateException exception = assertThrows(
                InvalidDateException.class, () -> DateDifference.findDaysBetween(earlierDate, laterDate));
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(errorMessage, exception.getMessage());
    }

}