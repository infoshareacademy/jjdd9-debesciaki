package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Place;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class PlaceDao {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Place> findByApiId(long id) {
        Query query = entityManager.createQuery("SELECT c FROM Place c WHERE c.apiId=:apiID");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Place) query.getSingleResult());
    }

    public void persistEntityList(List<Place> list) throws IOException {
        for (Place p : list) {
            entityManager.persist(p);
        }
    }
}
