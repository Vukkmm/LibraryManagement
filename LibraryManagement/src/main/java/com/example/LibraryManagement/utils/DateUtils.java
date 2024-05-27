package com.example.LibraryManagement.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    /**
     * method get object LocalDateTime(convert String to LocalDateTime)
     * */
    public static LocalDateTime convertStringToLocalDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public static String getDueToDateTimeString() {
        LocalDateTime now = LocalDateTime.now().plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }


    public static long calculateDurationInSeconds(String borrowDateTimeString, String returnDateTimeString) {
        LocalDateTime borrowDateTime = convertStringToLocalDateTime(borrowDateTimeString);
        LocalDateTime returnDateTime = convertStringToLocalDateTime(returnDateTimeString);

        Duration duration = Duration.between(borrowDateTime, returnDateTime);
        return duration.getSeconds();
        /**
         *  or .toMinutes(), .toHours(), .toDays() tùy nhu cầu
         *  */
    }




    public static String getCurrentDateString() {
        return LocalDate.now().toString();
    }

    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
