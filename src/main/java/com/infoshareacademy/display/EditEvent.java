package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Organizer;
import com.infoshareacademy.parser.Place;
import com.infoshareacademy.parser.Url;
import com.infoshareacademy.repository.UniqueIDprovider;
import com.infoshareacademy.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditEvent {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    EditEvent(){

    }

    void consolePrintEditOptions() {
        STDOUT.info("{}1 - Nazwa{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}2 - Data startu{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}3 - Data zakończenia{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}4 - Miejsce{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}5 - Organizator{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}6 - Opis krótki{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}7 - Opis długi{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}8 - Adres www{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
        STDOUT.info("{}0 - Wyjdź{}\n", ConsoleColor.RED_UNDERLINED, ConsoleColor.RESET);
    }
    void addId(Event e){
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        e.setId(uniqueIDprovider.getEventID());
    }
    void editName(Event e){
        Validator v =new Validator();
        e.setName(v.inputString("Wprowadź nazwę wydarzenia: ").get());
    }
    void editOrganizer(Event e){
        Validator v =new Validator();
        Organizer o = new Organizer();
        o.setDesignation(v.inputString("Wprowadź nazwę organizatora: ").get());
    }
    void editShortDesc(Event e){
        Validator v =new Validator();
        e.setDescShort(v.inputString("Wprowadź krótki opis: ").get());
    }
    void editLongDesc(Event e){
        Validator v =new Validator();
        e.setDescLong(v.inputString("Wprowadź długi opis: ").get());
    }
    void editStartDate(Event e){
        Validator v = new Validator();
       e.setStartDate(v.localDateTimeRequest("Data rozpoczęcia wydarzenia."));
    }
    void editEndDate(Event e){
        Validator v = new Validator();
        e.setEndDate(v.localDateTimeRequest("Data zakończenia wydarzenia."));
    }
    void editPlace(Event e){
        Validator v = new Validator();
        Place p =new Place();
        p.setName(v.inputString("Wprowadź nazwę miejsca: ").get());
        p.setSubname(v.inputString("Wprowadź nazwę dodatkową miejsca: ").get());
        p.getAddress().setCity(v.inputString("Wprowadź miasto: ").get());
        p.getAddress().setZipcode(v.inputString("Wprowadź kod pocztowy: ").get());
        p.getAddress().setStreet(v.inputString("Wprowadź ulicę: ").get());
        e.setPlace(p);
    }
    void editUrl(Event e){
        Validator v = new Validator();
        Url urls = new Url();
        urls.setFb(v.inputString("Wprowadź adres facebook: ").get());
        urls.setWww(v.inputString("Wprowadź adres www: ").get());
        urls.setTickets(v.inputString("Wprowadź adres www strony z biletami: ").get());
        e.setUrls(urls);
    }
}
