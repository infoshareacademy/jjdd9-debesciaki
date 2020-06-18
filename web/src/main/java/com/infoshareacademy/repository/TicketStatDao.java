package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.TicketStat;
import com.infoshareacademy.domain.entity.ViewStat;

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

    public List<ViewStat> findAll() {
        Query query = entityManager.createNamedQuery("TicketStat.findAll");
        return query.getResultList();
    }
}
