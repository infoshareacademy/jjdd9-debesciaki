package com.infoshareacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParseToRepos {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() throws IOException {
        // Jackson's object mapper, instance for parsing purposes
        ObjectMapper mapper = new ObjectMapper();

        // Below preparing Files from project tree to java object, then using mapper for parsing JSON list based on type
        // reference <List<class>>
        File json = new File("organizers.json");
        List<Organizer> organizerList = mapper.readValue(json, new TypeReference<List<Organizer>>() {
        });
        //Singleton
        OrganizerRepository.setAllOrganizers(organizerList);


        File json2 = new File("places.json");
        List<Place> placesList = mapper.readValue(json2, new TypeReference<List<Place>>() {
        });
        //Singleton
        PlaceRepository.setAllPlaces(placesList);


        //Preparing events
        File json3 = new File("events.json");
        List<Event> eventList = mapper.readValue(json3, new TypeReference<List<Event>>() {
        });
        //Singleton
        EventRepository.setAllEvents(eventList);

        //Preparing files for  parsing categories
        File json4 = new File("categories.json");
        List<Category> categoryList = mapper.readValue(json4, new TypeReference<List<Category>>() {
        });
        //Singleton
        CategoryRepository.setAllCategorys(categoryList);

    }

}
