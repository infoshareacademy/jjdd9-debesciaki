package com.infoshareacademy.favourites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infoshareacademy.parser.Event;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class SaveToFileWithFavourites {
    public void run(List<Event> events) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.writeValue(Paths.get("favourites.json").toFile(), events);
    }
}
