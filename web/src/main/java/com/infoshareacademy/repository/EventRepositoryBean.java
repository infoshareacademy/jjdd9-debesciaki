package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class EventRepositoryBean {
    @PersistenceContext
    EntityManager entityManager;

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
}
