package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AddAvailableTicketsService {
    @Inject
    private EventDao eventDao;

    public String update() {
        List<Event> eventList = eventDao.allEventsList();
        for (Event e : eventList) {
            addTicket(e);
        }
        return "Events updated with 100 tickets available";
    }

    private void addTicket(Event event) {
        event.setTicketAmount(100L);
        eventDao.update(event);
    }

}
