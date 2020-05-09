package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Ticket;
import com.infoshareacademy.properties.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
        Optional<String> name = Optional.ofNullable(e.getName());
        String out = "Brak informacji";
        if (!name.isEmpty() && name.isPresent()) {
            out = name.get();
        }
        String statusIndicator;
        if (e.getStartDate().minusDays(2).isBefore(LocalDateTime.now())) {
            statusIndicator = ConsoleColor.RED;
        } else if (e.getStartDate().minusDays(7).isBefore(LocalDateTime.now())) {
            statusIndicator = ConsoleColor.YELLOW;
        } else {
            statusIndicator = colorFuture;
        }
        STDOUT.info("Nazwa: {}{}{}\n", statusIndicator, out, ConsoleColor.RESET);
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
        STDOUT.info("Data zakończenia: {}{}{}\n", ConsoleColor.BLUE, date, ConsoleColor.RESET);
    }

    public void printStartDate(Event e) {
        String date = dateAccordingToSetup(e.getStartDate());
        STDOUT.info("Data rozpoczęcia: {}{}{}\n", ConsoleColor.BLUE, date, ConsoleColor.RESET);
    }

    public void printOrganizer(Event e) {
        STDOUT.info("Organizator: {}{}{}\n", ConsoleColor.GREEN, e.getOrganizer().getDesignation(), ConsoleColor.RESET);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.forLanguageTag("pl-PL"));
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

    public void printPlace(Event e) {
        STDOUT.info("Miasto: {}{}{}\n", ConsoleColor.GREEN_BOLD, e.getPlace().getAddress().getCity(), ConsoleColor.RESET);
        STDOUT.info("Kod pocztowy: {}{}{}\n", ConsoleColor.GREEN_BOLD, e.getPlace().getAddress().getZipcode(), ConsoleColor.RESET);
        STDOUT.info("Ulica: {}{}{}\n", ConsoleColor.GREEN_BOLD, e.getPlace().getAddress().getStreet(), ConsoleColor.RESET);
        STDOUT.info("Długość geograficzna: {}{}{}\n", ConsoleColor.GREEN_BOLD, e.getPlace().getAddress().getLng(), ConsoleColor.RESET);
        STDOUT.info("Szerokość geograficzna: {}{}{}\n", ConsoleColor.GREEN_BOLD, e.getPlace().getAddress().getLng(), ConsoleColor.RESET);
    }

    public void printTicket(Event e) {
        if (e.getTickets() != null) {
            if (e.getTickets().getType() != null && !e.getTickets().getType().equals("unknown")) {
                if (e.getTickets().getType().equalsIgnoreCase("tickets")) {
                    STDOUT.info("Typ: {}bilety{}\n", ConsoleColor.GREEN_BOLD, ConsoleColor.RESET);
                }
                if (e.getTickets().getType().equalsIgnoreCase("free")) {
                    STDOUT.info("Typ: {}darmowe{}\n", ConsoleColor.GREEN_BOLD, ConsoleColor.RESET);
                }
            }
            if (e.getTickets().getStartTicket() != null && e.getTickets().getEndTicket() != null && !e.getTickets().getType().equalsIgnoreCase("free")) {
                STDOUT.info("Cena: {}{} - {}{}\n", ConsoleColor.GREEN_BOLD, e.getTickets().getStartTicket(), e.getTickets().getEndTicket(), ConsoleColor.RESET);
            }
        }
    }
}
