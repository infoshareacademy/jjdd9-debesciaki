package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class EventQueryRestService {
    @Inject
    private EventDao eventDao;

    public List<Event> findByEve(int firstResult, String phrase, Boolean isLimited) {
        return eventDao.searchByPhraseListEve(firstResult, phrase, isLimited);
    }

    public List<Event> findByEveDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        return eventDao.searchByPhraseListEveDate(firstResult, phrase, isLimited, start, end);
    }

    public List<Event> findByOrg(int firstResult, String phrase, Boolean isLimited) {
        return eventDao.searchByPhraseListOrg(firstResult, phrase, isLimited);
    }

    public List<Event> findByEveOrg(int firstResult, String phrase, Boolean isLimited) {
        return eventDao.searchByPhraseListEveOrg(firstResult, phrase, isLimited);
    }

}
