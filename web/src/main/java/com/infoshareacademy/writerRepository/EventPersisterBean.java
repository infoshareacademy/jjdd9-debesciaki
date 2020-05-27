package com.infoshareacademy.writerRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.Organizer;
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
    @PersistenceContext
    EntityManager entityManager;

    public void persistEntityList(List<Event> list) throws IOException {
        for (Event o : list) {
            entityManager.persist(o);
        }
    }
}
