package com.infoshareacademy.favourites;

import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.parser.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RemoveFavourites {

    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    ShowFavourites showFavourites = new ShowFavourites();
    List<Event> listFavourites = showFavourites.getEvents();
    DisplayMenu displayMenu = new DisplayMenu();
    Scanner scanner = new Scanner(System.in);
    SaveToFileWithFavourites save = new SaveToFileWithFavourites();

    public RemoveFavourites() throws IOException {
    }

    void run() throws IOException {
        showFavourites.run();
        STDOUT.info("Maksymalna liczba ulubionych wydarzeń - 3\n");
        STDOUT.info("Wybierz wydarzenie, które chciałbyś usunąć (wpisz 0 aby cofnąć): ");
        Integer choiceFromKeyboard = displayMenu.tryGetChoiceFromKeyboard(3);

        if (choiceFromKeyboard != 0) {
            listFavourites.remove(choiceFromKeyboard - 1);
            save.run(listFavourites);
        }
       // showFavourites.run();
    }
}
