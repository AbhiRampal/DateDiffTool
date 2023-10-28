package com.abhi.rampal.datediff;

import com.abhi.rampal.exception.InvalidDateException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
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

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] dates = input.split(", ");
                String earlierDate = dates[0];
                String laterDate = dates[1];

                try {
                    int difference = DateDifference.findDaysBetween(earlierDate, laterDate);
                    System.out.println(earlierDate + ", " + laterDate + ", " + difference);
                } catch (InvalidDateException e) {
                    System.out.println("Invalid dates: "+earlierDate + ", " + laterDate +": "+ e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

}
