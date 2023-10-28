package com.abhi.rampal.datediff;

import com.abhi.rampal.exception.InvalidDateException;
import com.abhi.rampal.util.DateUtil;
import com.abhi.rampal.validator.Validator;

public class DateDifference {

    public static int findDaysBetween(String earlierDate, String laterDate) throws InvalidDateException {
        int[] date1= parseDate(earlierDate);
        int[] date2 = parseDate(laterDate);
        int days1 = dateToDays(date1);
        int days2 = dateToDays(date2);
        if (days1 > days2) {
            throw new InvalidDateException("Invalid dates. First date is later than second date",
                    "FIRST_LATER_THAN_SECOND");
        }
        return Math.abs(days2 - days1);

    }

    public static int[] parseDate(String date) throws InvalidDateException {
        String[] parts = date.split(" ");
        if (parts.length != 3) {
            throw new InvalidDateException("Invalid date format. Expected format is DD MM YYYY", "INVALID_DATE_FORMAT");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        Validator.validateDate(day, month, year);
        return new int[]{day, month, year};
    }

    public static int dateToDays(int[] date) {
        int day = date[0];
        int month = date[1];
        int year = date[2];

        int totalDays = day;
        totalDays += DateUtil.getDaysTillMonth(month, year);
        totalDays += DateUtil.getDaysTillYear(year - 1);
        return totalDays;
    }




}
