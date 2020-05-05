package com.infoshareacademy.favourites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.parser.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveToFileWithFavourites {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run(List<Event> events) {
        try {
            List<Integer> idToSave = new ArrayList<>();
            for (int i = 0; i < events.size(); i++) {
                idToSave.add(events.get(i).getId());
            }

            idToSave.stream()
                    .distinct()
                    .collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get("favourites.json").toFile(), idToSave);
        } catch (IOException e) {
            STDOUT.info("Błąd przy próbie zapisu pliku.");
        }
    }
}