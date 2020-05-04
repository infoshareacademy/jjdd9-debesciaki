package com.infoshareacademy.favourites;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.parser.Event;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadFromFavouritesFile {
    ObjectMapper mapper = new ObjectMapper();
    File favouritesJson = new File("favourites.json");

    public void run() throws IOException {
        List<Integer> listOfFavouritesId = mapper.readValue(favouritesJson, new TypeReference<List<Integer>>(){});
        List<Event> listOfFavourites = null;
        for (Integer el : listOfFavouritesId) {
            listOfFavourites.add()
        }
    }

}
