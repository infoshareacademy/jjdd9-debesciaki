package com.infoshareacademy.menu;

import com.infoshareacademy.display.Display;

public class MenuController {
    public void run() {
        showMainMenu();
    }

    private void showMainMenu() {
        DisplayMenu<MenuMainOption> m = new DisplayMenu<>();

        do {
            MenuMainOption choice = m.showMenu(MenuMainOption.values());
            switch (choice) {
                case EXIT: {
                    return;
                }
                case SHOW_EVENTS: {
                    Display display = new Display();
                    display.displayCurrentEvents("pp");
                    break;
                }
                case SHOW_FAVOURITES: {
                    showFavouritesMenu();
                    break;
                }
                case SETTINGS: {
                    showSettingsMenu();
                    break;
                }
            }
        } while (true);

    }

    private void showEventsMenu() {
        DisplayMenu<MenuEventsOption> m = new DisplayMenu<>();

        do {
            MenuEventsOption choice = m.showMenu(MenuEventsOption.values());
            switch (choice) {
                case RETURN: {
                    return;
                }
                case FILTER: {
                    return;
                }
                default: {
                    return;
                }
            }
        } while (true);

    }

    private void showFavouritesMenu() {
        DisplayMenu<MenuFavouritesOption> m = new DisplayMenu<>();

        do {
            MenuFavouritesOption choice = m.showMenu(MenuFavouritesOption.values());
            switch (choice) {
                case RETURN: {
                    return;
                }
                case ADD: {
                    return;
                }
                case DELETE: {
                    return;
                }
                case SHOW_EARLIEST: {
                    return;
                }
            }
        } while (true);
    }

    private void showSettingsMenu() {
        DisplayMenu<MenuSettingsOption> m = new DisplayMenu<>();

        do {
            MenuSettingsOption choice = m.showMenu(MenuSettingsOption.values());
            switch (choice) {
                case RETURN: {
                    return;
                }
                case SORT: {
                    return;
                }
                case DATE_FORMAT: {
                    return;
                }
            }
        } while (true);
    }
}
