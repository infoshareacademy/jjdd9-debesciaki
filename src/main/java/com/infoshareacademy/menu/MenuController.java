package com.infoshareacademy.menu;

import com.infoshareacademy.display.DisplayEvents;
import com.infoshareacademy.properties.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class MenuController {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        showMainMenu();
    }

    private void showMainMenu() {
        PropertiesRepository.getInstance().putBreadcrumb("Menu główne");
        DisplayMenu<MenuMainOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            MenuMainOption choice = m.showMenu(MenuMainOption.values());
            switch (choice) {
                case EXIT:
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    return;
                case SHOW_EVENTS:
                    PropertiesRepository.getInstance().putBreadcrumb("Pokaż wydarzenia");
                    showEventsMenu();
                    PropertiesRepository.getInstance().removeBreadcrumb();
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
            DisplayEvents displayEvents = new DisplayEvents();
            MenuEventsOption choice = m.showMenu(MenuEventsOption.values());
            switch (choice) {
                case RETURN:
                    return;
                case ALL:
                    displayEvents.displayOrganizers();
                     break;
                case COMING:
                    displayEvents.displayComingEvents();
                    break;
                case SEARCH_BY_ORGANIZER:
                    displayEvents.displaySearchOrganizer();
                    break;
                case SEARCH_BY_NAME:
                    displayEvents.displaySearchName();
                    break;
                case FILTER_AFTER:
                    displayEvents.displayAfter();
                    break;
                case FILTER_BEFORE:
                    displayEvents.displayBefore();
                    break;
                case FILTER_BETWEEN:
                    displayEvents.displayPeriodically();
                    break;
                case RESET:
                    displayEvents.resetList();
                    break;
                default:
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
        PropertiesRepository.getInstance().putBreadcrumb("Ustawienia");
        DisplayMenu<MenuSettingsOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            MenuSettingsOption choice = m.showMenu(MenuSettingsOption.values());
            switch (choice) {
                case RETURN:
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    return;

                default:
                    showSettingsMenu();
                    break;
            }
        } while (true);
    }
}
