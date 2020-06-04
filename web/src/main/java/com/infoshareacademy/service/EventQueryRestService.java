package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventQueryRestService {
    @Inject
    private EventDao eventDao;
    
    public List<Event> findByEve(int firstResult, String phrase, Boolean isLimited){
        return eventDao.searchByPhraseListEve(firstResult, phrase, isLimited);
    }

    public List<Event> findByOrg(int firstResult, String phrase, Boolean isLimited){
        return eventDao.searchByPhraseListOrg(firstResult, phrase, isLimited);
    }

    public List<Event> findByEveOrg(int firstResult, String phrase, Boolean isLimited){
        return eventDao.searchByPhraseListEveOrg(firstResult, phrase, isLimited);
    }

}
