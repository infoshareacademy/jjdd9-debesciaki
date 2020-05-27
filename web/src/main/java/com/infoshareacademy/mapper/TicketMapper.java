package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.TicketJSON;
import com.infoshareacademy.domain.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class TicketMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(TicketMapper.class.getName());

    public TicketJSON daoToJson(Ticket ticket) {
        TicketJSON jsonTicket = new TicketJSON();
        jsonTicket.setType(ticket.getType());
        jsonTicket.setStartTicket(ticket.getStartTicket());
        jsonTicket.setEndTicket(ticket.getEndTicket());
        STDLOG.info("Success in mapping dao to json");
        return jsonTicket;
    }

    public Ticket jsonToDao(Ticket ticket) {
        Ticket daoTicket = new Ticket();
        daoTicket.setType(ticket.getType());
        daoTicket.setStartTicket(ticket.getStartTicket());
        daoTicket.setEndTicket(ticket.getEndTicket());
        STDLOG.info("Success in mapping json to dao");
        return daoTicket;
    }
}
