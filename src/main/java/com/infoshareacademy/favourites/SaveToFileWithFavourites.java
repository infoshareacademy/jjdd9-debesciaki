package com.infoshareacademy.favourites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.parser.Event;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveToFileWithFavourites {
    public void run(List<Event> events) throws IOException {
        List<Integer> idFromEvents = new ArrayList<>();
        for (Event el : events) {
            idFromEvents.add(el.getId());
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get("favourites.json").toFile(), idFromEvents);
    }
}
