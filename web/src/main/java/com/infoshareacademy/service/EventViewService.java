package com.infoshareacademy.service;

import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.query.EventQuery;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventViewService {

    @Inject
    EventQuery eventQuery;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public List<EventView> prepareEventsToShow(int firstResult) {

        List<EventView> eventsList = new ArrayList<>();

        for(Event el : eventQuery.eventListWithLimit(firstResult)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public Integer listSize() {
        return eventQuery.sizeList();
    }

    public EventView prepareSingleEvent(Long id) {
        EventView event = new EventView();

        for (Event el : eventQuery.allEventsList()) {
            if(el.getId() == id) {
                event = mapper(el);
            }
        }

        return event;
    }

    private EventView mapper(Event event) {
        EventView eventView = new EventView();
        eventView.setId(event.getId());
        eventView.setName(event.getName());
        eventView.setEndDate(event.getEndDate().format(formatter));
        eventView.setStartDate(event.getStartDate().format(formatter));
        eventView.setCategoryName(event.getCategory().getName().isEmpty() ? null : event.getCategory().getName());
        eventView.setOrganizerName((Optional.ofNullable(event.getOrganizer().getDesignation()).isPresent()) ? event.getOrganizer().getDesignation() : "Brak informacji");
        eventView.setDescShort(Optional.ofNullable(event.getDescShort()).isPresent() ? event.getDescShort() : "Brak informacji" );
        eventView.setDescLong(Optional.ofNullable(event.getDescLong()).isPresent() ? event.getDescLong() : "Brak informacji o wydarzeniu" );
        eventView.setPlaceName(Optional.ofNullable(event.getPlace().getName()).isPresent() ? event.getPlace().getName() : "Brak informacji");
        eventView.setFileName((Optional.ofNullable(event.getAttachments()).isPresent() && event.getAttachments().size() > 0) ? event.getAttachments().get(0).getFileName() : "https://mikado.pl/upload/brak.png");
//        eventView.setOrganizerName(Optional.ofNullable(event.getOrganizer().getDesignation()).get());
//        eventView.setTicket(Optional.ofNullable(event.getTicket().getType()).get());
//        eventView.setMinTicketPrice(Optional.ofNullable(event.getTicket().getStartTicket()).get());
//        eventView.setMaxTicketPrice(Optional.ofNullable(event.getTicket().getEndTicket()).get());
//        eventView.setWebsite(Optional.ofNullable(event.getUrls().getWww()).get());
//        eventView.setPlaceName(Optional.ofNullable(event.getPlace().getName()).get());
        return eventView;
    }

}
