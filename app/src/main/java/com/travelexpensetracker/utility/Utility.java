package com.travelexpensetracker.utility;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by Rini Banerjee on 22-08-2017.
 */

public class Utility {
    public static String getCurrentDateForUserDisplay(){

        String sDate;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String monthString = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthsArray = dfs.getMonths();
        if (month >= 0 && month <= 11 ) {
            monthString = monthsArray[month];
        }
        sDate = String.valueOf(day)+"-"+monthString + "-" + String.valueOf(year);
        return sDate;
    }
    public static String covertToMonth(int month){

        String monthString = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthsArray = dfs.getMonths();
        if (month >= 0 && month <= 11 ) {
            monthString = monthsArray[month];
        }
        return monthString;
    }
}
