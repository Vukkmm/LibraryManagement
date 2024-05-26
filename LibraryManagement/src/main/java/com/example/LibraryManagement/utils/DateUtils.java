package com.example.LibraryManagement.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getCurrentDateString() {
        return LocalDate.now().toString();
    }
    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }


    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getDueToDateTimeString() {
        LocalDateTime now = LocalDateTime.now().plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
