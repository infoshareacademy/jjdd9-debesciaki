package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.TicketStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class TicketStatDao {

    private static final Logger STDLOG = LoggerFactory.getLogger(TicketStatDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public void save(TicketStat ticketStat) {
        entityManager.persist(ticketStat);
        STDLOG.info("Ticket stat have been persisted id: {}", ticketStat.getId());
    }

    public void update(TicketStat ticketStat) {
        entityManager.merge(ticketStat);
        STDLOG.info("Ticket stat have been updated id: {}", ticketStat.getId());
    }

    public List<TicketStat> findAll() {
        Query query = entityManager.createNamedQuery("TicketStat.findAll");
        return query.getResultList();
    }

    public Optional<TicketStat> findById(Event event) {
        Query query = entityManager.createNamedQuery("TicketStat.findByEventId");
        query.setParameter("eventId", event.getId());
        List result = query.getResultList();
        if (!result.isEmpty()) {
            STDLOG.info("Success in searching for ticket stat");
            return Optional.ofNullable((TicketStat) result.get(0));
        } else {
            STDLOG.error("Problems during searching for ticket stat");
            return Optional.empty();
        }
    }
}
