package com.infoshareacademy.menu;

import com.infoshareacademy.display.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class MenuController {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        showMainMenu();
    }

    private void showMainMenu() {
        DisplayMenu<MenuMainOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            MenuMainOption choice = m.showMenu(MenuMainOption.values());
            switch (choice) {
                case EXIT:
                    return;
                case SHOW_EVENTS:
                    Display display = new Display();
                    //display.displayComingEvents();
                    //display.displayAllEvents();
                    //display.displaySearch();
                    display.displayAfter();
                    break;
                case SHOW_FAVOURITES:
                    showFavouritesMenu();
                    break;
                case SETTINGS:
                    showSettingsMenu();
                    break;
            }
        } while (true);

    }

    private void showEventsMenu() {
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

    private void showFavouritesMenu() {
        DisplayMenu<MenuFavouritesOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            MenuFavouritesOption choice = m.showMenu(MenuFavouritesOption.values());
            switch (choice) {
                case RETURN:
                    return;
                default:
                    showFavouritesMenu();
                    return;
            }
        } while (true);
    }

    private void showSettingsMenu() {
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
