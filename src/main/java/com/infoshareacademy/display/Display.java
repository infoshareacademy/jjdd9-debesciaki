package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.repository.EventRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class Display {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    String pattern;
    Integer qty;
    Integer elemPerPage;
    boolean firstStart;

    public void displayCurrentEvents(String pattern) {
        this.pattern = pattern;
        cleanConsole();
        Optional<Integer> compQty;
        Optional<Integer> pageMaxElements;
        firstStart = true;
        do {
            if (!firstStart) {
                cleanConsole();
                STDOUT.info("Podano zerowe lub ujemne wartości parametrów, proszę wprowadzić je ponownie.\n");
            }
            firstStart = false;
            compQty = inputInteger("Ile nadchodzących wydarzeń chcesz zobaczyć łącznie? ");
            pageMaxElements = inputInteger("Ile wydarzeń chcesz zobaczyć na jednej stronie? ");
            qty = compQty.get();
            elemPerPage = pageMaxElements.get();
        } while (qty <= 0 || elemPerPage <= 0);
        List<Event> eventList = selectedList(qty);
        displayPages(qty, elemPerPage, eventList, this.pattern);
    }

    public Optional<Integer> inputInteger(String subject) {
        Integer qty = null;
        Optional<Integer> opt = Optional.ofNullable(qty);
        String in;
        do {
            STDOUT.info("{}", subject);
            Scanner scanner = new Scanner(System.in);
            if (NumberUtils.isDigits(in = scanner.nextLine())) {
                qty = Integer.parseInt(in);
            }
            opt = Optional.ofNullable(qty);
            if (!NumberUtils.isDigits(in)) {
                cleanConsole();
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie!\n");
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
            if (eventList.size() < qty && eventList.size() < EventRepository.getAllEvents().size()) {
                if (isAfterNow(e.getEndDate())) eventList.add(e);
            }
        }
        return eventList;
    }

    public boolean isAfterNow(String eventTime) {
        return eventLDT(eventTime).isAfter(LocalDateTime.now());
    }

    public void displayPages(Integer qty, Integer elemPerPage, List<Event> eventList, String pattern) {
        Optional<Integer> decision = null;
        double pageCountd = Math.ceil((double) qty / elemPerPage);
        Integer pageCount = (int) pageCountd;
        int limU = 0, limD = elemPerPage, actual = 1;
        do {
            cleanConsole();
            for (int i = limU; i < limD; i++) {
                if (i < eventList.size()) {
                    Event e = eventList.get(i);
                    consolePrintEventScheme(e, pattern);
                }
            }

            if (actual == 1 && pageCount > 1) {
                decision = inputInteger("0 - Wyjdź\n2 - Następna\nStrona nr " + actual + "\nTwój wybór to: ");
            }
            if (actual == pageCount && actual != 1) {
                decision = inputInteger("0 - Wyjdź\n1 - Poprzednia\nStrona nr " + actual + "\nTwój wybór to: ");
            }
            if (actual > 1 && actual < pageCount) {
                decision = inputInteger("0 - Wyjdź\n1 - Poprzednia\n2 - Następna\nStrona nr " + actual + "\nTwój wybór to: ");
            }
            if (actual == 1 && pageCount == 1) {
                decision = inputInteger("0 - Wyjdź\nStrona nr " + actual + "\nTwój wybór to: ");
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
                cleanConsole();
                break;
            }
        } while (decision.get() != 0);
    }

    public void consolePrintEventScheme(Event e, String pattern) {
        Place p = e.getPlace();
        String eventTimeFormatted = null;
        Optional<String> opt = Optional.ofNullable(eventTimeFormatted);

        if (pattern.isBlank() || pattern.isEmpty()) {
            pattern = "yyyy-MM-dd HH:mm:ss";
            this.pattern = pattern;
        }

        do {
            try {
                eventTimeFormatted = configureDate(e.getEndDate(), pattern);
                opt = Optional.ofNullable(eventTimeFormatted);
            } catch (IllegalMonitorStateException exception) {
                cleanConsole();
                STDOUT.info("Niepoprawny format daty w pliku konfiguracyjnym, proszę popraw konfigurację i poczekaj na odświeżenie aplikacji. msg: {}\n", exception.getMessage());
            } catch (UnsupportedOperationException exception) {
                cleanConsole();
                STDOUT.info("Niepoprawny format daty w pliku konfiguracyjnym, proszę popraw konfigurację i poczekaj na odświeżenie aplikacji. msg: {}\n", exception.getMessage());
            } catch (IllegalArgumentException exception) {
                cleanConsole();
                STDOUT.info("Niepoprawny format daty w pliku konfiguracyjnym, proszę popraw konfigurację i poczekaj na odświeżenie aplikacji. msg: {}\n", exception.getMessage());
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
