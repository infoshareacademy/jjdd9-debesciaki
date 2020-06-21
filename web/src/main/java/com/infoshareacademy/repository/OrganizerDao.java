package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Organizer;
import com.infoshareacademy.domain.entity.Urls;

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
    private EntityManager entityManager;

    public Optional<Organizer> findByApiId(long id) {
        Query query = entityManager.createNamedQuery("Organizer.findByApiId");
        query.setParameter("apiID", id);
        return Optional.ofNullable((Organizer) query.getSingleResult());
    }

    public Optional<Organizer> findByEventId(Long eventId) {
        Query query = entityManager.createNamedQuery("Urls.findByEventId");
        query.setParameter("eventId", eventId);
        return Optional.ofNullable((Organizer) query.getSingleResult());
    }

    public Optional<Organizer> findByDesignation(String name) {
        Query query = entityManager.createNamedQuery("Organizer.findByDesignation");
        query.setParameter("designation", name);
        return Optional.ofNullable((Organizer) query.getResultList().get(0));
    }

    private final static int MAX_RESULTS = 20;

    public List<Organizer> organizersListWithLimit(int firstElement) {
        Query query = entityManager.createNamedQuery("Organizer.findAll");
        query.setFirstResult(firstElement).setMaxResults(MAX_RESULTS);
        return query.getResultList();
    }

    public List<Organizer> activeOrganizersListWithLimit(int firstElement) {
        Query query = entityManager.createNamedQuery("Organizer.findActiveOrderByEventCount");
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

    public Integer sizeActiveList() {
        Query query = entityManager.createNamedQuery("Organizer.findActiveOrderByEventCount");
        return query.getResultList().size();
    }

    public void persistEntityList(List<Organizer> list) throws IOException {
        for (Organizer o : list) {
            entityManager.persist(o);
        }
    }

    public Organizer create(String designation) {
        Organizer organizer = new Organizer();
        organizer.setDesignation(designation);
        entityManager.persist(organizer);
        return organizer;
    }
}
