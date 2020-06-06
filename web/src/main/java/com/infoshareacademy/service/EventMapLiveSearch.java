package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.livesearch.EventLS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Stateless
public class EventMapLiveSearch {
    private static final Logger STDLOG = LoggerFactory.getLogger(EventMapLiveSearch.class.getName());

    @Inject
    EventQueryRestService eventQueryRestService;

    public List<EventLS> searchThenAndMapEve(String phrase) {
        List<Event> entities = eventQueryRestService.findByEve(1, phrase, true);
        List<EventLS> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLS x = new EventLS();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        STDLOG.debug("Succes in mapping event to event live search format, searching by event name");
        return liveSearchList;
    }

    public List<EventLS> searchThenAndMapEveDate(String phrase, String start, String end) {
        LocalDateTime startDate = stringToDate(start);
        LocalDateTime endDate = stringToDate(end);
        List<Event> entities = eventQueryRestService.findByEveDate(1, phrase, true, startDate, endDate);
        List<EventLS> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLS x = new EventLS();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public List<EventLS> searchThenAndMapEveOrg(String phrase) {
        List<Event> entities = eventQueryRestService.findByEveOrg(1, phrase, true);
        List<EventLS> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLS x = new EventLS();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public List<EventLS> searchThenAndMapOrg(String phrase) {
        List<Event> entities = eventQueryRestService.findByOrg(1, phrase, true);
        List<EventLS> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLS x = new EventLS();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public LocalDateTime stringToDate(String in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(in, formatter);
    }
}
