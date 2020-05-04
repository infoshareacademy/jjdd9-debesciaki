package com.infoshareacademy.favourites;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infoshareacademy.display.CMDCleaner;
import com.infoshareacademy.display.DisplayEvents;
import com.infoshareacademy.parser.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ShowFavourites {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    File favouritesJson = new File("favourites.json");
    DisplayEvents display = new DisplayEvents();

    public void run() throws IOException {
        List<Event> favouritesList = getEvents();
        if (favouritesList.isEmpty()) {
            CMDCleaner.cleanConsole();
            STDOUT.info("BRAK ULUBIONYCH WYDARZEŃ\n");
        } else {
            CMDCleaner.cleanConsole();
            STDOUT.info("TWOJE ULUBIONE WYDARZENIA\n");
            for (Event el : favouritesList) {
                display.consolePrintSingleEventScheme(el);
            }
        }

    }

    public List<Event> getEvents() throws IOException {
        try {
            return mapper.readValue(favouritesJson, new TypeReference<List<Event>>() {
            });
        } catch (MismatchedInputException e) {
            return Collections.emptyList();
        }
    }
}
