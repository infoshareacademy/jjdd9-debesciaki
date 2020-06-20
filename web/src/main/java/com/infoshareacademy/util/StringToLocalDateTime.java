package com.infoshareacademy.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime {
    public static LocalDateTime process(String in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(in, formatter);
    }
}
