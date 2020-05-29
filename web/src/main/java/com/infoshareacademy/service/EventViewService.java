package com.infoshareacademy.service;

import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.query.EventQuery;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventViewService {

    @Inject
    EventQuery eventQuery;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public List<EventView> prepareEventsToShow(int firstResult) {

        List<EventView> eventsList = new ArrayList<>();

        for(Event el : eventQuery.eventList(firstResult)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public Integer listSize() {
        return eventQuery.sizeList();
    }

    private EventView mapper(Event event) {
        EventView eventView = new EventView();
        eventView.setId(event.getId());
        eventView.setName(event.getName());
        eventView.setEndDate(event.getEndDate().format(formatter));
        eventView.setStartDate(event.getStartDate().format(formatter));
        eventView.setCategoryName(event.getCategory().getName());
        return eventView;
    }

}
