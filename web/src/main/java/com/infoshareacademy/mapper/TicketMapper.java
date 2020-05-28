package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.TicketJSON;
import com.infoshareacademy.domain.entity.Ticket;

import javax.ejb.Stateless;

@Stateless
public class TicketMapper {

    public TicketJSON daoToJson(Ticket ticket) {
        TicketJSON jsonTicket = new TicketJSON();
        jsonTicket.setType(ticket.getType());
        jsonTicket.setStartTicket(ticket.getStartTicket());
        jsonTicket.setEndTicket(ticket.getEndTicket());
        return jsonTicket;
    }

    public Ticket jsonToDao(Ticket ticket) {
        Ticket daoTicket = new Ticket();
        daoTicket.setType(ticket.getType());
        daoTicket.setStartTicket(ticket.getStartTicket());
        daoTicket.setEndTicket(ticket.getEndTicket());
        return daoTicket;
    }
}
