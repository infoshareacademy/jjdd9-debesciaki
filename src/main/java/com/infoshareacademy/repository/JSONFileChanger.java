package com.infoshareacademy.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Organizer;
import com.infoshareacademy.parser.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JSONFileChanger {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private List<Event> allEventsList = new ArrayList<>();
    private List<Place> allPlaces = new ArrayList<>();
    private List<Organizer> allOrganizers = new ArrayList<>();

    public JSONFileChanger() {
        allEventsList = EventRepository.getAllEventsList();
        allPlaces = PlaceRepository.getAllPlaces();
        allOrganizers = OrganizerRepository.getAllOrganizers();
    }

    public void removeEvent(int id) {
        for (Event e : this.allEventsList) {
            if (e.getId() == id) {
                this.allEventsList.remove(e);
                break;
            }
        }
        EventRepository.setAllEventsList(this.allEventsList);
        writeEventList();
    }

    public void addEvent(Event e) {
        this.allEventsList.add(e);
        EventRepository.setAllEventsList(this.allEventsList);
        writeEventList();
    }

    public void addPlace(Place p) {
        this.allPlaces.add(p);
        PlaceRepository.setAllPlaces(this.allPlaces);
        writePlaceList();
    }

    public void addOrganizer(Organizer o) {
        this.allOrganizers.add(o);
        OrganizerRepository.setAllOrganizers(this.allOrganizers);
        writeOrganizerList();
    }

    public static void writeEventList() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        module.addDeserializer(LocalDateTime.class, deserializer);
        mapper.registerModule(module);
        try {
            File eventsJson = new File("events.json");
            mapper.writeValue(eventsJson, EventRepository.getAllEventsList());
        } catch (IOException e) {
            STDOUT.info("Błąd zapisu pliku do JSON!\n");
        }
    }

    public static void writePlaceList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File placesJson = new File("places.json");
            mapper.writeValue(placesJson, PlaceRepository.getAllPlaces());
        } catch (IOException e) {
            STDOUT.info("Błąd zapisu pliku do JSON!\n");
        }
    }

    public static void writeOrganizerList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File organizersJson = new File("organizers.json");
            mapper.writeValue(organizersJson, OrganizerRepository.getAllOrganizers());
        } catch (IOException e) {
            STDOUT.info("Błąd zapisu pliku do JSON!\n");
        }
    }


}
