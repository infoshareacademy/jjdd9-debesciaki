package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.TicketStat;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class TicketStatDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(TicketStat ticketStat) {
        entityManager.persist(ticketStat);
    }

    public void update(TicketStat ticketStat) {
        entityManager.merge(ticketStat);
    }

    public List<TicketStat> findAll() {
        Query query = entityManager.createNamedQuery("TicketStat.findAll");
        return query.getResultList();
    }

    public Optional<TicketStat> findById(Event event) {
        Query query = entityManager.createNamedQuery("TicketStat.findByEventId");
        query.setParameter("event", event);
        return Optional.ofNullable((TicketStat) query.getSingleResult());
    }
}
