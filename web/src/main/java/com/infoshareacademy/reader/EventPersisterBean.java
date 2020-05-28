package com.infoshareacademy.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.mapper.EventMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class EventPersisterBean {
    @Inject
    EventMapper eventMapper;

    @PersistenceContext
    EntityManager entityManager;

    public void fromJsonFile(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<EventJSON> eventList = mapper.readValue(file, new TypeReference<List<EventJSON>>() {
        });
        for (EventJSON e : eventList) {
            entityManager.persist(eventMapper.jsonToDao(e));
        }
    }
}
