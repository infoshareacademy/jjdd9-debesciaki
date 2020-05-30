package com.infoshareacademy.query;

import com.infoshareacademy.domain.entity.Organizer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class OrganizerQuery {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Organizer> findByApiId(long id) {
        Query query = entityManager.createQuery("SELECT c FROM Organizer c WHERE c.apiId=:apiID");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Organizer) query.getSingleResult());
    }
}
