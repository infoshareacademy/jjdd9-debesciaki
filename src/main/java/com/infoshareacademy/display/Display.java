package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n", ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ConsoleColor.YELLOW, ConsoleColor.RESET);
    }

    public void currentEvents() {
        //current date
        Date date = new Date();
        //date conversion
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateToInt actualT = new DateToInt();
        DateToInt listedT = new DateToInt();
        actualT.convert(formatter.format(date));


        //declare + init hashmap
        Map<Integer, Event> eventMap = new HashMap<Integer, Event>();
        Map<Integer, Event> eventMap2 = new HashMap<Integer, Event>();
        int iteracje2=0;
        int iteracje=0;

        //Loop to fill hashmap with list of places

        for (Event e : EventRepository.getAllEvents()) {
            eventMap2.put(e.getId(), e);
            iteracje2++;
        }


        //Selective filling


        for (Event e : EventRepository.getAllEvents()) {
            listedT.convert(e.getEndDate());
            if (listedT.getYear() == actualT.getYear()) {
                if (listedT.getMonth() == actualT.getMonth()) {
                    if (listedT.getDay() == actualT.getDay()) {
                        if (listedT.dayTimeSec() > actualT.dayTimeSec()) {
                            eventMap.put(e.getId(), e);
                            iteracje++;
                        }
                    } else if (listedT.getDay() > actualT.getDay()) {
                        eventMap.put(e.getId(), e);
                        iteracje++;
                    }
                } else if (listedT.getMonth() > actualT.getMonth()) {
                    eventMap.put(e.getId(), e);
                    iteracje++;
                }
            } else if (listedT.getYear() > actualT.getYear()) {
                eventMap.put(e.getId(), e);
                iteracje++;
            }
        }






        for (Event e : EventRepository.getAllEvents()) {
           /* Category c = categoryMap.get(e.getCategoryId());
            String catName;
            if (c==null) {
                catName = "brak informacji/błędne ID";
            } else {
                catName = c.getName();
            }*/
            Place p = e.getPlace();
            STDOUT.info("Name: {}{}{}\nPlace: {}{}{} \nEnd date: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getName(), ConsoleColor.RESET,
                    ConsoleColor.BLUE, p.getName(), ConsoleColor.RESET,
                    ConsoleColor.CYAN_BACKGROUND, e.getEndDate(), ConsoleColor.RESET);

            //STDOUT.info("Place ID: {} \n", p.getId());
        }
        STDOUT.info("size of map {}\nsize of a repository  {}\niteracje {}\npełna mapa {}\n" ,eventMap.size(),EventRepository.getAllEvents().size(),iteracje,iteracje2);
    }
}
