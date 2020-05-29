package com.infoshareacademy.repository.writer;

import com.infoshareacademy.domain.entity.Place;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

@Stateless
public class PlacePersisterBean {
    @PersistenceContext
    EntityManager entityManager;

    public void persistEntityList(List<Place> list) throws IOException {
        for (Place p : list) {
            entityManager.merge(p);
        }
    }
}
