package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class TicketDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Ticket ticket) {
        entityManager.persist(ticket);
    }

    public void update(Ticket ticket) {
        entityManager.merge(ticket);
    }

    public Optional<Ticket> findByEventId(Long eventId) {
        Query query = entityManager.createNamedQuery("Ticket.findByEventId");
        query.setParameter("eventId", eventId);
        return Optional.ofNullable((Ticket) query.getSingleResult());
    }
}