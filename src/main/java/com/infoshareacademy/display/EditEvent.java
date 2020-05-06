package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditEvent {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    EditEvent(){

    }

    void consolePrintForEditing(Event e) {
        EventPrinter eventPrinter = new EventPrinter(ConsoleColor.BLUE_BACKGROUND, ConsoleColor.RED_BACKGROUND);
        eventPrinter.printName(e);
        eventPrinter.printOrganizer(e);
        eventPrinter.printPlace(e);
        eventPrinter.printStartDate(e);
        eventPrinter.printEndDate(e);
        eventPrinter.printLongDesc(e);
        eventPrinter.printShortDesc(e);
        eventPrinter.printTickets(e);
        eventPrinter.printActive(e);
        STDOUT.info("\n");
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

    void editName(Event e){
        Validator v =new Validator();
        e.setName(v.inputString("Wprowadź nazwę wydarzenia: ").get());
    }
    void editShortDesc(Event e){
        Validator v =new Validator();
        e.setDescShort(v.inputString("Wprowadź krótki opis: ").get());
    }
    void editLongDesc(Event e){
        Validator v =new Validator();
        e.setDescLong(v.inputString("Wprowadź długi opis: ").get());
    }
}
