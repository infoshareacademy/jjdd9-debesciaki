package com.infoshareacademy.repository;

import com.infoshareacademy.parser.Place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceRepository {
    private static List<Place> allPlaces = new ArrayList<>();
    private static Map<Integer, Place> allPlacesMap = new HashMap<>();;

    private PlaceRepository() {
    }

    public static List<Place> getAllPlaces() {
        return allPlaces;
    }

    public static void setAllPlaces(List<Place> allPlaces) {
        PlaceRepository.allPlaces = allPlaces;
    }

    public static Map<Integer, Place> getAllPlacesMap() {
        return allPlacesMap;
    }

    public static void setAllPlacesMap() {
        for (Place p : allPlaces) {
            PlaceRepository.allPlacesMap.put(p.getId(), p);
        }
    }
}
