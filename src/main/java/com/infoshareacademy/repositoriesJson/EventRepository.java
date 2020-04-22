package com.infoshareacademy.repositoriesJson;

import com.infoshareacademy.parseJson.Event;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private static List<Event> allEvents = new ArrayList<>();

    private EventRepository() {
    }

    public static List<Event> getAllEvents() {
        return allEvents;
    }

    public static void setAllEvents(List<Event> allEvents) {
        EventRepository.allEvents = allEvents;
    }
}
