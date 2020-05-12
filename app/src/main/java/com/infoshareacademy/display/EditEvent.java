package com.infoshareacademy.display;

import com.infoshareacademy.parser.*;
import com.infoshareacademy.repository.JSONFileChanger;
import com.infoshareacademy.repository.UniqueIDprovider;
import com.infoshareacademy.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class EditEvent {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String COLOR = ConsoleColor.WHITE_UNDERLINED;

    EditEvent() {

    }

    void consolePrintEditOptions() {
        STDOUT.info("{}1 - Nazwa{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}2 - Data startu{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}3 - Data zakończenia{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}4 - Miejsce{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}5 - Organizator{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}6 - Opis krótki{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}7 - Opis długi{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}8 - Adres www{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}9 - Bilety{}\n", COLOR, ConsoleColor.RESET);
        STDOUT.info("{}0 - Wyjdź{}\n", COLOR, ConsoleColor.RESET);
    }

    void addId(Event e) {
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        e.setId(uniqueIDprovider.getEventID());
    }

    void editName(Event e) {
        Validator v = new Validator();
        e.setName(v.inputString("Wprowadź nazwę wydarzenia: ").get());
    }

    void editOrganizer(Event e) {
        JSONFileChanger writer = new JSONFileChanger();
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        Validator v = new Validator();
        Organizer o = new Organizer();
        o.setDesignation(v.inputString("Wprowadź nazwę organizatora: ").get());
        o.setId(uniqueIDprovider.getOrganizerID());
        writer.addOrganizer(o);
        e.setOrganizer(o);
    }

    void editShortDesc(Event e) {
        Validator v = new Validator();
        e.setDescShort(v.inputString("Wprowadź krótki opis: ").get());
    }

    void editLongDesc(Event e) {
        Validator v = new Validator();
        e.setDescLong(v.inputString("Wprowadź długi opis: ").get());
    }

    void editStartDate(Event e) {
        Validator v = new Validator();
        e.setStartDate(v.localDateTimeRequest("Data rozpoczęcia wydarzenia.", false));
    }

    void editEndDate(Event e) {
        Validator v = new Validator();
        LocalDateTime temp;
        do {
            temp = v.localDateTimeRequest("Data zakończenia wydarzenia.", false);
        } while (temp.isBefore(e.getStartDate()));
        e.setEndDate(temp);
    }

    void editPlace(Event e) {
        JSONFileChanger writer = new JSONFileChanger();
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        Validator v = new Validator();
        Place p = new Place();
        Address a = new Address();
        p.setId(uniqueIDprovider.getPlaceID());
        p.setName(v.inputString("Wprowadź nazwę miejsca: ").get());
        p.setSubname(v.inputString("Wprowadź nazwę dodatkową miejsca: ").get());
        a.setCity(v.inputString("Wprowadź miasto: ").get());
        a.setZipcode(v.inputString("Wprowadź kod pocztowy: ", Pattern.compile("^[0-9]{2}-[0-9]{3}$")).get());
        a.setStreet(v.inputString("Wprowadź ulicę: ").get());
        p.setAddress(a);
        writer.addPlace(p);
        e.setPlace(p);
    }

    void editUrl(Event e) {
        Validator v = new Validator();
        Url urls = new Url();
        urls.setFb(v.inputString("Wprowadź adres facebook: ").get());
        urls.setWww(v.inputString("Wprowadź adres www: ").get());
        urls.setTickets(v.inputString("Wprowadź adres www strony z biletami: ").get());
        e.setUrls(urls);
    }

    void editTicket(Event e) {
        Validator v = new Validator();
        Ticket t = new Ticket();
        t.setType(v.inputString("Wprowadź typ biletów: ").get());
        t.setEndTicket(v.inputInteger("Wprowadź maksymalną cenę biletu: ", 0, Integer.MAX_VALUE, true).get());
        t.setStartTicket(v.inputInteger("Wprowadź minimalną cenę biletu: ", 0, t.getEndTicket(), true).get());
        e.setTickets(t);
    }

}
