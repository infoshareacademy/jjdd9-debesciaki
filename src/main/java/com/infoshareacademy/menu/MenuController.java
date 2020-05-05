package com.infoshareacademy.menu;

import com.infoshareacademy.display.DisplayEvents;
import com.infoshareacademy.favourites.AddFavourites;
import com.infoshareacademy.favourites.RemoveFavourites;
import com.infoshareacademy.favourites.ShowFavourites;
import com.infoshareacademy.favourites.ShowUpcoming;
import com.infoshareacademy.properties.PropertiesRepository;

import java.io.IOException;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class MenuController {
    ShowFavourites showFavourites = new ShowFavourites();

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
            MenuMainOption choice = m.showMenu(MenuMainOption.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case EXIT:
                    return;
                case SHOW_EVENTS:
                    showEventsMenu();
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
            DisplayEvents displayEvents = new DisplayEvents();
            MenuEventsOption choice = m.showMenu(MenuEventsOption.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case RETURN:
                    return;
                case ALL:
                    displayEvents.displayAllEvents();
                     break;
                case COMING:
                    cleanConsole();
                    displayEvents.displayComingEvents();
                    break;
                case SEARCH:
                    showEventsSearch();
                    break;
                case FILTER:
                    showEventsFilter();
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
                    return;
                case ADD:
                    showFavourites.run();
                    new AddFavourites().run();
                    break;
                case DELETE:
                    RemoveFavourites removeFavourites = new RemoveFavourites();
                    removeFavourites.run(false);
                    break;
            }
        } while (true);
    }

    private void showSettingsMenu() {
        DisplayMenu<MenuSettingsOption> m = new DisplayMenu<>();
        do {
            cleanConsole();
            MenuSettingsOption choice = m.showMenu(MenuSettingsOption.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case RETURN:
                    return;

                default:
                    showSettingsMenu();
                    break;
            }
        } while (true);
    }

    private void showEventsSearch() {
        DisplayMenu<MenuEventsOptionSearch> m = new DisplayMenu<>();

        do {
            cleanConsole();
            DisplayEvents displayEvents = new DisplayEvents();
            MenuEventsOptionSearch choice = m.showMenu(MenuEventsOptionSearch.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case RETURN:
                    return;
                case SEARCH_BY_ORGANIZER:
                    displayEvents.displaySearchOrganizer();
                    break;
                case SEARCH_BY_NAME:
                    displayEvents.displaySearchName();
                    break;
                case RESET:
                    displayEvents.resetList();
                    break;
                default:
                    showSettingsMenu();
                    break;
            }
        } while (true);
    }

    private void showEventsFilter() {
        DisplayMenu<MenuEventsOptionFilter> m = new DisplayMenu<>();

        do {
            cleanConsole();
            DisplayEvents displayEvents = new DisplayEvents();
            MenuEventsOptionFilter choice = m.showMenu(MenuEventsOptionFilter.values(), PropertiesRepository.getInstance().getProperty("homeOnly"));
            switch (choice) {
                case RETURN:
                    return;
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
                    showSettingsMenu();
                    break;
            }
        } while (true);
    }
}
