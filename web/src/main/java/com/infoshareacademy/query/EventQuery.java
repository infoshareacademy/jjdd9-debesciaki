package com.infoshareacademy.query;


import com.infoshareacademy.domain.entity.Event;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EventQuery {

    @PersistenceContext
    private EntityManager entityManager;

    private final static int MAX_RESULTS = 20;

    public List<Event> eventList(int firstElement) {
        Query query = entityManager.createNamedQuery("Event.findAll");
        query.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        return query.getResultList();
    }

    public Integer sizeList() {
        Query query = entityManager.createNamedQuery("Event.findAll");
        return query.getResultList().size();
    }

}
