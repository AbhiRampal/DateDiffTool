package com.abhi.rampal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DateDifference {

    private static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner scanner;
        try {
            // if there is a command line argument, treat it as a file path
            if (args.length > 0) {
                File file = new File(args[0]);
                scanner = new Scanner(file);
            } else {
                // otherwise, read from standard input
                scanner = new Scanner(System.in);
            }

            String earlierDate = scanner.nextLine();
            String laterDate = scanner.nextLine();

            int difference = dateDifference(earlierDate, laterDate);

            System.out.println(earlierDate + ", " + laterDate + ", " + difference);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidDateException e) {
            e.printStackTrace();
        }
    }

    public static int dateDifference(String earlierDate, String laterDate) throws InvalidDateException {
        int[] date1= parseDate(earlierDate);
        int[] date2 = parseDate(laterDate);
        int days1 = dateToDays(date1);
        int days2 = dateToDays(date2);
        return Math.abs(days2 - days1);

    }

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
    }

    public static int[] parseDate(String date) throws InvalidDateException {
        String[] parts = date.split(" ");
        if (parts.length != 3) {
            throw new InvalidDateException("Invalid date format. Expected format is DD MM YYYY", "INVALID_DATE_FORMAT");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        validateDate(day, month, year);
        return new int[]{day, month, year};
    }

    public static int dateToDays(int[] date) {
        int day = date[0];
        int month = date[1];
        int year = date[2];

        int totalDays = day;
        totalDays += getDaysTillCurrentMonth(month, year);
        totalDays += getDaysTillCurrentYear(year - 1);
        return totalDays;
    }

    private static int getDaysTillCurrentMonth(int month, int year){
        int totalDays = 0;
        for (int i=0; i<month-1; i++ ) {//month-1 because we want to calculate all days till the previous month
            if (i == 1 && isLeapYear(year)){
                totalDays += 29;
            } else {
                totalDays += monthDays[i];
            }
        }
        return totalDays;
    }

    private static int getDaysTillCurrentYear(int year){
        int totalYears = year - 1900;
        int leapYears = totalYears / 4 ;
        int nonLeapYears = totalYears -leapYears;

        int days = leapYears*366;
        days += nonLeapYears*365;
        return days;
    }

    // a year is leap year when
    // --its exactly divisible by 4
    //   --except when (it isn't exactly divisible by 100)
    //    --except when is exactly divisible by 400
    private static boolean isLeapYear(int year){
        if (year % 4 == 0){
            if (year % 100 == 0){
                if (year % 400 == 0 ){
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
