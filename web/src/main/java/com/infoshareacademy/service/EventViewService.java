package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventViewService {

    @Inject
    EventDao eventDao;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public List<EventView> prepareEventsToShow(int firstResult) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.eventListWithLimit(firstResult)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShow(int firstResult, String phrase, Boolean isLimited) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListEveOrg(firstResult, phrase, isLimited)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public Integer getAllEventsCount() {
        return eventDao.getAllEventsCount();
    }


    public EventView prepareSingleEvent(Long id) {
        EventView event = new EventView();

        for (Event el : eventDao.allEventsList()) {
            if (el.getId() == id) {
                event = mapper(el);
            }
        }

        return event;
    }

    private EventView mapper(Event event) {
        EventView eventView = new EventView();
        eventView.setId(event.getId());
        eventView.setName(event.getName());
        eventView.setStartDate(event.getStartDate().format(formatter));
        eventView.setEndDate(event.getEndDate().format(formatter));
        eventView.setDescShort(Optional.ofNullable(event.getDescShort()).isPresent() ? event.getDescShort() : "Brak informacji");
        eventView.setDescLong(Optional.ofNullable(event.getDescLong()).isPresent() ? event.getDescLong() : "Brak informacji o wydarzeniu");
        eventView.setCategoryName(event.getCategory().getName().isEmpty() ? null : event.getCategory().getName());
        eventView.setOrganizerName((Optional.ofNullable(event.getOrganizer().getDesignation()).isPresent()) ? event.getOrganizer().getDesignation() : "Brak informacji");
        eventView.setPlaceName(Optional.ofNullable(event.getPlace().getName()).isPresent() ? event.getPlace().getName() : "Brak informacji");
        eventView.setPlaceSubname(Optional.ofNullable(event.getPlace().getSubname()).isPresent() ? event.getPlace().getSubname() : "brak");

        if (eventView.getPlaceName() == eventView.getPlaceSubname()) {
            eventView.setPlaceSubname("brak");
        }

        if (event.getTicket().getType().equals("tickets")) {
            eventView.setTicket("bilety");
        } else if (event.getTicket().getType().equals("free")) {
            eventView.setTicket("wstęp wolny");
        } else {
            eventView.setTicket("brak informacji");
        }

        if (eventView.getTicket() == "bilety") {
            eventView.setMinTicketPrice(event.getTicket().getStartTicket());
            eventView.setMaxTicketPrice(event.getTicket().getEndTicket());
        }

        eventView.setWebsite(Optional.ofNullable(event.getUrls().getWww()).isPresent() ? event.getUrls().getWww() : "Brak strony internetowej");
        eventView.setFacebook(Optional.ofNullable(event.getUrls().getFb()).isPresent() ? event.getUrls().getFb() : "Brak profilu na facebooku");
        eventView.setFileName((Optional.ofNullable(event.getAttachments()).isPresent() && event.getAttachments().size() > 0 && event.getAttachments().get(0).getFileName().contains("http")) ? event.getAttachments().get(0).getFileName() : "https://mikado.pl/upload/brak.png");

        return eventView;
    }

}
