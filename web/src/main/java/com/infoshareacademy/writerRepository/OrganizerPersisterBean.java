package com.infoshareacademy.writerRepository;

import com.infoshareacademy.domain.entity.Organizer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

@Stateless
public class OrganizerPersisterBean {
    @PersistenceContext
    EntityManager entityManager;

    public void persistEntityList(List<Organizer> list) throws IOException {
        for (Organizer o : list) {
            entityManager.persist(o);
        }
    }
}
