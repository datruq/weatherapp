package com.finleap.casestudy.weatherapp.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime stringDateTimeToLocalDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static String localDateTimeToStringDateTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String dateInString = date.format(formatter);
        return dateInString;
    }
}
