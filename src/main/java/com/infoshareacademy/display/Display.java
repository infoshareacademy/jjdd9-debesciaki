package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n", ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ConsoleColor.YELLOW, ConsoleColor.RESET);
    }

    public void currentEvents() throws IOException, InterruptedException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateToInt actualT = new DateToInt();

        actualT.convert(formatter.format(date));

        //Ask for how many elements user wants to see
        Scanner scanner = new Scanner(System.in);
        STDOUT.info("Ile nadchodzących wydarzeń chcesz zobaczyć? --> ");
        int qty = scanner.nextInt();

        Map<Integer, Event> eventMap = selectedMap(actualT, qty);


        for (Event e : eventMap.values()) {
            Place p = e.getPlace();
            STDOUT.info("Name: {}{}{}\nPlace: {}{}{} \nEnd date: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getName(), ConsoleColor.RESET,
                    ConsoleColor.BLUE, p.getName(), ConsoleColor.RESET,
                    ConsoleColor.CYAN_BACKGROUND, e.getEndDate(), ConsoleColor.RESET);
        }
        STDOUT.info("size of map {}\nsize of a repository  {}\n", eventMap.size(), EventRepository.getAllEvents().size());
    }

    public Map<Integer, Event> selectedMap(DateToInt actualT, int qty) {
        DateToInt listedT = new DateToInt();
        //Selective filling
        Map<Integer, Event> eventMap = new HashMap<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (eventMap.size() < qty) {
                listedT.convert(e.getEndDate());
                if (listedT.getYear() == actualT.getYear()) {
                    if (listedT.getMonth() == actualT.getMonth()) {
                        if (listedT.getDay() == actualT.getDay()) {
                            if (listedT.dayTimeSec() > actualT.dayTimeSec()) {
                                eventMap.put(e.getId(), e);
                            }
                        } else if (listedT.getDay() > actualT.getDay()) {
                            eventMap.put(e.getId(), e);
                        }
                    } else if (listedT.getMonth() > actualT.getMonth()) {
                        eventMap.put(e.getId(), e);
                    }
                } else if (listedT.getYear() > actualT.getYear()) {
                    eventMap.put(e.getId(), e);
                }
            }
        }
        return eventMap;
    }
    public DateToInt actualTime(){return null;}
}
