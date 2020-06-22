package com.infoshareacademy.comparator;


import com.infoshareacademy.domain.view.EventView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class EventViewComparators {
    public static Comparator<EventView> EventDateComparatorAsc = new Comparator<EventView>() {

        public int compare(EventView event1, EventView event2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            LocalDateTime eventLDT1 = LocalDateTime.parse(event1.getStartDateAll(), formatter);
            LocalDateTime eventLDT2 = LocalDateTime.parse(event2.getStartDateAll(), formatter);
            return eventLDT1.compareTo(eventLDT2);
        }
    };
}
