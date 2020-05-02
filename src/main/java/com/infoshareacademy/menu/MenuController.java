package com.infoshareacademy.menu;

import com.infoshareacademy.favourites.AddFavourites;
import com.infoshareacademy.favourites.ShowFavourites;
import com.infoshareacademy.favourites.ShowUpcoming;
import com.infoshareacademy.properties.PropertiesRepository;
import com.infoshareacademy.display.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class MenuController {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    ShowFavourites showFavourites = new ShowFavourites();
    AddFavourites addFavourites = new AddFavourites();

    public MenuController() throws IOException {
    }

    public void run() throws IOException {
        showMainMenu();
    }

    private void showMainMenu() throws IOException {
        DisplayMenu<MenuMainOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            if (PropertiesRepository.getInstance().getProperty("homeOnly").equals("true")) {
                new ShowUpcoming();
            }
            MenuMainOption choice = m.showMenu(MenuMainOption.values());
            switch (choice) {
                case EXIT:
                    return;
                case SHOW_EVENTS:
                    Display display = new Display();
                    display.displayCurrentEvents();
                    break;
                case SHOW_FAVOURITES:
                    cleanConsole();
                    showFavourites.run();
                    showFavouritesMenu();
                    break;
                case SETTINGS:
                    showSettingsMenu();
                    break;
            }
        } while (true);

    }

    private void showEventsMenu() throws IOException {
        DisplayMenu<MenuEventsOption> m = new DisplayMenu<>();
        do {
            cleanConsole();
            MenuEventsOption choice = m.showMenu(MenuEventsOption.values());
            switch (choice) {
                case RETURN:
                    return;

                default:
                    showEventsMenu();
                    break;
            }
        } while (true);

    }

    private void showFavouritesMenu() throws IOException {
        DisplayMenu<MenuFavouritesOption> m = new DisplayMenu<>();

        do {
            MenuFavouritesOption choice = m.showMenu(MenuFavouritesOption.values());
            switch (choice) {
                case RETURN:
                    return;
                case ADD:
                    showFavourites.run();
                    addFavourites.run();
                    break;
            }
        } while (true);
    }

    private void showSettingsMenu() throws IOException {
        DisplayMenu<MenuSettingsOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            MenuSettingsOption choice = m.showMenu(MenuSettingsOption.values());
            switch (choice) {
                case RETURN:
                    return;

                default:
                    showSettingsMenu();
                    break;
            }
        } while (true);
    }
}
