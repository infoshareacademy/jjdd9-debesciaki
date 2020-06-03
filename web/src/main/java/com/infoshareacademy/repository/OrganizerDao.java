package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Organizer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class OrganizerDao {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<Organizer> findByApiId(long id) {
        Query query = entityManager.createQuery("SELECT c FROM Organizer c WHERE c.apiId=:apiID");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Organizer) query.getSingleResult());
    }

    private final static int MAX_RESULTS = 20;

    public List<Organizer> organizersListWithLimit(int firstElement) {
        Query query = entityManager.createNamedQuery("Organizer.findAll");
        query.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        return query.getResultList();
    }

    public List<Organizer> allOrganizersList () {
        Query query = entityManager.createNamedQuery("Organizer.findAll");
        return query.getResultList();
    }

    public Integer sizeList() {
        Query query = entityManager.createNamedQuery("Organizer.findAll");
        return query.getResultList().size();
    }

    public void persistEntityList(List<Organizer> list) throws IOException {
        for (Organizer o : list) {
            entityManager.persist(o);
        }
    }
}
