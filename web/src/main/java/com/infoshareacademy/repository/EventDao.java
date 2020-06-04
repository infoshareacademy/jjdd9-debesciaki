package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventDao {
    @PersistenceContext
    EntityManager entityManager;

    private final static int MAX_RESULTS = 20;

    public List<Event> eventListWithLimit(int firstElement) {
        Query query = entityManager.createNamedQuery("Event.findAll");
        query.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        return query.getResultList();
    }

    public List<Event> allEventsList() {
        Query query = entityManager.createNamedQuery("Event.findAll");
        return query.getResultList();
    }

    public List<Event> searchByPhraseListEveOrg(int firstElement, String phrase, Boolean isLimited) {
        Query queryEventName = entityManager
                .createQuery("SELECT c FROM Event c WHERE (c.name LIKE :phrase) OR (c.organizer.designation LIKE :phrase) ");

        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(phrase);
        sb.append("%");

        queryEventName.setParameter("phrase", sb.toString());

        if (isLimited) {
            queryEventName.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        } else {
            return queryEventName.getResultList();
        }
        return queryEventName.getResultList();
    }

    public List<Event> searchByPhraseListEve(int firstElement, String phrase, Boolean isLimited) {
        Query queryEventName = entityManager
                .createQuery("SELECT c FROM Event c WHERE c.name LIKE :phrase");

        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(phrase);
        sb.append("%");

        queryEventName.setParameter("phrase", sb.toString());

        if (isLimited) {
            queryEventName.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        } else {
            return queryEventName.getResultList();
        }
        return queryEventName.getResultList();
    }

    public List<Event> searchByPhraseListOrg(int firstElement, String phrase, Boolean isLimited) {
        Query queryEventName = entityManager
                .createQuery("SELECT c FROM Event c WHERE c.organizer.designation LIKE :phrase");

        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(phrase);
        sb.append("%");

        queryEventName.setParameter("phrase", sb.toString());

        if (isLimited) {
            queryEventName.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        } else {
            return queryEventName.getResultList();
        }
        return queryEventName.getResultList();
    }

    public Integer getAllEventsCount() {
        Query query = entityManager.createNamedQuery("Event.findAll");
        return query.getResultList().size();
    }

    public Optional<Event> findById(long id) {
        try {
            return Optional.ofNullable(entityManager.find(Event.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Event> findByApiId(long id) {
        Query query = entityManager.createQuery("SELECT c FROM Event c WHERE c.apiId=:apiID");
        query.setParameter("apiID", id);
        try {
            return Optional.ofNullable((Event) query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public void persistEntityList(List<Event> list) throws IOException {
        for (Event o : list) {
            entityManager.persist(o);
        }
    }
}
