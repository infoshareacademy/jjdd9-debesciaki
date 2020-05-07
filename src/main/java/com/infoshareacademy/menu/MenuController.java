package com.infoshareacademy.menu;

import com.infoshareacademy.display.DisplayEvents;
import com.infoshareacademy.favourites.*;
import com.infoshareacademy.properties.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class MenuController {
    ShowFavourites showFavourites = new ShowFavourites();
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");


    public void run() throws IOException {
        showMainMenu();
    }

    private void showMainMenu() throws IOException {
        PropertiesRepository.getInstance().putBreadcrumb("Menu główne ");
        DisplayMenu<MenuMainOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            if (PropertiesRepository.getInstance().getProperty("homeOnly").equals("true")) {
                new ShowUpcoming();
            }
            MenuMainOption choice = m.showMenu(MenuMainOption.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case EXIT:
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    return;
                case SHOW_EVENTS:
                    PropertiesRepository.getInstance().putBreadcrumb(" Pokaż wydarzenia ");
                    showEventsMenu();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case SHOW_FAVOURITES:
                    PropertiesRepository.getInstance().putBreadcrumb(" Pokaż ulubione wydarzenia ");
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
            DisplayEvents displayEvents = new DisplayEvents();
            MenuEventsOption choice = m.showMenu(MenuEventsOption.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case RETURN:
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    return;
                case ALL:
                    PropertiesRepository.getInstance().putBreadcrumb("Wszystkie");
                    displayEvents.displayAllEvents();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case COMING:
                    cleanConsole();
                    PropertiesRepository.getInstance().putBreadcrumb("Nadchodzące");
                    displayEvents.displayComingEvents();
                    break;
                case SEARCH_BY_ORGANIZER:
                    PropertiesRepository.getInstance().putBreadcrumb("Wyszukuj frazą organizatora");
                    displayEvents.displaySearchOrganizer();
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
                    showEventsMenu();
                    break;
            }
        } while (true);

    }

    private void showFavouritesMenu() {
        DisplayMenu<MenuFavouritesOption> m = new DisplayMenu<>();

        do {
            showFavourites.run();
            MenuFavouritesOption choice = m.showMenu(MenuFavouritesOption.values(), "true");
            switch (choice) {
                case RETURN:
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    return;
                case ADD:
                    PropertiesRepository.getInstance().putBreadcrumb(" Dodaj do ulubionych ");
                    showFavourites.run();
                    new AddFavourites().run();
                    PropertiesRepository.getInstance().removeBreadcrumb();
                    break;
                case DELETE:
                    if (FavouritesRepository.getAllFavouritesList().isEmpty()) {
                        STDOUT.info("Brak wydarzeń do usunięcia.");
                    } else {
                        PropertiesRepository.getInstance().putBreadcrumb(" Usuń z ulubionych ");
                        RemoveFavourites removeFavourites = new RemoveFavourites();
                        removeFavourites.run(false);
                        PropertiesRepository.getInstance().removeBreadcrumb();
                    }
                    break;
            }
        } while (true);
    }

    private void showSettingsMenu() {
        PropertiesRepository.getInstance().putBreadcrumb("Ustawienia");
        DisplayMenu<MenuSettingsOption> m = new DisplayMenu<>();

        do {
            cleanConsole();
            MenuSettingsOption choice = m.showMenu(MenuSettingsOption.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
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
