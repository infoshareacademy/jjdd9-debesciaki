package com.infoshareacademy.service.event;

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
        return eventDao.searchByPhraseListEve(firstResult, cleanPhrase(phrase), isLimited);
    }

    public List<Event> findByEveDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        return eventDao.searchByPhraseListEveDate(firstResult, cleanPhrase(phrase), isLimited, start, end);
    }

    public List<Event> findByOrg(int firstResult, String phrase, Boolean isLimited) {
        return eventDao.searchByPhraseListOrg(firstResult, cleanPhrase(phrase), isLimited);
    }

    public List<Event> findByOrgDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        return eventDao.searchByPhraseListOrgDate(firstResult, cleanPhrase(phrase), isLimited, start, end);
    }

    public List<Event> findByEveOrg(int firstResult, String phrase, Boolean isLimited) {
        return eventDao.searchByPhraseListEveOrg(firstResult, cleanPhrase(phrase), isLimited);
    }

    public List<Event> findByEveOrgDate(int firstResult, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        return eventDao.searchByPhraseListEveOrgDate(firstResult, cleanPhrase(phrase), isLimited, start, end);
    }

    private String cleanPhrase(String in){
        String out = new String();
        out = in.replaceAll("%", "");
        return out;
    }

}
