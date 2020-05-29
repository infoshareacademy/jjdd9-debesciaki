package com.infoshareacademy.repository.writer;

import com.infoshareacademy.domain.entity.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
