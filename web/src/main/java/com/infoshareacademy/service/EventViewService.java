package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Stateless
public class EventViewService {

    @Inject
    private EventDao eventDao;

    @Inject
    private UserService userService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public List<EventView> prepareFavouriteEvents(int firstResult, String email) {
        List<EventView> eventsList = new ArrayList<>();

        User user = userService.findByEmail(email);

        int i = 0;
        for (Event el : user.getEvents()) {
            if (i >= firstResult) {
                eventsList.add(mapper(el));
            }
            i++;
        }

        return eventsList;
    }

    public Integer getFavouritesCount(String email) {
        Set<Event> eventsList = new HashSet<>();
        User user = userService.findByEmail(email);
        Set<Event> events = user.getEvents();
        return events.size();
    }

    public List<EventView> prepareEventsToShow(int firstResult) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.eventListWithLimit(firstResult)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShowByEveOrg(int firstResult, String phrase, Boolean isLimited) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListEveOrg(firstResult, phrase, isLimited)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShowByEveOrgDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListEveOrgDate(firstResult, phrase, isLimited, start, end)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShowByEve(int firstResult, String phrase, Boolean isLimited) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListEve(firstResult, phrase, isLimited)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShowByEveDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListEveDate(firstResult, phrase, isLimited, start, end)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShowByOrg(int firstResult, String phrase, Boolean isLimited) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListOrg(firstResult, phrase, isLimited)) {
            eventsList.add(mapper(el));
        }

        return eventsList;
    }

    public List<EventView> prepareSearchedEventsToShowByOrgDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {

        List<EventView> eventsList = new ArrayList<>();

        for (Event el : eventDao.searchByPhraseListOrgDate(firstResult, phrase, isLimited, start, end)) {
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

    public EventView mapper(Event event) {
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

    public List<EventView> listEvents(Integer firstResult, String cleanPhrase, int eve, int org, int date, LocalDateTime start, LocalDateTime end) {
        if (eve == 1 && org == 0 && date == 0) {
            return prepareSearchedEventsToShowByEve(firstResult, cleanPhrase, true);
        } else if (eve == 0 && org == 1 && date == 0) {
            return prepareSearchedEventsToShowByOrg(firstResult, cleanPhrase, true);
        } else if (eve == 1 && org == 1 && date == 0) {
            return prepareSearchedEventsToShowByEveOrg(firstResult, cleanPhrase, true);
        } else if (eve == 1 && org == 1 && date == 1) {
            return prepareSearchedEventsToShowByEveOrgDate(firstResult, cleanPhrase, true, start, end);
        } else if (eve == 0 && org == 1 && date == 1) {
            return prepareSearchedEventsToShowByOrgDate(firstResult, cleanPhrase, true, start, end);
        } else if (eve == 1 && org == 0 && date == 1) {
            return prepareSearchedEventsToShowByEveDate(firstResult, cleanPhrase, true, start, end);
        } else {
            return null;
        }
    }

    public Integer listSize(String cleanPhrase, int eve, int org, int date, LocalDateTime start, LocalDateTime end) {
        if (eve == 1 && org == 0 && date == 0) {
            return prepareSearchedEventsToShowByEve(1, cleanPhrase, false).size();
        } else if (eve == 0 && org == 1 && date == 0) {
            return prepareSearchedEventsToShowByOrg(1, cleanPhrase, false).size();
        } else if (eve == 1 && org == 1 && date == 0) {
            return prepareSearchedEventsToShowByEveOrg(1, cleanPhrase, false).size();
        } else if (eve == 1 && org == 1 && date == 1) {
            return prepareSearchedEventsToShowByEveOrgDate(1, cleanPhrase, false, start, end).size();
        } else if (eve == 0 && org == 1 && date == 1) {
            return prepareSearchedEventsToShowByOrgDate(1, cleanPhrase, false, start, end).size();
        } else if (eve == 1 && org == 0 && date == 1) {
            return prepareSearchedEventsToShowByEveDate(1, cleanPhrase, false, start, end).size();
        } else {
            return 0;
        }
    }
}
