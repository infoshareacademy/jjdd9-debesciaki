package com.infoshareacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseTester {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() throws IOException {
        STDOUT.info("Hello World!");

        //Testing enironment

        // Jackson's object mapper, instance for parsing purposes
        ObjectMapper mapper = new ObjectMapper();

        // Below preparing Files from project tree to java object, then using mapper for parsing JSON list based on type
        // reference <List<class>>
        File json = new File("organizers.json");
        List<Organizer> organizerList = mapper.readValue(json, new TypeReference<List<Organizer>>() {
        });
        //Singleton


        File json2 = new File("places.json");
        List<Place> placesList = mapper.readValue(json2, new TypeReference<List<Place>>() {
        });
        //Singleton
        EventRepository.setAllEvents(eventList);

        //declare + init hashmap
        Map<Integer, Place> placesMap = new HashMap<>();

        //Loop to fill hashmap with list of places
        for (Place p : placesList) {
            placesMap.put(p.getId(), p);
        }

        //Preparing events
        File json3 = new File("events.json");
        List<Event> eventList = mapper.readValue(json3, new TypeReference<List<Event>>() {
        });
        //Singleton
        EventRepository.setAllEvents(eventList);

        //Preparing files for  parsing categories
        File json4 = new File("categories.json");
        List<Category> categoryList = mapper.readValue(json4, new TypeReference<List<Category>>() {
        });
        //Singleton

        //declare + init hashmap
        Map<Integer, Category> rootsMap = new HashMap<>();

        //Loop to fill hashmap with list of places
        for (Category r : categoryList) {
            rootsMap.put(r.getId(), r);
        }

        //Singletons prints

        //Events
        /*

        for (Event e : EventRepository.getAllEvents()) {
            STDOUT.info("ID= {} name= {}  \n  Category ID= {}   \n", e.getId(), e.getName(), e.getCategoryId());
            Place p = e.getPlace();
            STDOUT.info("Place ID: {} \n", p.getId());
        }

        */

        //Place


        /*
        //Print organizers

        for (Organizer o: organizerList){
            STDOUT.info("ID: {}  Organizator: {}\n",o.getId() ,o.getDesignation());
        }


        //Print places

        for (Place z: placesList){
            STDOUT.info("ID= {}  Name: {}   City: {} \n",z.getId(),z.getName(),z.getAddress().getCity());
            STDOUT.info("Zipcode= {}   City: {}  SubName: {}  \n",z.getAddress().getZipcode(),z.getAddress().getStreet(),z.getSubname());
            STDOUT.info("Street= {}   Lat: {}  Lng: {}  \n",z.getAddress().getStreet(),z.getAddress().getLat(),z.getAddress().getLng());
        }

        //Print events

        for(Event e: eventList){
            STDOUT.info("ID= {} name= {}  \n  Category ID= {}   \n",e.getId(),e.getName(),e.getCategoryId());
            Place p=e.getPlace();
            STDOUT.info("Place ID: {} \n",p.getId());
        }



        //Print categories
        for (Category c: categoryList){
            RootCategory r=c.getRootCategory();
            STDOUT.info("Category ID: {} Name: {} \n",c.getId(),c.getName());
            if (r!=null) {
                STDOUT.info("Root ID: {} Name: {} \n", r.getId(), r.getName());
            }
        }
        */


    }

}
