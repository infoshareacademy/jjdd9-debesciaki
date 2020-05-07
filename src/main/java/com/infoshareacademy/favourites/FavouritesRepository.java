package com.infoshareacademy.favourites;

import com.infoshareacademy.parser.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouritesRepository {
    private static List<Event> allFavouritesList = new ArrayList<>();
    private static Map<Integer, Event> allFavouritesMap = new HashMap<>();

    private FavouritesRepository() {
    }

    public static List<Event> getAllFavouritesList() {
        new SaveToFileWithFavourites().run(allFavouritesList);
        return allFavouritesList;
    }

    public static void setAllFavouritesList(List<Event> allFavouritesList) {
        FavouritesRepository.allFavouritesList = allFavouritesList;
    }

    public static Map<Integer, Event> getAllFavouritesMap() {
        return allFavouritesMap;
    }

    public static void setAllFavouritesMap() {
        for (int i = 1; i <= allFavouritesList.size(); i++) {
            allFavouritesMap.put(i,allFavouritesList.get(i-1));
        }
    }

}
