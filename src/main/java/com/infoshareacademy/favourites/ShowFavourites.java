package com.infoshareacademy.favourites;

import com.infoshareacademy.display.CMDCleaner;
import com.infoshareacademy.display.ConsoleColor;
import com.infoshareacademy.display.DisplayEvents;
import com.infoshareacademy.parser.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowFavourites {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    DisplayEvents display = new DisplayEvents();

    public void run() {
        if (FavouritesRepository.getAllFavouritesList().isEmpty()) {
            CMDCleaner.cleanConsole();
            STDOUT.info("{}BRAK ULUBIONYCH WYDARZEŃ{}\n", ConsoleColor.RED_BOLD, ConsoleColor.RESET);
        } else {
            CMDCleaner.cleanConsole();
            STDOUT.info("{}\nWSZYSTKIE ULUBIONE WYDARZENIA{}\n", ConsoleColor.YELLOW, ConsoleColor.RESET);
            for (Event el : FavouritesRepository.getAllFavouritesList()) {
                display.consolePrintSingleEventScheme(el);
            }
        }

    }
}
