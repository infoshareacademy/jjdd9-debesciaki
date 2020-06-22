package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Address;
import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.Place;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Stateless
public class PlaceDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    AddressDao addressDao;

    public Optional<Place> findByApiId(long id) {
        Query query = entityManager.createNamedQuery("Place.findByApiId");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Place) query.getSingleResult());
    }

    public Place findByNameAndSubname(String name, String subname) {
        Query query = entityManager.createNamedQuery("Place.findByNameAndSubname");
        query.setParameter("name", name);
        query.setParameter("subname", subname);

        if (query.getResultList().isEmpty()) {
            return create(name, subname);
        } else {
            return (Place) query.getResultList().get(0);
        }

    }

    public Optional<Place> findByEventId(Long id) {
        Query query = entityManager.createNamedQuery("Place.findByEventId");
        query.setParameter("eventId", id);
        return Optional.ofNullable((Place) query.getResultList().get(0));
    }

    public List<Place> allPlacesList() {
        Query query = entityManager.createNamedQuery("Place.findAll");
        return query.getResultList();
    }

    public void persistEntityList(List<Place> list) {
        for (Place p : list) {
            entityManager.persist(p);
        }
    }

    public Place create(String name, String subname) {
        Place place = new Place();
        place.setName(name);
        place.setSubname(subname);
        entityManager.persist(place);
        return place;
    }

    public Place create(Place place) {
        entityManager.persist(place);
        return place;
    }

    public void update(Place place) {
        entityManager.merge(place);
    }

}
