package com.infoshareacademy.comparator;


import com.infoshareacademy.domain.view.EventView;

import java.util.Comparator;

public class EventViewComparators {
    public static Comparator<EventView> EventDateComparatorAsc = new Comparator<EventView>() {

        public int compare(EventView event1, EventView event2) {
            String eventName1 = event1.getStartDate();
            String eventName2 = event2.getStartDate();
            return eventName1.compareTo(eventName2);
        }

    };
}
