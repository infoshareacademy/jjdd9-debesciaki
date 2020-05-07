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
                    PropertiesRepository.getInstance().putBreadcrumb("Wszystkie");
                    displayEvents.displayAllEvents();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case COMING:
                    PropertiesRepository.getInstance().putBreadcrumb("Nadchodzące");
                    displayEvents.displayComingEvents();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case SEARCH_BY_ORGANIZER:
                    PropertiesRepository.getInstance().putBreadcrumb("Wyszukuj frazą organizatora");
                    displayEvents.displaySearchOrganizer();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case SEARCH_BY_NAME:
                    PropertiesRepository.getInstance().putBreadcrumb("Wyszukuj frazą w nazwie");
                    displayEvents.displaySearchName();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case FILTER_BY_ORGANIZER:
                    PropertiesRepository.getInstance().putBreadcrumb("Wybrani organizatorzy z listy");
                    displayEvents.displayOrganizers();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case FILTER_AFTER:
                    PropertiesRepository.getInstance().putBreadcrumb("Wydarzenia OD");
                    displayEvents.displayAfter();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case FILTER_BEFORE:
                    PropertiesRepository.getInstance().putBreadcrumb("Wydarzenia DO");
                    displayEvents.displayBefore();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case FILTER_BETWEEN:
                    PropertiesRepository.getInstance().putBreadcrumb("Wydarzenia OD DO");
                    displayEvents.displayPeriodically();
                    PropertiesRepository.getInstance().removeBreadcrumb();
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
