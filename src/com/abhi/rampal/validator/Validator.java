package com.abhi.rampal.validator;

import com.abhi.rampal.exception.InvalidDateException;
import com.abhi.rampal.util.DateUtil;

public class Validator {

    public static void validateDate(int day, int month, int year) throws InvalidDateException {
        if (day < 1 || day > 31) {
            throw new InvalidDateException("Invalid day. Day should be between 1 and 31", "INVALID_DAY");
        }
        if (month < 1 || month > 12) {
            throw new InvalidDateException("Invalid month. Month should be between 1 and 12", "INVALID_MONTH");
        }
        if (year < 1900 || year > 2020) {
            throw new InvalidDateException("Invalid year. Year should be between 1900 and 2020", "INVALID_YEAR");
        }
        if (month == 2) {
            boolean isLeapYear = DateUtil.isLeapYear(year);
            if (isLeapYear && day > 29) {
                throw new InvalidDateException("Invalid Date for February. Your date cannot be higher than 29 for a leap " +
                        "year", "INVALID_FEB_DAY_NON_LEAP_YEAR");
            } else if (!isLeapYear && day > 28) {
                throw new InvalidDateException("Invalid Date for February. Your date cannot be higher than 28 for a non leap " +
                        "year", "INVALID_FEB_DAY_LEAP_YEAR");
            }
        }
    }
}

