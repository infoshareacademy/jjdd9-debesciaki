package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Ticket;
import com.infoshareacademy.properties.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class EventPrinter {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private String colorPast;
    private String colorFuture;

    public EventPrinter() {
    }

    public EventPrinter(String colorFuture, String colorPast) {
        this.colorPast = colorPast;
        this.colorFuture = colorFuture;
    }

    public void printName(Event e) {
        String statusIndicator;
        if (e.getEndDate().minusHours(1).isAfter(LocalDateTime.now())) {
            statusIndicator = colorFuture;
        } else {
            statusIndicator = colorPast;
        }
        STDOUT.info("Nazwa: {}{}{}\n", statusIndicator, e.getName(), ConsoleColor.RESET);
    }

    public void printID(Event e) {
        STDOUT.info("ID: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getId(), ConsoleColor.RESET);
    }

    public void printShortDesc(Event e) {
        STDOUT.info("Krótki opis: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getDescShort(), ConsoleColor.RESET);
    }

    public void printLongDesc(Event e) {
        STDOUT.info("Długi opis: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getDescLong(), ConsoleColor.RESET);
    }

    public void printActive(Event e) {
        STDOUT.info("Aktywność: {}{}{}\n", ConsoleColor.RED_UNDERLINED, e.getActive(), ConsoleColor.RESET);
    }

    public void printTickets(Event e) {
        Ticket t = e.getTickets();
        STDOUT.info("Bilety: {}{}{}\n", ConsoleColor.RED_UNDERLINED, t.getType(), ConsoleColor.RESET);
    }

    public void printEndDate(Event e) {
        String date = dateAccordingToSetup(e.getEndDate());
        STDOUT.info("Data zakończenia: {}{}{}\n", ConsoleColor.BLUE_UNDERLINED, date, ConsoleColor.RESET);
    }

    public void printStartDate(Event e) {
        String date = dateAccordingToSetup(e.getStartDate());
        STDOUT.info("Data rozpoczęcia: {}{}{}\n", ConsoleColor.BLUE_UNDERLINED, date, ConsoleColor.RESET);
    }
    public void printOrganizer(Event e) {
        STDOUT.info("Organizator: {}{}{}\n", ConsoleColor.GREEN_BOLD, e.getOrganizer().getDesignation(), ConsoleColor.RESET);
    }

    public String dateAccordingToSetup(LocalDateTime evenTime) {
        String dateReformatted = null;
        Optional<String> optTime = Optional.ofNullable(dateReformatted);
        String pattern;
        do {
            pattern = PropertiesRepository.getInstance().getProperty("date-format");
            if (pattern.isBlank() || pattern.isEmpty()) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            try {
                dateReformatted = dateConfigurator(evenTime, pattern);
                optTime = Optional.ofNullable(dateReformatted);
            } catch (UnsupportedOperationException | IllegalArgumentException | DateTimeException exception) {
                promptError();
            }
        } while (optTime.isEmpty());
        return dateReformatted;
    }

    public String dateConfigurator(LocalDateTime eventTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return eventTime.format(formatter);
    }

    private void promptError() {
        cleanConsole();
        STDOUT.info("Niepoprawny format daty w pliku konfiguracyjnym, proszę popraw konfigurację. \n\n");
        STDOUT.info("Potwierdź poprawienie pliku konfiguracyjnego wciskając klawisz [Enter]\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        cleanConsole();
    }
}
