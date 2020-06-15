package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventDao {
    @PersistenceContext
    private EntityManager entityManager;

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
        Query queryEventName = entityManager.createNamedQuery("Event.findByEveOrg");

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

    public List<Event> searchByPhraseListEveOrgDate(int firstElement, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        Query queryEventName = entityManager.createNamedQuery("Event.findByEveOrgDate");

        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(phrase);
        sb.append("%");

        queryEventName.setParameter("phrase", sb.toString());
        queryEventName.setParameter("start", start);
        queryEventName.setParameter("end", end);

        if (isLimited) {
            queryEventName.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        } else {
            return queryEventName.getResultList();
        }
        return queryEventName.getResultList();
    }

    public List<Event> searchByPhraseListEve(int firstElement, String phrase, Boolean isLimited) {
        Query queryEventName = entityManager.createNamedQuery("Event.findByEve");

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

    public List<Event> searchByPhraseListEveDate(int firstElement, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        Query queryEventName = entityManager.createNamedQuery("Event.findByEveDate");

        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(phrase);
        sb.append("%");

        queryEventName.setParameter("phrase", sb.toString());
        queryEventName.setParameter("start", start);
        queryEventName.setParameter("end", end);

        if (isLimited) {
            queryEventName.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        } else {
            return queryEventName.getResultList();
        }
        return queryEventName.getResultList();
    }

    public List<Event> searchByPhraseListOrg(int firstElement, String phrase, Boolean isLimited) {
        Query queryEventName = entityManager.createNamedQuery("Event.findByOrg");

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

    public List<Event> searchByPhraseListOrgDate(int firstElement, String phrase, Boolean isLimited, LocalDateTime start, LocalDateTime end) {
        Query queryEventName = entityManager.createNamedQuery("Event.findByOrgDate");

        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(phrase);
        sb.append("%");

        queryEventName.setParameter("phrase", sb.toString());
        queryEventName.setParameter("start", start);
        queryEventName.setParameter("end", end);

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
        Query query = entityManager.createNamedQuery("Event.findByApiId");
        query.setParameter("apiID", id);
        try {
            return Optional.ofNullable((Event) query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public List<Event> findFinished() {
        Query query = entityManager.createNamedQuery("Event.findFinished");
        query.setParameter("now", LocalDateTime.now());
        return query.getResultList();
    }

    public void persistEntityList(List<Event> list) throws IOException {
        for (Event o : list) {
            entityManager.persist(o);
        }
    }
}
