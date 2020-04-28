package com.infoshareacademy;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.ParseService;
import com.infoshareacademy.repository.EventRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        new ParseService().parseFiles();
       List<Event> list = EventRepository.getAllEvents();
        System.out.println(list.get(10).getEndDate().toString());

    }
}
