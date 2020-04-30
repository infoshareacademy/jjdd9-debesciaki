package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.properties.PropertiesRepository;
import com.infoshareacademy.repository.EventRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class DisplayEvents {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String DECISION_REQUEST = "\nTwój wybór to: ";
    Integer qty;
    Integer elemPerPage;
    boolean firstStart;
    List<Event> eventList;

    public DisplayEvents() {
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

    public void displayAfter() {
        cleanConsole();
        LocalDateTime minStartDate = localDateTimeRequest("Od kiedy najwcześniej mają się rozpocząć wydarzenia?");
        this.eventList = filterAfter(minStartDate);
        searchingResultDisplay();
    }

    public void displayBefore() {
        cleanConsole();
        LocalDateTime maxEndDate = localDateTimeRequest("Do kiedy najpóźniej mają się zakończyć wydarzenia?");
        this.eventList = filterBefore(maxEndDate);
        searchingResultDisplay();
    }

    public void displayPeriodically() {
        do {
            cleanConsole();
            LocalDateTime minStartDate = localDateTimeRequest("Od kiedy najwcześniej mają się rozpocząć wydarzenia?");
            LocalDateTime maxEndDate = localDateTimeRequest("Do kiedy najpóźniej mają się zakończyć wydarzenia?");
            this.eventList = filterPeriodically(minStartDate, maxEndDate);
        }while(!searchingResultDisplay());
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
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            if (this.eventList.size() < qty && this.eventList.size() < EventRepository.getAllEvents().size() && isAfterNow(e.getEndDate())) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> searchListByName(String query) {
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            if (e.getName().contains(query)) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> filterBefore(LocalDateTime beforeTimePoint) {
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            if (e.getEndDate().isBefore(beforeTimePoint)) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> filterAfter(LocalDateTime afterTimePoint) {
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            if (e.getStartDate().isAfter(afterTimePoint)) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> filterPeriodically(LocalDateTime afterTimePoint, LocalDateTime beforeTimePoint) {
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            LocalDateTime d=e.getStartDate();
            LocalDateTime b=e.getEndDate();

            if (d.isAfter(afterTimePoint)&&b.isBefore(beforeTimePoint)) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private boolean isAfterNow(LocalDateTime eventTime) {
        return eventTime.isAfter(LocalDateTime.now());
    }

    private LocalDateTime localDateTimeRequest(String subject) {
        LocalDateTime out = null;
        boolean matches = false;
        Optional<LocalDateTime> optionalLocalDateTime = Optional.ofNullable(null);
        String in = null;
        Pattern p = Pattern.compile("^[1-2][0-9]{3}-[0-1][0-9]-[0-3][0-9] [0-2][0-9]:[0-5][0-9]$");
        String patternStr = "yyyy-MM-dd HH:mm";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patternStr);
        do {
            Scanner scanner = new Scanner(System.in);
            STDOUT.info("Wprowadź datę {} {}: ", patternStr, subject);
            in = scanner.nextLine();
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = p.matcher(in);
            matches = matcher.matches();
            if (!matches || in.isEmpty() || in.isBlank() || Integer.parseInt(in.substring(5, 7)) > 12) {
                promptError("Źle wprowadzona data!");
            }
            try {
                optionalLocalDateTime = Optional.ofNullable(out = LocalDateTime.parse(in, dtf));
            } catch (DateTimeParseException e) {
                promptError("Rok przestępny/źle wprowadzony miesiąc/dzień");
            }
        } while (!matches || optionalLocalDateTime.isEmpty() || in.isEmpty() || in.isBlank() || Integer.parseInt(in.substring(5, 7)) > 12);
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

        //eventPrinter.printID(e);
        eventPrinter.printName(e);
        eventPrinter.printStartDate(e);
        eventPrinter.printEndDate(e);
        //eventPrinter.printShortDesc(e);
        //eventPrinter.printLongDesc(e);
        //eventPrinter.printActive(e);
        //eventPrinter.printTickets(e);
        STDOUT.info("\n");
    }

    private boolean searchingResultDisplay(){
        Optional<Integer> pageMaxElements;
        String decision;
        do {
            if (this.eventList.size() > 1) {
                cleanConsole();
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
        if (this.eventList.size()>0){
            return true;
        }else{
         return false;
        }
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

    private void promptError(String msg) {
        cleanConsole();
        STDOUT.info("{}\n", msg);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        cleanConsole();
    }
}