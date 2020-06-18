package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Category;
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
    private EntityManager entityManager;

    public Optional<Place> findByApiId(long id) {
        Query query = entityManager.createNamedQuery("Place.findByApiId");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Place) query.getSingleResult());
    }

    public Optional<Place> findByName(String name) {
        Query query = entityManager.createNamedQuery("Place.findByName");
        query.setParameter("name", name);
        return Optional.ofNullable((Place) query.getSingleResult());
    }


    public void persistEntityList(List<Place> list) {
        for (Place p : list) {
            entityManager.persist(p);
        }
    }

    public Place create(String name) {
        Place place = new Place();
        place.setName(name);
        entityManager.persist(place);
        return place;
    }

}
