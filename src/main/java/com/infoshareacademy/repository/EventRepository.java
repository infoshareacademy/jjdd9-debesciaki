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
}
