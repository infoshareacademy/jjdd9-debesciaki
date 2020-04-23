package com.infoshareacademy.repository;

import com.infoshareacademy.parser.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceRepository {
    private static List<Place> allPlaces = new ArrayList<>();

    private PlaceRepository() {
    }

    public static List<Place> getAllPlaces() {
        return allPlaces;
    }

    public static void setAllPlaces(List<Place> allPlaces) {
        PlaceRepository.allPlaces = allPlaces;
    }


}
