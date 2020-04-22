package com.infoshareacademy.repositoriesJson;

import com.infoshareacademy.parseJson.Organizer;

import java.util.ArrayList;
import java.util.List;

public class OrganizerRepository {
    private static List<Organizer> allOrganizers = new ArrayList<>();

    private OrganizerRepository() {
    }

    public static List<Organizer> getAllOrganizers() {
        return allOrganizers;
    }

    public static void setAllOrganizers(List<Organizer> allOrganizers) {
        OrganizerRepository.allOrganizers = allOrganizers;
    }
}
