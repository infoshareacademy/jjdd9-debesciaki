package com.infoshareacademy;

import com.infoshareacademy.parser.ParseService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {
        new ParseService().parseFiles();
    }
}
