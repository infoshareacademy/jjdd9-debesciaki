package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.repository.EventRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
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
    List<Event> eventList;

    public Display() {
        this.eventList = listOfAllEvents();
    }

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
        this.eventList = selectedListOfComingEvents(qty);
        displayPages(qty, elemPerPage, eventList);
    }

    public void displayAllEvents() {
        cleanConsole();
        this.eventList = listOfAllEvents();
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
        displayPages(this.eventList.size(), elemPerPage, this.eventList);
    }

    public void displaySearch() {
        cleanConsole();
        Optional<Integer> pageMaxElements;
        Optional<String> optQuery;
        String query, decision;
        firstStart = true;
        do {
            optQuery = inputString("Wpisz wyszukiwaną frazę: ");
            if (optQuery.isPresent()) {
                query = optQuery.get();
                this.eventList = searchListByName(query);
                if (this.eventList.size() > 1) {
                    STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", this.eventList.size());
                    if (this.eventList.size() > 5) {
                        pageMaxElements = inputInteger("Ile wydarzeń chcesz zobaczyć na jednej stronie? ");
                    } else {
                        pageMaxElements = Optional.ofNullable(eventList.size());
                    }
                    if (pageMaxElements.isPresent()) {
                        elemPerPage = pageMaxElements.get();
                        displayPages(this.eventList.size(), elemPerPage, this.eventList);
                    }
                } else {
                    STDOUT.info("Nie znaleziono wydarzeń odpowiadających kryteriom.");
                }
            }
            decision = inputString("Chcesz kontynuować wyszukiwanie?[!n/n]").get();
        } while (!(decision.equals("N") || decision.equals("n")));

    }

    public void displayBefore() {
    }

    public void displayAfter() {
        cleanConsole();
        LocalDateTime minStartDate = localDateTimeRequest("Od kiedy najwcześniej mają się odbywać wydarzenia?\n");
        System.out.println(minStartDate.toString());
        this.eventList = filterAfter(minStartDate);
        Optional<Integer> pageMaxElements;
        String decision;
        do {
            if (this.eventList.size() > 1) {
                STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", this.eventList.size());
                if (this.eventList.size() > 5) {
                    pageMaxElements = inputInteger("Ile wydarzeń chcesz zobaczyć na jednej stronie? ");
                } else {
                    pageMaxElements = Optional.ofNullable(eventList.size());
                }
                if (pageMaxElements.isPresent()) {
                    elemPerPage = pageMaxElements.get();
                    displayPages(this.eventList.size(), elemPerPage, this.eventList);
                }
            } else {
                STDOUT.info("Nie znaleziono wydarzeń odpowiadających kryteriom.");
            }
            decision = inputString("Chcesz kontynuować wyszukiwanie?[!n/n]").get();
        } while (!(decision.equals("N") || decision.equals("n")));

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
        for (Event e : this.eventList) {
            if (this.eventList.size() < qty && this.eventList.size() < EventRepository.getAllEvents().size() && isAfterNow(e.getEndDate())) {
                this.eventList.add(e);
            }
        }
        return this.eventList;
    }

    private List<Event> searchListByName(String query) {
        for (Event e : this.eventList) {
            if (e.getName().contains(query)) {
                this.eventList.add(e);
            }
        }
        return this.eventList;
    }

    private List<Event> filterBefore(LocalDateTime beforeTimePoint) {
        for (Event e : this.eventList) {
            if (e.getEndDate().isBefore(beforeTimePoint)) {
                this.eventList.add(e);
            }
        }
        return this.eventList;
    }

    private List<Event> filterAfter(LocalDateTime afterTimePoint) {
        for (Event e : this.eventList) {
            if (e.getStartDate().isAfter(afterTimePoint)) {
                this.eventList.add(e);
            }
        }
        return this.eventList;
    }

    private boolean isAfterNow(LocalDateTime eventTime) {
        return eventTime.isAfter(LocalDateTime.now());
    }

    private LocalDateTime localDateTimeRequest(String subject) {
        LocalDateTime out = null;
        Optional<LocalDateTime> optionalLocalDateTime = null;

        return out;
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