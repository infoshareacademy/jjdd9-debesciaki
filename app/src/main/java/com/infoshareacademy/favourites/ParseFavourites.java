package com.infoshareacademy.favourites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.parser.Event;
import com.infoshareacademy.repository.EventRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParseFavourites {
    private ObjectMapper mapper = new ObjectMapper();
    private File favouritesJson = new File("favourites.json");

    public void run() {
        List<Integer> listOfFavouritesId = mapperFromFile();
        List<Event> favouritesList = new ArrayList<>();

        for (Integer el : listOfFavouritesId) {
            for (Event event : EventRepository.getAllEventsList()) {
                if (event.getId() == el) {
                    favouritesList.add(event);
                }
            }
        }

        FavouritesRepository.setAllFavouritesList(favouritesList);
    }

    public List<Integer> mapperFromFile() {
        try {
            return Arrays.asList(mapper.readValue(favouritesJson, Integer[].class));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
