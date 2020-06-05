package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.livesearch.EventLS;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventMapLiveSearch {
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
}
