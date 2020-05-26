package com.infoshareacademy.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.infoshareacademy.classJSONs.CategoryJSON;
import com.infoshareacademy.classJSONs.EventJSON;
import com.infoshareacademy.classJSONs.OrganizerJSON;
import com.infoshareacademy.classJSONs.PlaceJSON;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReaderJSON {
    public ReaderJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        module.addDeserializer(LocalDateTime.class,deserializer);
        mapper.registerModule(module);


        File organizerJson = new File("organizers.json");
        List<OrganizerJSON> organizerList = mapper.readValue(organizerJson, new TypeReference<List<OrganizerJSON>>() {
        });


        File placesJson = new File("places.json");
        List<PlaceJSON> placesList = mapper.readValue(placesJson, new TypeReference<List<PlaceJSON>>() {
        });

        File eventsJson = new File("events.json");
        List<EventJSON> eventList = mapper.readValue(eventsJson, new TypeReference<List<EventJSON>>() {
        });
    }


}
