package com.infoshareacademy.display;

import com.infoshareacademy.parser.Category;
import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.CategoryRepository;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n", ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ConsoleColor.YELLOW, ConsoleColor.RESET);
    }

    public void events() {
        //declare + init hashmap
        Map<Integer, Event> eventMap = new HashMap<Integer, Event>();

        //Loop to fill hashmap with list of places
        for (Event e : EventRepository.getAllEvents()) {
            eventMap.put(e.getId(), e);
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
    }
}
