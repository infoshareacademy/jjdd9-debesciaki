package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n", ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ConsoleColor.YELLOW, ConsoleColor.RESET);
    }

    public void currentEvents() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        STDOUT.info("{}\n", LocalDate.now().format(formatter));
        DateTimeFormatter eventFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        //sprawdź czy null
        Optional<Integer> optInt = inputElementQuantity();
        Integer qty = optInt.get();
        STDOUT.info("Number is: {} \n", qty);



        //Map<Integer, Event> eventMap = selectedMap(actualT, qty);

        /*
        for (Event e : eventMap.values()) {
            Place p = e.getPlace();
            STDOUT.info("Name: {}{}{}\nPlace: {}{}{} \nEnd date: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getName(), ConsoleColor.RESET,
                    ConsoleColor.BLUE, p.getName(), ConsoleColor.RESET,
                    ConsoleColor.CYAN_BACKGROUND, e.getEndDate(), ConsoleColor.RESET);
        }
        STDOUT.info("size of map {}\nsize of a repository  {}\n", eventMap.size(), EventRepository.getAllEvents().size());

         */
    }

    public Optional<Integer> inputElementQuantity() {
        Integer qty = null;
        Optional<Integer> opt = Optional.ofNullable(qty);
        do {
            STDOUT.info("Ile nadchodzących wydarzeń chcesz zobaczyć? --> ");
            try {
                Scanner scanner = new Scanner(System.in);
                opt = Optional.ofNullable(qty = scanner.nextInt());
            } catch (InputMismatchException e) {
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie! msg: {}\n", e.getMessage());
            }
        } while (opt.isEmpty());
        return opt;
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
}
