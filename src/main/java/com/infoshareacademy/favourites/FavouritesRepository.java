package com.infoshareacademy.favourites;

import com.infoshareacademy.parser.Event;

import java.util.ArrayList;
import java.util.List;

public class FavouritesRepository {
    private static List<Event> allFavouritesList = new ArrayList<>();

    private FavouritesRepository() {
    }

    public static List<Event> getAllFavouritesList() {
        return allFavouritesList;
    }

    public static void setAllFavouritesList(List<Event> allFavouritesList) {
        FavouritesRepository.allFavouritesList = allFavouritesList;
    }

}
