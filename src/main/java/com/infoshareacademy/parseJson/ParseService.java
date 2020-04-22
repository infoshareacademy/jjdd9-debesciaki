package com.infoshareacademy.parseJson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.parseJson.Category;
import com.infoshareacademy.parseJson.Event;
import com.infoshareacademy.parseJson.Organizer;
import com.infoshareacademy.parseJson.Place;
import com.infoshareacademy.repositoriesJson.CategoryRepository;
import com.infoshareacademy.repositoriesJson.EventRepository;
import com.infoshareacademy.repositoriesJson.OrganizerRepository;
import com.infoshareacademy.repositoriesJson.PlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParseService {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() throws IOException {
        // Jackson's object mapper, instance for parsing purposes
        ObjectMapper mapper = new ObjectMapper();

        // Below preparing Files from project tree to java object, then using mapper for parsing JSON list based on type
        // reference <List<class>>
        File organizerJson = new File("organizers.json");
        List<Organizer> organizerList = mapper.readValue(organizerJson, new TypeReference<List<Organizer>>() {
        });
        //Singleton
        OrganizerRepository.setAllOrganizers(organizerList);


        File placesJson = new File("places.json");
        List<Place> placesList = mapper.readValue(placesJson, new TypeReference<List<Place>>() {
        });
        //Singleton
        PlaceRepository.setAllPlaces(placesList);


        //Preparing events
        File eventsJson = new File("events.json");
        List<Event> eventList = mapper.readValue(eventsJson, new TypeReference<List<Event>>() {
        });
        //Singleton
        EventRepository.setAllEvents(eventList);

        //Preparing files for  parsing categories
        File categoriesJson = new File("categories.json");
        List<Category> categoryList = mapper.readValue(categoriesJson, new TypeReference<List<Category>>() {
        });
        //Singleton
        CategoryRepository.setAllCategorys(categoryList);

    }

}
