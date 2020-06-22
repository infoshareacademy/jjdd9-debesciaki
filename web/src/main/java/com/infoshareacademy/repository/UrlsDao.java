package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Urls;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class UrlsDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Urls urls) {
        entityManager.persist(urls);
    }

    public void update(Urls urls) {
        entityManager.merge(urls);
    }

    public Optional<Urls> findByEventId(Long eventId) {
        Query query = entityManager.createNamedQuery("Urls.findByEventId");
        query.setParameter("eventId", eventId);
        return Optional.ofNullable((Urls) query.getResultList().get(0));
    }
}
