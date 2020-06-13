package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TicketDao {
    @PersistenceContext
    EntityManager entityManager;

    public Ticket save(String typeOfTicket) {
        Ticket ticket = new Ticket();
        ticket.setType(typeOfTicket);
        entityManager.persist(ticket);
        return ticket;
    }

    public Ticket save(String typeOfTicket, Integer reduceTicket, Integer normalTicket) {
        Ticket ticket = new Ticket();
        ticket.setType(typeOfTicket);
        ticket.setStartTicket(reduceTicket);
        ticket.setEndTicket(normalTicket);
        entityManager.persist(ticket);
        return ticket;
    }
}