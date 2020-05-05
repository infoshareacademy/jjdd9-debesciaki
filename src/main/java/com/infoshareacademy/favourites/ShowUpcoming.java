package com.infoshareacademy.favourites;

import com.infoshareacademy.display.ConsoleColor;
import com.infoshareacademy.display.DisplayEvents;
import com.infoshareacademy.parser.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowUpcoming {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    DisplayEvents display = new DisplayEvents();

    public ShowUpcoming() {
        run();
    }

    public void run() {
        if (FavouritesRepository.getAllFavouritesList().isEmpty()) {
            STDOUT.info("{}BRAK ULUBIONYCH WYDARZEŃ\n{}", ConsoleColor.RED_BOLD, ConsoleColor.RESET);
        } else {
            STDOUT.info("{}NAJBLIŻSZE ULUBIONE WYDARZENIE:{}\n", ConsoleColor.YELLOW, ConsoleColor.RESET);
            Event upcomingEvent = FavouritesRepository.getAllFavouritesList().get(0);
            for (int i = 0; i < FavouritesRepository.getAllFavouritesList().size(); i++) {
                if (FavouritesRepository.getAllFavouritesList().get(i).getStartDate().isBefore(upcomingEvent.getStartDate())) {
                    upcomingEvent = FavouritesRepository.getAllFavouritesList().get(i);
                }
            }
            display.consolePrintSingleEventScheme(upcomingEvent);
        }
    }
}
