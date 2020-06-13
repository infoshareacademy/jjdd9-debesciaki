package com.infoshareacademy.service.event;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.livesearch.EventLiveSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventMapLiveSearch {
    private static final Logger STDLOG = LoggerFactory.getLogger(EventMapLiveSearch.class.getName());

    @Inject
    EventQueryRestService eventQueryRestService;

    public List<EventLiveSearchDTO> searchThenAndMapEve(String phrase) {
        List<Event> entities = eventQueryRestService.findByEve(1, phrase, true);
        List<EventLiveSearchDTO> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLiveSearchDTO eventLiveSearchDTO = new EventLiveSearchDTO();
            eventLiveSearchDTO.setApiId(e.getApiId());
            eventLiveSearchDTO.setId(e.getId());
            eventLiveSearchDTO.setName(e.getName());
            liveSearchList.add(eventLiveSearchDTO);
        }
        STDLOG.debug("Succes in mapping event to event live search format, searching by event name");
        return liveSearchList;
    }

    public List<EventLiveSearchDTO> searchThenAndMapEveDate(String phrase, String start, String end) {
        LocalDateTime startDate = stringToDate(start);
        LocalDateTime endDate = stringToDate(end);
        List<Event> entities = eventQueryRestService.findByEveDate(1, phrase, true, startDate, endDate);
        List<EventLiveSearchDTO> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLiveSearchDTO x = new EventLiveSearchDTO();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public List<EventLiveSearchDTO> searchThenAndMapEveOrg(String phrase) {
        List<Event> entities = eventQueryRestService.findByEveOrg(1, phrase, true);
        List<EventLiveSearchDTO> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLiveSearchDTO x = new EventLiveSearchDTO();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public List<EventLiveSearchDTO> searchThenAndMapOrgDate(String phrase, String start, String end) {
        LocalDateTime startDate = stringToDate(start);
        LocalDateTime endDate = stringToDate(end);
        List<Event> entities = eventQueryRestService.findByEveDate(1, phrase, true, startDate, endDate);
        List<EventLiveSearchDTO> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLiveSearchDTO x = new EventLiveSearchDTO();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public List<EventLiveSearchDTO> searchThenAndMapOrg(String phrase) {
        List<Event> entities = eventQueryRestService.findByOrg(1, phrase, true);
        List<EventLiveSearchDTO> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLiveSearchDTO x = new EventLiveSearchDTO();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    public List<EventLiveSearchDTO> searchThenAndMapEveOrgDate(String phrase, String start, String end) {
        LocalDateTime startDate = stringToDate(start);
        LocalDateTime endDate = stringToDate(end);
        List<Event> entities = eventQueryRestService.findByEveOrgDate(1, phrase, true, startDate, endDate);
        List<EventLiveSearchDTO> liveSearchList = new ArrayList<>();
        for (Event e : entities) {
            EventLiveSearchDTO x = new EventLiveSearchDTO();
            x.setApiId(e.getApiId());
            x.setId(e.getId());
            x.setName(e.getName());
            liveSearchList.add(x);
        }
        return liveSearchList;
    }

    private LocalDateTime stringToDate(String in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(in, formatter);
    }
}
