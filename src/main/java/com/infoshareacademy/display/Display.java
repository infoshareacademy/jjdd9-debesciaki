package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        STDOUT.info("{}Czerwony{}Zielony{}Niebieski{}Żółty{}\n", ConsoleColor.RED, ConsoleColor.GREEN, ConsoleColor.BLUE, ConsoleColor.YELLOW, ConsoleColor.RESET);
    }

    public void currentEvents() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");     //("yyyy-MM-dd'T'HH:mm:ssZ");
        STDOUT.info("{}\n", LocalDateTime.now().format(formatter));
        DateTimeFormatter eventFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        //ZonedDateTime.parse("2020-04-21T17:00:00+0200",formatter);// 0 19
        String actT = LocalDateTime.now().format(formatter);
        //sprawdź czy null
        Optional<Integer> compQty = inputElementQuantity("łącznie");
        Optional<Integer> pageMaxElements = inputElementQuantity("na jednej stronie");
        Integer qty = compQty.get();
        Integer pMel = pageMaxElements.get();
        STDOUT.info("Number is: {} \n", qty);


        Map<Integer, Event> eventMap = selectedMap(qty);


        for (Event e : eventMap.values()) {
            Place p = e.getPlace();
            STDOUT.info("Name: {}{}{}\nPlace: {}{}{} \nEnd date: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getName(), ConsoleColor.RESET,
                    ConsoleColor.BLUE, p.getName(), ConsoleColor.RESET,
                    ConsoleColor.CYAN_BACKGROUND, e.getEndDate(), ConsoleColor.RESET);
        }
        STDOUT.info("size of map {}\nsize of a repository  {}\n", eventMap.size(), EventRepository.getAllEvents().size());


    }

    public Optional<Integer> inputElementQuantity(String subject) {
        Integer qty = null;
        Optional<Integer> opt = Optional.ofNullable(qty);
        do {
            STDOUT.info("Ile nadchodzących wydarzeń chcesz zobaczyć {}? --> ",subject);
            try {
                Scanner scanner = new Scanner(System.in);
                opt = Optional.ofNullable(qty = scanner.nextInt());
            } catch (InputMismatchException e) {
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie! msg: {}\n", e.getMessage());
            }
        } while (opt.isEmpty());
        return opt;
    }

    public Map<Integer, Event> selectedMap(int qty) {
        //Selective filling
        Map<Integer, Event> eventMap = new HashMap<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (eventMap.size() < qty) {
                if (compareDateStrings(e.getEndDate())) eventMap.put(e.getId(), e);
            }
        }
        return eventMap;
    }

    public boolean compareDateStrings(String eventT) {
        if (Integer.parseInt(eventT.substring(0, 4)) == LocalDateTime.now().getYear()) {
            if (Integer.parseInt(eventT.substring(5, 7)) == (LocalDateTime.now().getMonthValue())) {
                if (Integer.parseInt(eventT.substring(8, 10)) == LocalDateTime.now().getDayOfMonth()) {
                    if (Integer.parseInt(eventT.substring(11, 13)) == LocalDateTime.now().getHour()) {
                        if (Integer.parseInt(eventT.substring(14, 16)) == LocalDateTime.now().getMinute()) {
                            if (Integer.parseInt(eventT.substring(17, 19)) > LocalDateTime.now().getSecond()) {
                                return true;
                            } else return false;
                        } else if (Integer.parseInt(eventT.substring(14, 16)) > LocalDateTime.now().getMinute()) {
                            return true;
                        } else return false;
                    } else if (Integer.parseInt(eventT.substring(11, 13)) > LocalDateTime.now().getHour()) {
                        return true;
                    } else return false;
                } else if (Integer.parseInt(eventT.substring(8, 10)) > LocalDateTime.now().getDayOfMonth()) {
                    return true;
                } else return false;
            } else if (Integer.parseInt(eventT.substring(5, 7)) > LocalDateTime.now().getMonthValue()) {
                return true;
            } else return false;
        } else if (Integer.parseInt(eventT.substring(0, 4)) > LocalDateTime.now().getYear()) {
            return true;
        } else return false;
    }

    public void displayer(Integer qty, Integer elemPerPage,Map<Integer, Event> eventMap ){

    }

    //this.year =Integer.parseInt(Date.substring(0,4));
    //  this.month=Integer.parseInt(Date.substring(5,7));
    // this.day =Integer.parseInt(Date.substring(8,10));
    // this.hour =Integer.parseInt(Date.substring(11,13));
    // this.minute =Integer.parseInt(Date.substring(14,16));
    //this.seconds=Integer.parseInt(Date.substring(17,19));


}
