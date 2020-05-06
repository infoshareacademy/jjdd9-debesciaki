package com.infoshareacademy.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.infoshareacademy.parser.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepository {
    private static List<Event> allEventsList = new ArrayList<>();
    private static Map<Integer, Event> allEventsMap = new HashMap<>();
    ;

    private EventRepository() {
    }

    public static List<Event> getAllEventsList() {
        return allEventsList;
    }

    public static void setAllEventsList(List<Event> allEventsList) {
        EventRepository.allEventsList = allEventsList;
    }

    public static Map<Integer, Event> getAllEventsMap() {
        return allEventsMap;
    }

    public static void setAllEventsMap() {
        for (Event e : allEventsList) {
            EventRepository.allEventsMap.put(e.getId(), e);
        }
    }

    public static void removeEvent(int id) {
        for (Event e : allEventsList) {
            if (e.getId() == id) {
                allEventsList.remove(e);
                break;
            }
        }
    }

    public static void addEvent(Event e) {
        allEventsList.add(e);
    }

    public static void writeEventList() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        module.addDeserializer(LocalDateTime.class, deserializer);
        mapper.registerModule(module);
        //mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            File eventsJson = new File("events.json");
            mapper.writeValue(eventsJson, EventRepository.getAllEventsList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
