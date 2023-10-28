package com.abhi.rampal.util;

public class DateUtil {

    private static int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // a year is leap year when
    // --its exactly divisible by 4
    //   --except when (it isn't exactly divisible by 100)
    //    --except when is exactly divisible by 400
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && !(year % 100 == 0)) || year % 400 == 0;
    }

    public static int getDaysTillMonth(int month, int year) {
        int totalDays = 0;
        for (int i = 0; i < month - 1; i++) {//month-1 because we want to calculate all days till the previous month
            if (i == 1 && DateUtil.isLeapYear(year)) {
                totalDays += 29;
            } else {
                totalDays += daysInMonth[i];
            }
        }
        return totalDays;
    }

    public static int getDaysTillYear(int year) {
        int totalYears = year - 1900;
        int leapYears = totalYears / 4;
        int nonLeapYears = totalYears - leapYears;

        int days = leapYears * 366;
        days += nonLeapYears * 365;
        return days;
    }
}
