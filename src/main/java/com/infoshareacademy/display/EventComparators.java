package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;

import java.time.LocalDateTime;
import java.util.Comparator;

public class EventComparators {
    public static Comparator<Event> EventNameComparatorAsc = new Comparator<Event>() {

        public int compare(Event event1, Event event2) {
            String eventName1 = event1.getName().toUpperCase();
            String eventName2 = event2.getName().toUpperCase();
            return eventName1.compareTo(eventName2);
        }

    };
    public static Comparator<Event> EventNameComparatorDesc = new Comparator<Event>() {

        public int compare(Event event1, Event event2) {
            String eventName1 = event1.getName().toUpperCase();
            String eventName2 = event2.getName().toUpperCase();
            return eventName2.compareTo(eventName1);
        }

    };
    public static Comparator<Event> EventOrganizerComparatorAsc = new Comparator<Event>() {

        public int compare(Event event1, Event event2) {
            String eventOrganizer1 = event1.getOrganizer().getDesignation();
            String eventOrganizer2 = event2.getOrganizer().getDesignation();
            return eventOrganizer1.compareTo(eventOrganizer2);
        }

    };
    public static Comparator<Event> EventOrganizerComparatorDesc = new Comparator<Event>() {

        public int compare(Event event1, Event event2) {
            String eventOrganizer1 = event1.getOrganizer().getDesignation();
            String eventOrganizer2 = event2.getOrganizer().getDesignation();
            return eventOrganizer2.compareTo(eventOrganizer1);
        }

    };
    public static Comparator<Event> EventEndDateComparatorAsc = new Comparator<Event>() {

        public int compare(Event event1, Event event2) {
            LocalDateTime eventLDT1 = event1.getEndDate();
            LocalDateTime eventLDT2 = event2.getEndDate();
            return eventLDT1.compareTo(eventLDT2);
        }

    };
    public static Comparator<Event> EventEndDateComparatorDesc = new Comparator<Event>() {

        public int compare(Event event1, Event event2) {
            LocalDateTime eventLDT1 = event1.getEndDate();
            LocalDateTime eventLDT2 = event2.getEndDate();
            return eventLDT2.compareTo(eventLDT1);
        }

    };
}