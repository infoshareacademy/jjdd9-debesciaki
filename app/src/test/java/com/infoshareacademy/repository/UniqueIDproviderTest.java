package com.infoshareacademy.repository;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Organizer;
import com.infoshareacademy.parser.ParseService;
import com.infoshareacademy.parser.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UniqueIDproviderTest {
    @BeforeEach
    public void parse() throws IOException {
        new ParseService().parseFiles();
    }

    @Test
    void getEventID() {
        //GIVEN
        Map<Integer, Event> eventMap = EventRepository.getAllEventsMap();
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        int result;

        //WHEN
        result = uniqueIDprovider.getEventID();

        //THEN
        org.assertj.core.api.Assertions.assertThat(eventMap).doesNotContainKey(result);

    }

    @Test
    void getOrganizerID() {
        //GIVEN
        Map<Integer, Organizer> organizerMap = OrganizerRepository.getAllOrganizersMap();
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        int result;

        //WHEN
        result = uniqueIDprovider.getOrganizerID();

        //THEN
        org.assertj.core.api.Assertions.assertThat(organizerMap).doesNotContainKey(result);

    }

    @Test
    void getPlaceID() {
        //GIVEN
        Map<Integer, Place> placeMap = PlaceRepository.getAllPlacesMap();
        UniqueIDprovider uniqueIDprovider = new UniqueIDprovider();
        int result;

        //WHEN
        result = uniqueIDprovider.getPlaceID();

        //THEN
        org.assertj.core.api.Assertions.assertThat(placeMap).doesNotContainKey(result);

    }
}