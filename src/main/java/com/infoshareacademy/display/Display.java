package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.repository.EventRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class Display {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String DECISION_REQUEST = "\nTwój wybór to: ";
    Integer qty;
    Integer elemPerPage;
    boolean firstStart;

    public void displayComingEvents() {
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
            if (compQty.isPresent()) {
                qty = compQty.get();
            }
            if (pageMaxElements.isPresent()) {
                elemPerPage = pageMaxElements.get();
            }
        } while (qty <= 0 || elemPerPage <= 0);
        List<Event> eventList = selectedListOfComingEvents(qty);
        displayPages(qty, elemPerPage, eventList);
    }

    public void displayAllEvents() {
        cleanConsole();
        Optional<Integer> pageMaxElements;
        firstStart = true;
        do {
            if (!firstStart) {
                cleanConsole();
                STDOUT.info("Podano zerowe lub ujemne wartości parametrów, proszę wprowadzić je ponownie.\n");
            }
            firstStart = false;
            pageMaxElements = inputInteger("Ile wydarzeń chcesz zobaczyć na jednej stronie? ");
            if (pageMaxElements.isPresent()) {
                elemPerPage = pageMaxElements.get();
            }
        } while (elemPerPage <= 0);
        List<Event> eventList = listOfAllEvents();
        displayPages(eventList.size(), elemPerPage, eventList);
    }

    public void displaySearch() {
        cleanConsole();
        Optional<Integer> pageMaxElements;
        Optional<String> optQuery;
        String query, decision;
        List<Event> eventList;
        firstStart = true;
        do {
            optQuery = inputString("Wpisz wyszukiwaną frazę: ");
            if (optQuery.isPresent()) {
                query = optQuery.get();
                eventList = searchedByNameList(query);
                if (eventList.size() > 1) {
                    STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", eventList.size());
                    if (eventList.size() > 5) {
                        pageMaxElements = inputInteger("Ile wydarzeń chcesz zobaczyć na jednej stronie? ");
                    } else {
                        pageMaxElements = Optional.ofNullable(eventList.size());
                    }
                    if (pageMaxElements.isPresent()) {
                        elemPerPage = pageMaxElements.get();
                        displayPages(eventList.size(), elemPerPage, eventList);
                    }
                } else {
                    STDOUT.info("Nie znaleziono wydarzeń odpowiadających kryteriom.");
                }
            }
            decision = inputString("Chcesz kontynuować wyszukiwanie?[T/n]").get();
        } while (!(decision.equals("N")||decision.equals("n")));

    }

    private Optional<Integer> inputInteger(String subject) {
        Integer quantity = null;
        Optional<Integer> opt = null;
        String in;
        do {
            STDOUT.info("{}", subject);
            Scanner scanner = new Scanner(System.in);
            in = scanner.nextLine();
            if (NumberUtils.isDigits(in)) {
                quantity = Integer.parseInt(in);
            }
            opt = Optional.ofNullable(quantity);
            if (!NumberUtils.isDigits(in)) {
                cleanConsole();
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie!\n");
            }
        } while (opt.isEmpty());
        return opt;
    }

    private Optional<String> inputString(String subject) {
        String in;
        Optional<String> opt = null;
        do {
            STDOUT.info("{}", subject);
            Scanner scanner = new Scanner(System.in);
            in = scanner.nextLine();
            opt = Optional.ofNullable(in);
        } while (opt.isEmpty());
        return opt;
    }

    private List<Event> listOfAllEvents() {
        return EventRepository.getAllEvents();
    }

    private List<Event> selectedListOfComingEvents(int qty) {
        List<Event> eventList = new ArrayList<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (eventList.size() < qty && eventList.size() < EventRepository.getAllEvents().size() && isAfterNow(e.getEndDate())) {
                eventList.add(e);
            }
        }
        return eventList;
    }

    private List<Event> searchedByNameList(String query) {
        List<Event> eventList = new ArrayList<>();
        for (Event e : EventRepository.getAllEvents()) {
            if (e.getName().contains(query)) {
                eventList.add(e);
            }
        }
        return eventList;
    }

    private boolean isAfterNow(LocalDateTime eventTime) {
        return eventTime.isAfter(LocalDateTime.now());
    }

    private void displayPages(Integer qty, Integer elemPerPage, List<Event> eventList) {
        Optional<Integer> decision = null;
        double pageCountd = Math.ceil((double) qty / elemPerPage);
        Integer pageCount = (int) pageCountd;
        int limU = 0;
        int limD = elemPerPage;
        int actual = 1;
        do {
            cleanConsole();
            for (int i = limU; i < limD; i++) {
                if (i < eventList.size()) {
                    Event e = eventList.get(i);
                    consolePrintSingleEventScheme(e);
                }
            }
            decision = pageNavigatorDisplay(pageCount, actual, decision);
            int dec = 0;
            if (decision.isPresent()) {
                dec = decision.get();
            }
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

    private void consolePrintSingleEventScheme(Event e) {
        EventPrinter eventPrinter = new EventPrinter(ConsoleColor.BLUE_BACKGROUND, ConsoleColor.RED_BACKGROUND);

        eventPrinter.printID(e);
        eventPrinter.printName(e);
        eventPrinter.printStartDate(e);
        eventPrinter.printEndDate(e);
        eventPrinter.printShortDesc(e);
        eventPrinter.printLongDesc(e);
        eventPrinter.printActive(e);
        eventPrinter.printTickets(e);
        STDOUT.info("\n");
    }

    private Optional<Integer> pageNavigatorDisplay(int pageCount, int actual, Optional<Integer> decision) {
        if (actual == 1 && pageCount > 1) {
            decision = inputInteger("2 - Następna\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual == pageCount && actual != 1) {
            decision = inputInteger("1 - Poprzednia\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual > 1 && actual < pageCount) {
            decision = inputInteger("2 - Następna\n1 - Poprzednia\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual == 1 && pageCount == 1) {
            decision = inputInteger("0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        return decision;
    }
}