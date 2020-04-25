package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    String pattern;

    public void displayCurrentEvents(String pattern) {
        this.pattern = pattern;
        Optional<Integer> compQty = inputInteger("Ile nadchodzących wydarzeń chcesz zobaczyć łącznie? ");
        Optional<Integer> pageMaxElements = inputInteger("Ile wydarzeń chcesz zobaczyć na jednej stronie? ");
        Integer qty = compQty.get();
        Integer elemPerPage = pageMaxElements.get();
        List<Event> eventList = selectedList(qty);
        displayPages(qty, elemPerPage, eventList);
    }

    public Optional<Integer> inputInteger(String subject) {
        Integer qty = null;
        Optional<Integer> opt = Optional.ofNullable(qty);
        do {
            STDOUT.info("{}", subject);
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
                if (isAfterNow(e.getEndDate())) eventMap.put(e.getId(), e);
            }
        }
        return eventMap;
    }

    public List<Event> selectedList(int qty) {
        //Selective filling
        List<Event> eventList = new ArrayList<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (eventList.size() < qty) {
                if (isAfterNow(e.getEndDate())) eventList.add(e);
            }
        }
        return eventList;
    }

    public boolean isAfterNow(String eventTime) {
        return eventLDT(eventTime).isAfter(LocalDateTime.now());
    }

    public void displayPages(Integer qty, Integer elemPerPage, List<Event> eventList) {
        Optional<Integer> decision = null;
        double pageCountd = Math.ceil((double) qty / elemPerPage);
        Integer pageCount = (int) pageCountd;
        int limU = 0, limD = elemPerPage, actual = 1;
        do {
            cleanConsole();
            for (int i = limU; i < limD; i++) {
                if (i < eventList.size()) {
                    Event e = eventList.get(i);
                    consoleEventScheme(e);
                }
            }
            if (actual == 1) {
                decision = inputInteger("0 - Wyjdź\n2 - Następna\nStrona nr " + actual + "\nTwój wybór to: ");
            }
            if (actual == pageCount) {
                decision = inputInteger("0 - Wyjdź\n1 - Poprzednia\nStrona nr " + actual + "\nTwój wybór to: ");
            }
            if (actual > 1 && actual < pageCount) {
                decision = inputInteger("0 - Wyjdź\n1 - Poprzednia\n2 - Następna\nStrona nr " + actual + "\nTwój wybór to: ");
            }

            int dec = decision.get();
            if (actual > 1 && dec == 1) {
                actual--;
                limU -= elemPerPage;
                limD -= elemPerPage;
            } else if (actual < pageCount && dec == 2) {
                actual++;
                limU += elemPerPage;
                limD += elemPerPage;
            } else if (dec == 0) {
                break;
            }
        } while (decision.get() != 0);
    }

    public void consoleEventScheme(Event e) {
        Place p = e.getPlace();
        String eventTimeFormatted = null;
        Optional<String> opt = Optional.ofNullable(eventTimeFormatted);
        if (this.pattern.isBlank() || this.pattern.isEmpty()) this.pattern = "yyyy-MM-dd HH:mm:ss";
        do {
            try {
                eventTimeFormatted = configureDate(e.getEndDate(), this.pattern);
                opt = Optional.ofNullable(eventTimeFormatted);
            } catch (Exception exception) {
                Timer timer = new Timer();
                STDOUT.info("Niepoprawny format daty w pliku konfiguracyjnym, proszę popraw konfigurację i poczekaj na odświeżenie aplikacji.\n");
                try {
                    timer.wait(6000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        } while (opt.isEmpty());

        STDOUT.info("Name: {}{}{}\nPlace: {}{}{} \nEnd date: {}{}{}\n",
                ConsoleColor.RED_UNDERLINED, e.getName(), ConsoleColor.RESET,
                ConsoleColor.BLUE, p.getName(), ConsoleColor.RESET,
                ConsoleColor.BLUE, eventTimeFormatted, ConsoleColor.RESET);
    }

    public String configureDate(String eventTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return eventLDT(eventTime).format(formatter);
    }

    public LocalDateTime eventLDT(String eventTime) {
        String subEventTime = eventTime.substring(0, 19);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(subEventTime, formatter);
    }
}
