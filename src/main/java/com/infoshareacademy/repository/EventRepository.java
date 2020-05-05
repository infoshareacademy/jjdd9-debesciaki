package com.infoshareacademy.repository;

import com.infoshareacademy.parser.Event;

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
            }
        }
    }

    public static void addEvent(Event e){
        allEventsList.add(e);
    }
}
