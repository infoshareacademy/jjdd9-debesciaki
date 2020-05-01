package com.infoshareacademy.repository;

import com.infoshareacademy.parser.Organizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizerRepository {
    private static List<Organizer> allOrganizers = new ArrayList<>();
    private static Map<Integer, Organizer> allOrganizersMap = new HashMap<>();

    public static Map<Integer, Organizer> getAllOrganizersMap() {
        return allOrganizersMap;
    }

    public static void setAllOrganizersMap() {
        for (Organizer o : allOrganizers) {
            OrganizerRepository.allOrganizersMap.put(o.getId(), o);
        }
    }

    private OrganizerRepository() {
    }

    public static List<Organizer> getAllOrganizers() {
        return allOrganizers;
    }

    public static void setAllOrganizers(List<Organizer> allOrganizers) {
        OrganizerRepository.allOrganizers = allOrganizers;
    }
}
