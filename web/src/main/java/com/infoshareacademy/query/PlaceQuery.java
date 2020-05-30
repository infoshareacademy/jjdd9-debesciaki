package com.infoshareacademy.query;

import com.infoshareacademy.domain.entity.Place;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class PlaceQuery {
    @PersistenceContext
    EntityManager entityManager;


    public Optional<Place> findByApiId(long id) {
        Query query = entityManager.createQuery("SELECT c FROM Place c WHERE c.apiId=:apiID");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Place) query.getSingleResult());
    }
}
