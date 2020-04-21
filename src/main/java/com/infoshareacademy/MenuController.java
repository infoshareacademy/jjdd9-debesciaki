package com.infoshareacademy;

public class MenuController {
    public void run() {
        showMainMenu();
    }

    private void showMainMenu() {
        DisplayMenu<MenuMainOption> m = new DisplayMenu<>();
        MenuMainOption choice = m.showMenu(MenuMainOption.values());

        do {
            switch (choice) {
                case EXIT: {
                    return;
                }
                case SHOW_EVENTS: {
                    showEventsMenu();
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
        MenuEventsOption choice = m.showMenu(MenuEventsOption.values());

        do {
            switch (choice) {
                case RETURN: {
                    showMainMenu();
                    break;
                }
                case FILTER: {
                    showMainMenu();
                    break;
                }
                default: {
                    showMainMenu();
                    break;
                }
            }
        } while (true);

    }

    private void showFavouritesMenu() {
        DisplayMenu<MenuFavouritesOption> m = new DisplayMenu<>();
        MenuFavouritesOption choice = m.showMenu(MenuFavouritesOption.values());

        do {
            switch (choice) {
                case RETURN: {
                    showMainMenu();
                    break;
                }
                case ADD: {
                    showMainMenu();
                    break;
                }
                case DELETE: {
                    showMainMenu();
                    break;
                }
                case SHOW_EARLIEST: {
                    showMainMenu();
                    break;
                }
            }
        } while (true);
    }

    private void showSettingsMenu() {
        DisplayMenu<MenuSettingsOption> m = new DisplayMenu<>();
        MenuSettingsOption choice = m.showMenu(MenuSettingsOption.values());

        do {
           switch (choice) {
               case RETURN: {
                   showMainMenu();
                   break;
               }
               case SORT: {
                   showMainMenu();
                   break;
               }
               case DATE_FORMAT: {
                   showMainMenu();
                   break;
               }
           }
        } while (true);
    }
}
