package com.infoshareacademy.repository;

import java.util.NoSuchElementException;

public class UniqueIDprovider {
    public int getEventID() {
        return EventRepository.getAllEventsMap()
                .keySet()
                .stream()
                .mapToInt(v -> v)
                .max()
                .orElseThrow(NoSuchElementException::new) + 1;
    }

    public int getOrganizerID() {
        return OrganizerRepository.getAllOrganizersMap()
                .keySet()
                .stream()
                .mapToInt(v -> v)
                .max()
                .orElseThrow(NoSuchElementException::new) + 1;
    }

    public int getPlaceID() {
        return PlaceRepository.getAllPlacesMap()
                .keySet()
                .stream()
                .mapToInt(v -> v)
                .max()
                .orElseThrow(NoSuchElementException::new) + 1;
    }

}
