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

/**
 * Hello world!
 */
public class App {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static void main(String[] args) throws IOException {
        STDOUT.info("Hello World!");

        //Testing enironment

        // Jackson's object mapper, instance for parsing purposes
        ObjectMapper mapper = new ObjectMapper();

        // Below preparing Files from project tree to java object, then using mapper for parsing JSON list based on type
        // reference <List<class>>
        File json = new File("organizers.json");
        List<Organizer> organizerList = mapper.readValue(json, new TypeReference<List<Organizer>>() {});

        File json2 = new File("places.json");
        List<Place> placesList = mapper.readValue(json2, new TypeReference<List<Place>>() {});
        //declare + init hashmap
        Map<Integer,Place> placesMap = new HashMap<>();

        //Loop to fill hashmap with list of places
        for(Place p: placesList){
            placesMap.put(p.getId(),p);
        }
        File json3 = new File("events.json");
        List<Event> eventList = mapper.readValue(json3, new TypeReference<List<Event>>() {});

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


    }
}
