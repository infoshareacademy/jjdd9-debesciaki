package com.infoshareacademy.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.infoshareacademy.repository.CategoryRepository;
import com.infoshareacademy.repository.EventRepository;
import com.infoshareacademy.repository.OrganizerRepository;
import com.infoshareacademy.repository.PlaceRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParseService {

    public void parseFiles() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        module.addDeserializer(LocalDateTime.class, deserializer);
        mapper.registerModule(module);

        File organizerJson = new File("organizers.json");
        List<Organizer> organizerList = mapper.readValue(organizerJson, new TypeReference<List<Organizer>>() {
        });
        OrganizerRepository.setAllOrganizers(organizerList);
        OrganizerRepository.setAllOrganizersMap();

        File placesJson = new File("places.json");
        List<Place> placesList = mapper.readValue(placesJson, new TypeReference<List<Place>>() {
        });
        PlaceRepository.setAllPlaces(placesList);
        PlaceRepository.setAllPlacesMap();

        File eventsJson = new File("events.json");
        List<Event> eventList = mapper.readValue(eventsJson, new TypeReference<List<Event>>() {
        });
        EventRepository.setAllEventsList(eventList);
        EventRepository.setAllEventsMap();

        File categoriesJson = new File("categories.json");
        List<Category> categoryList = mapper.readValue(categoriesJson, new TypeReference<List<Category>>() {
        });
        CategoryRepository.setAllCategories(categoryList);
        CategoryRepository.setAllCategoriesMap();
        CategoryRepository.setRelationForCategories();
    }

}
