package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.TicketJSON;
import com.infoshareacademy.domain.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.Random;

@Stateless
public class TicketMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(TicketMapper.class.getName());

    private Random random = new Random();
    private final static int MIN = 10;
    private final static int MAX = 150;
    private final static int MIN_PRICE = 20;
    private final static int MAX_PRICE = 50;


    public TicketJSON daoToJson(Ticket ticket) {
        TicketJSON jsonTicket = new TicketJSON();
        jsonTicket.setType(ticket.getType());
        jsonTicket.setStartTicket(ticket.getStartTicket());
        jsonTicket.setEndTicket(ticket.getEndTicket());
        STDLOG.info("Success in mapping dao to json");
        return jsonTicket;
    }

    public Ticket jsonToDao(TicketJSON ticket) {
        Ticket daoTicket = new Ticket();

        if (ticket.getType().equals("free")) {
            daoTicket.setType("free");
            generateNumberOfTickets(daoTicket);
        } else {
            daoTicket.setType("tickets");
            generateNumberOfTickets(daoTicket);
            daoTicket.setEndTicket(random.nextInt((MAX_PRICE - MIN_PRICE) + 1) + MIN_PRICE);
            daoTicket.setStartTicket(daoTicket.getEndTicket()/2);
        }
        STDLOG.info("Success in mapping json to dao");
        return daoTicket;
    }

    private void generateNumberOfTickets(Ticket daoTicket) {
        daoTicket.setNumberOfTickets(random.nextInt((MAX - MIN) + 1) + MIN);
    }
}
