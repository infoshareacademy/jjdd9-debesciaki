package com.infoshareacademy.display;

import com.infoshareacademy.parser.Category;
import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Organizer;
import com.infoshareacademy.properties.PropertiesRepository;
import com.infoshareacademy.repository.CategoryRepository;
import com.infoshareacademy.repository.EventRepository;
import com.infoshareacademy.repository.OrganizerRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class DisplayEvents {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String DECISION_REQUEST = "\nTwój wybór to: ";
    private static final String ASK_FOR_PAGE_COUNT = "Ile wydarzeń chcesz zobaczyć na jednej stronie? ";
    private Integer qty;
    private Integer elemPerPage;
    private boolean firstStart;
    private List<Event> eventList;

    public DisplayEvents() {
        this.eventList = EventRepository.getAllEventsList();
    }

    public void resetList() {
        this.eventList = EventRepository.getAllEventsList();
    }

    public void displayComingEvents() {
        cleanConsole();
        resetList();
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
            pageMaxElements = inputInteger(ASK_FOR_PAGE_COUNT);
            qty = compQty.get();
            elemPerPage = pageMaxElements.get();
        } while (qty <= 0 || elemPerPage <= 0);
        this.eventList = selectedListOfComingEvents(qty);
        displayPages(qty, elemPerPage, this.eventList);
    }

    public void displayAllEvents() {
        cleanConsole();
        resetList();
        Optional<Integer> pageMaxElements;
        firstStart = true;
        do {
            if (!firstStart) {
                cleanConsole();
                STDOUT.info("Podano zerowe lub ujemne wartości parametrów, proszę wprowadzić je ponownie.\n");
            }
            firstStart = false;
            pageMaxElements = inputInteger(ASK_FOR_PAGE_COUNT);
            elemPerPage = pageMaxElements.get();
        } while (elemPerPage <= 0);
        displayPages(this.eventList.size(), elemPerPage, this.eventList);
    }

    public void displaySearchName() {
        displayQuery(true);
    }

    public void displaySearchOrganizer() {
        displayQuery(false);
    }

    private void displayQuery(boolean byNameOrOrganizer) {
        cleanConsole();
        Optional<Integer> pageMaxElements;
        Optional<String> optQuery;
        String query;
        String decision;
        firstStart = true;
        do {
            optQuery = inputString("Wpisz wyszukiwaną frazę: ");
            query = optQuery.get();

            if (byNameOrOrganizer) {
                this.eventList = searchListByName(query);
            } else {
                this.eventList = filterOrganizer(query);
            }
            if (this.eventList.size() > 1) {
                STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", this.eventList.size());
                if (this.eventList.size() > 5) {
                    pageMaxElements = inputInteger(ASK_FOR_PAGE_COUNT);
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
            decision = inputString("Chcesz spróbować ponownie?[tak]").get().toLowerCase();
        } while (decision.equals("tak"));
    }

    public void displayAfter() {
        do {
            cleanConsole();
            LocalDateTime minStartDate = localDateTimeRequest("Od kiedy najwcześniej mają się rozpocząć wydarzenia?");
            this.eventList = filterAfter(minStartDate);
        } while (!searchingResultDisplay(true));
    }

    public void displayBefore() {
        do {
            cleanConsole();
            LocalDateTime maxEndDate = localDateTimeRequest("Do kiedy najpóźniej mają się zakończyć wydarzenia?");
            this.eventList = filterBefore(maxEndDate);
        } while (!searchingResultDisplay(true));
    }

    public void displayPeriodically() {
        do {
            cleanConsole();
            LocalDateTime minStartDate = localDateTimeRequest("Od kiedy najwcześniej mają się rozpocząć wydarzenia?");
            LocalDateTime maxEndDate = localDateTimeRequest("Do kiedy najpóźniej mają się zakończyć wydarzenia?");
            this.eventList = filterPeriodically(minStartDate, maxEndDate);
        } while (!searchingResultDisplay(true));
    }

    public void displayCategorized() {
        do {
            ChooseCategory chooseCategory = new ChooseCategory();
            int choice = chooseCategory.displayGenres();
            if (choice != 0) {
                this.eventList = filterCategory(choice);
            }
        } while (!searchingResultDisplay(false));
    }

    public void displayOrganizers() {
        cleanConsole();
        Optional<Integer> pageMaxElements;
        AtomicInteger lp = new AtomicInteger(1);

        Map<Integer, Integer> organizersCoord = eventCountByOrganizer();

        Map<Integer, Integer> organizersDisplayTable = organizersCoord
                .entrySet()
                .stream()
                .collect(Collectors.toMap(k -> lp.getAndIncrement(),
                        Map.Entry::getKey));

        Map<Integer, Organizer> organizerMap = OrganizerRepository.getAllOrganizersMap();
        int x = 1;
        for (Map.Entry<Integer, Integer> entry : organizersCoord.entrySet()) {
            STDOUT.info("{} - {} ({})\n", x, organizerMap.get(entry.getKey()).getDesignation(), entry.getValue());
            x++;
        }
        STDOUT.info("0 - Wyjdź\n");

        Optional<Integer> in;
        Set<Integer> toDisplaySet = new HashSet<>();
        do {
            in = inputInteger("Wprowadź interesujacego Cię organizatora, jeśli chcesz zakończyć wprowadzanie organizatorów wprowadź 0: ", 0, organizersCoord.size(), false);
            if (in.isPresent() && in.get() != 0) toDisplaySet.add(organizersDisplayTable.get(in.get()));
        } while (!in.isPresent() || in.get() != 0);

        List<Event> temporaryList = new ArrayList<>();
        for (Integer i : toDisplaySet) {
            for (Event e : filterByOrganizer(i, this.eventList)) {
                temporaryList.add(e);
            }
        }
        this.eventList = temporaryList;

        if (this.eventList.size() > 1) {
            if (this.eventList.size() > 5) {
                pageMaxElements = inputInteger(ASK_FOR_PAGE_COUNT);
            } else {
                pageMaxElements = Optional.ofNullable(eventList.size());
            }
            if (pageMaxElements.isPresent()) {
                elemPerPage = pageMaxElements.get();
                displayPages(this.eventList.size(), elemPerPage, this.eventList);
            }
        }
    }

    public List<Event> filterByOrganizer(int id, List<Event> source) {
        return source.stream()
                .filter(e -> e.getOrganizer().getId() == id)
                .collect(Collectors.toList());
    }

    public Map<Integer, Integer> eventCountByOrganizer() {
        Map<Integer, Integer> out = OrganizerRepository.getAllOrganizers()
                .stream()
                .collect(Collectors.toMap(Organizer::getId,
                        v -> 0));
        resetList();
        this.eventList
                .forEach(e -> {
                    int p = e.getOrganizer().getId();
                    out.put(p, out.get(p) + 1);
                });
        return out.entrySet().stream()
                .filter(m -> m.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));

    }

    public Optional<Integer> inputInteger(String subject) {
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

    private Optional<Integer> inputInteger(String subject, int limitU, int limitD, boolean cleanWhenError) {
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
            if (!NumberUtils.isDigits(in) || (opt.isPresent() && (opt.get() > limitD || opt.get() < limitU))) {
                if (cleanWhenError) {
                    cleanConsole();
                }
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie!\n");
            }
        } while (opt.isEmpty() || (opt.get() > limitD || opt.get() < limitU));
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

    public List<Event> selectedListOfComingEvents(int qty) {
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            if (out.size() < qty && out.size() < EventRepository.getAllEventsList().size() && isAfterNow(e.getEndDate())) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> searchListByName(String query) {
        List<Event> out = new ArrayList<>();
        for (Event e : this.eventList) {
            if (e.getName().toLowerCase().contains(query.toLowerCase())) {
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
            LocalDateTime d = e.getStartDate();
            LocalDateTime b = e.getEndDate();

            if (d.isAfter(afterTimePoint) && b.isBefore(beforeTimePoint)) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> filterCategory(Integer key) {
        List<Event> out = new ArrayList<>();
        Map<Integer, Integer> relationalMap = CategoryRepository.getCategoriesRelationalMap();
        int id = relationalMap.get(key);
        for (Event e : this.eventList) {
            if (e.getCategoryId() == id) {
                out.add(e);
            }
        }
        this.eventList = out;
        return this.eventList;
    }

    private List<Event> filterOrganizer(String query) {
        Predicate<Event> byOrganizer = event -> event.getOrganizer().getDesignation().toLowerCase().contains(query.toLowerCase());
        return this.eventList.stream().parallel().filter(byOrganizer).collect(Collectors.toList());
    }

    private boolean isAfterNow(LocalDateTime eventTime) {
        return eventTime.isAfter(LocalDateTime.now());
    }

    private LocalDateTime localDateTimeRequest(String subject) {
        LocalDateTime out = null;
        String in;
        Optional<LocalDateTime> optionalLocalDateTime = Optional.ofNullable(null);
        String patternStr = "yyyy/MM/dd HH:mm";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patternStr);
        do {
            cleanConsole();
            Scanner scanner = new Scanner(System.in);
            STDOUT.info("Wprowadź datę {} {}: ", patternStr, subject);
            in = scanner.nextLine();
            try {
                DateUtils.parseDate(in, patternStr);
            } catch (ParseException e) {
                promptError("Źle wprowadzona data!");
                continue;
            }
            Pattern p = Pattern.compile("^[1-2][0-9]{3}/[0-1][0-9]/[0-3][0-9] [0-2][0-9]:[0-5][0-9]$");
            Matcher matcher = p.matcher(in);
            boolean matches = matcher.matches();
            if (matches) {
                out = LocalDateTime.parse(in, dtf);
                optionalLocalDateTime = Optional.ofNullable(out);
            } else {
                promptError("Źle wprowadzona data!");
            }
        } while (optionalLocalDateTime.isEmpty() || in.isEmpty() || in.isBlank());
        return out;
    }

    public void displayPages(Integer qty, Integer elemPerPage, List<Event> eventList) {
        if (PropertiesRepository.getInstance().getProperty("sort-by").equalsIgnoreCase("name")) {
            if (PropertiesRepository.getInstance().getProperty("sort-order").equalsIgnoreCase("desc")) {
                Collections.sort(eventList, EventComparators.EventNameComparatorDesc);
            } else {
                Collections.sort(eventList, EventComparators.EventNameComparatorAsc);
            }
        } else if (PropertiesRepository.getInstance().getProperty("sort-by").equalsIgnoreCase("organizer")) {
            if (PropertiesRepository.getInstance().getProperty("sort-order").equalsIgnoreCase("desc")) {
                Collections.sort(eventList, EventComparators.EventOrganizerComparatorDesc);
            } else {
                Collections.sort(eventList, EventComparators.EventOrganizerComparatorAsc);
            }
        } else {
            if (PropertiesRepository.getInstance().getProperty("sort-order").equalsIgnoreCase("desc")) {
                Collections.sort(eventList, EventComparators.EventEndDateComparatorDesc);
            } else {
                Collections.sort(eventList, EventComparators.EventEndDateComparatorAsc);
            }
        }

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
            decision = pageNavigatorDisplay(pageCount, actual);
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

    public void consolePrintSingleEventScheme(Event e) {
        int eventNumber = e.getId();
        STDOUT.info("Numer wydarzenia: {}{}{}\n", ConsoleColor.BLUE_UNDERLINED, eventNumber, ConsoleColor.RESET);
        EventPrinter eventPrinter = new EventPrinter(ConsoleColor.GREEN_BOLD, ConsoleColor.RED_BOLD);
        eventPrinter.printName(e);
        eventPrinter.printOrganizer(e);
        eventPrinter.printStartDate(e);
        eventPrinter.printEndDate(e);
        STDOUT.info("\n");
    }

    private boolean searchingResultDisplay(boolean repeatOption) {
        Optional<Integer> pageMaxElements;
        String decision = "x";
        if (this.eventList.size() > 1) {
            cleanConsole();
            STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", this.eventList.size());
            if (this.eventList.size() > 5) {
                pageMaxElements = inputInteger(ASK_FOR_PAGE_COUNT);
            } else {
                pageMaxElements = Optional.ofNullable(eventList.size());
            }
            if (pageMaxElements.isPresent()) {
                elemPerPage = pageMaxElements.get();
                displayPages(this.eventList.size(), elemPerPage, this.eventList);
            }
        } else {
            promptError("Nie znaleziono wydarzeń spełniających kryteria.");
            if (repeatOption) {
                decision = inputString("Chcesz spróbować ponownie, wpisz [tak]?").get().toLowerCase();
                if (decision.equals("tak")) {
                    this.eventList = EventRepository.getAllEventsList();
                }
            }
        }
        return (!decision.equals("tak"));
    }

    public Optional<Integer> pageNavigatorDisplay(int pageCount, int actual) {
        if (actual == 1 && pageCount > 1) {
            return inputInteger("2 - Następna\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual == pageCount && actual != 1) {
            return inputInteger("1 - Poprzednia\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual > 1 && actual < pageCount) {
            return inputInteger("2 - Następna\n1 - Poprzednia\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual == 1 && pageCount == 1) {
            return inputInteger("0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        STDOUT.info("ERROR ERROR\n");
        return Optional.ofNullable(0);
    }

    private void promptError(String msg) {
        cleanConsole();
        STDOUT.info("{}\n\n", msg);
        STDOUT.info("Potwierdź błąd wciskając klawisz [Enter]\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        cleanConsole();
    }
}