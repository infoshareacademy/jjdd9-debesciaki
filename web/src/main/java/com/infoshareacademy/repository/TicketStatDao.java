package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.TicketStat;
import com.infoshareacademy.domain.stat.TicketCount;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TicketStatDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(TicketStat ticketStat) {
        entityManager.persist(ticketStat);
    }

    public List<TicketCount> findFull() {
        Query query = entityManager.createNamedQuery("TicketStat.findFull");
        return query.getResultList();
    }

    public List<TicketCount> findRedu() {
        Query query = entityManager.createNamedQuery("TicketStat.findRedu");
        return query.getResultList();
    }
}
