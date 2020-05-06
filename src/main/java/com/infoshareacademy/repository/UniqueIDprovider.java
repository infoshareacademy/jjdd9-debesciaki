package com.infoshareacademy.repository;

import java.util.NoSuchElementException;

public class UniqueIDprovider {
    public int getEventID(){
        return EventRepository.getAllEventsMap()
                .keySet()
                .stream()
                .mapToInt(v->v)
                .max()
                .orElseThrow(NoSuchElementException::new)+1;
    }

}
