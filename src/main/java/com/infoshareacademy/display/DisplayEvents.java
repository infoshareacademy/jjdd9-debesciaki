package com.infoshareacademy.display;

import com.infoshareacademy.parser.Category;
import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Organizer;
import com.infoshareacademy.properties.PropertiesRepository;
import com.infoshareacademy.repository.CategoryRepository;
import com.infoshareacademy.repository.EventRepository;
import com.infoshareacademy.repository.JSONFileChanger;
import com.infoshareacademy.repository.OrganizerRepository;
import com.infoshareacademy.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class DisplayEvents {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String DECISION_REQUEST = "\nTwój wybór to: ";
    private static final String ASK_FOR_PAGE_COUNT = "Ile wydarzeń chcesz zobaczyć na jednej stronie? ";
    private static final String SPACING_MOD_SUBMENU = "                                 ";
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
        Validator v = new Validator();
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
            compQty = v.inputInteger("Ile nadchodzących wydarzeń chcesz zobaczyć łącznie? ");
            pageMaxElements = v.inputInteger(ASK_FOR_PAGE_COUNT);
            qty = compQty.get();
            elemPerPage = pageMaxElements.get();
        } while (qty <= 0 || elemPerPage <= 0);
        this.eventList = selectedListOfComingEvents(qty);
        displayPages(qty, elemPerPage, this.eventList);
    }

    public void displayAllEvents() {
        Validator v = new Validator();
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
            pageMaxElements = v.inputInteger(ASK_FOR_PAGE_COUNT);
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
        Validator v = new Validator();
        cleanConsole();
        Optional<Integer> pageMaxElements;
        Optional<String> optQuery;
        String query;
        String decision;
        firstStart = true;
        do {
            optQuery = v.inputString("Wpisz wyszukiwaną frazę: ");
            query = optQuery.get();

            if (byNameOrOrganizer) {
                this.eventList = searchListByName(query);
            } else {
                this.eventList = filterOrganizer(query);
            }
            if (this.eventList.size() > 1) {
                STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", this.eventList.size());
                if (this.eventList.size() > 5) {
                    pageMaxElements = v.inputInteger(ASK_FOR_PAGE_COUNT);
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
            decision = v.inputString("Chcesz spróbować ponownie?[tak]").get().toLowerCase();
        } while (decision.equals("tak"));
    }

    public void displayAfter() {
        Validator v = new Validator();
        do {
            cleanConsole();
            LocalDateTime minStartDate = v.localDateTimeRequest("Od kiedy najwcześniej mają się rozpocząć wydarzenia?");
            this.eventList = filterAfter(minStartDate);
        } while (!searchingResultDisplay(true));
    }

    public void displayBefore() {
        Validator v = new Validator();
        do {
            cleanConsole();
            LocalDateTime maxEndDate = v.localDateTimeRequest("Do kiedy najpóźniej mają się zakończyć wydarzenia?");
            this.eventList = filterBefore(maxEndDate);
        } while (!searchingResultDisplay(true));
    }

    public void displayPeriodically() {
        Validator v = new Validator();
        do {
            cleanConsole();
            LocalDateTime maxEndDate;
            LocalDateTime minStartDate = v.localDateTimeRequest("Od kiedy najwcześniej mają się rozpocząć wydarzenia?");
            do {
                maxEndDate = v.localDateTimeRequest("Do kiedy najpóźniej mają się zakończyć wydarzenia?");
            } while (maxEndDate.isBefore(minStartDate));
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
        Validator v = new Validator();
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
            in = v.inputInteger("Wprowadź interesujacego Cię organizatora, jeśli chcesz zakończyć wprowadzanie organizatorów wprowadź 0: ", 0, organizersCoord.size(), false);
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
                pageMaxElements = v.inputInteger(ASK_FOR_PAGE_COUNT);
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

    protected List<Event> selectedListOfComingEvents(int qty) {
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

    private void displayPages(Integer qty, Integer elemPerPage, List<Event> eventList) {
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
        IdCoordinator coordinator = new IdCoordinator();
        Map<Integer, Integer> eventsDisplayTable = coordinator.coordinatingEventResToRealID(eventList);

        Optional<Integer> decision = null;
        double pageCountd;
        if (qty <= eventList.size()) {
            pageCountd = Math.ceil((double) qty / elemPerPage);
        } else {
            pageCountd = Math.ceil((double) eventList.size() / elemPerPage);
        }

        Integer pageCount = (int) pageCountd;
        int limU = 0;
        int limD = elemPerPage;
        int actual = 1;
        do {
            cleanConsole();
            for (int i = limU; i < limD; i++) {
                if (i < eventList.size()) {
                    Event e = eventList.get(i);
                    consolePrintSingleEventScheme(e, i + 1);
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
            } else if (dec == 3) {
                ModificationMenu(eventsDisplayTable, limU, limD);
                resetList();
                break;
            } else if (dec == 0) {
                break;
            }
        } while (decision.get() != 0);
    }

    public void ModificationMenu(Map<Integer, Integer> eventsDisplayTable, int limU, int limD) {
        Validator v = new Validator();
        Optional<Integer> decision = null;
        STDOUT.info("{}1 - Usuń wydarzenie\n", SPACING_MOD_SUBMENU);
        STDOUT.info("{}2 - Modyfikuj wydarzenie\n", SPACING_MOD_SUBMENU);
        STDOUT.info("{}3 - Dodaj wydarzenie\n", SPACING_MOD_SUBMENU);
        STDOUT.info("{}0 - Wyjdź\n", SPACING_MOD_SUBMENU);
        decision = v.inputInteger(DECISION_REQUEST, 0, 3, false);
        switch (decision.get()) {
            case 1: {
                int removalID = v.inputInteger(SPACING_MOD_SUBMENU + "Wprowadź Lp. wydarzenia, które chcesz usunąć: ", limU + 1, limD + 1, false).get();
                removeByIDinRep(eventsDisplayTable.get(removalID));
                STDOUT.info("{}Usunięto wydarzenie!{}\n", ConsoleColor.YELLOW_BACKGROUND, ConsoleColor.RESET);
                break;
            }
            case 2: {
                int editID = v.inputInteger(SPACING_MOD_SUBMENU + "Wprowadź Lp. wydarzenia, które chcesz edytować: ", limU + 1, limD + 1, false).get();
                editByIDinRep(eventsDisplayTable.get(editID));
                STDOUT.info("{}Zmodyfikowano wydarzenie!{}\n", ConsoleColor.BLUE_BACKGROUND, ConsoleColor.RESET);
                break;
            }
            case 3: {
                new AddEvent();
                STDOUT.info("{}Dodano wydarzenie!{}\n", ConsoleColor.GREEN_BACKGROUND, ConsoleColor.RESET);
                break;
            }
            case 0: {
                break;
            }
        }
    }

    public void removeByIDinRep(int id) {
        new JSONFileChanger().removeEvent(id);
    }

    public void editByIDinRep(int id) {
        Validator v = new Validator();
        EventPrinter eventPrinter = new EventPrinter();
        Optional<Integer> dec = null;
        Event editedEvent = EventRepository.getAllEventsMap().get(id);
        removeByIDinRep(id);
        EditEvent editEntity = new EditEvent();
        do {
            cleanConsole();
            editEntity.consolePrintEditOptions();
            dec = v.inputInteger(DECISION_REQUEST, 0, 9, false);
            switch (dec.get()) {
                case 1: {
                    eventPrinter.printName(editedEvent);
                    editEntity.editName(editedEvent);
                    break;
                }
                case 2: {
                    eventPrinter.printStartDate(editedEvent);
                    editEntity.editStartDate(editedEvent);
                    break;
                }
                case 3: {
                    eventPrinter.printEndDate(editedEvent);
                    editEntity.editEndDate(editedEvent);
                    break;
                }
                case 4: {
                    eventPrinter.printPlace(editedEvent);
                    editEntity.editPlace(editedEvent);
                    break;
                }
                case 5: {
                    eventPrinter.printOrganizer(editedEvent);
                    editEntity.editOrganizer(editedEvent);
                    break;
                }
                case 6: {
                    eventPrinter.printShortDesc(editedEvent);
                    editEntity.editShortDesc(editedEvent);
                    break;
                }
                case 7: {
                    eventPrinter.printLongDesc(editedEvent);
                    editEntity.editLongDesc(editedEvent);
                    break;
                }
                case 8: {
                    editEntity.editUrl(editedEvent);
                    break;
                }
                case 9: {
                    editEntity.editTicket(editedEvent);
                    break;
                }
                case 0: {
                    new JSONFileChanger().addEvent(editedEvent);
                    cleanConsole();
                    break;
                }
            }
        } while (dec.get() != 0);

    }

    public void consolePrintSingleEventScheme(Event e) {
        EventPrinter eventPrinter = new EventPrinter(ConsoleColor.BLUE_BACKGROUND, ConsoleColor.RED_BACKGROUND);
        eventPrinter.printName(e);
        eventPrinter.printOrganizer(e);
        eventPrinter.printStartDate(e);
        eventPrinter.printEndDate(e);
        eventPrinter.printTicket(e);
        STDOUT.info("\n");
    }

    public void consolePrintSingleEventScheme(Event e, int tempID) {
        EventPrinter eventPrinter = new EventPrinter(ConsoleColor.BLUE, ConsoleColor.RED);
        STDOUT.info("Lp. : {}\n", tempID);
        eventPrinter.printName(e);
        eventPrinter.printOrganizer(e);
        eventPrinter.printStartDate(e);
        eventPrinter.printEndDate(e);
        eventPrinter.printTicket(e);
        STDOUT.info("\n");
    }

    private boolean searchingResultDisplay(boolean repeatOption) {
        Validator v = new Validator();
        Optional<Integer> pageMaxElements;
        String decision = "x";
        if (this.eventList.size() > 1) {
            cleanConsole();
            STDOUT.info("Znaleziono {} wydarzeń odpowiadających kryteriom.\n", this.eventList.size());
            if (this.eventList.size() > 5) {
                pageMaxElements = v.inputInteger(ASK_FOR_PAGE_COUNT);
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
                decision = v.inputString("Chcesz spróbować ponownie, wpisz [tak]?").get().toLowerCase();
                if (decision.equals("tak")) {
                    this.eventList = EventRepository.getAllEventsList();
                }
            }
        }
        return (!decision.equals("tak"));
    }

    public Optional<Integer> pageNavigatorDisplay(int pageCount, int actual) {
        Validator v = new Validator();
        if (actual == 1 && pageCount > 1) {
            return v.inputInteger("2 - Następna\n3 - Modyfikuj listę\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual == pageCount && actual != 1) {
            return v.inputInteger("1 - Poprzednia\n3 - Modyfikuj listę\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual > 1 && actual < pageCount) {
            return v.inputInteger("1 - Poprzednia\n2 - Następna\n3 - Modyfikuj listę\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
        }
        if (actual == 1 && pageCount == 1) {
            return v.inputInteger("3 - Modyfikuj listę\n0 - Wyjdź\nStrona nr " + actual + " z " + pageCount + DECISION_REQUEST);
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