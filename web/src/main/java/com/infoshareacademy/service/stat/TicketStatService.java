package com.infoshareacademy.service.stat;

import com.infoshareacademy.domain.entity.TicketStat;
import com.infoshareacademy.domain.stat.TicketRatio;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.TicketStatDao;
import com.infoshareacademy.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TicketStatService {

    private static final Logger STDLOG = LoggerFactory.getLogger(TicketStatService.class.getName());

    @Inject
    private TicketStatDao ticketStatDao;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    public void persistSingleTicketStat() {

    }

    public List<TicketRatio> findAll() {
        List<TicketRatio> list = new ArrayList<>();
        for (TicketStat ticketStat : ticketStatDao.findAll()) {
            list.add(mapToRatio(ticketStat));
        }
        return list;
    }

    private TicketRatio mapToRatio(TicketStat ticketStat) {
        return new TicketRatio(ticketStat.getEvent().getName(),
                ticketStat.getReduCount(),
                ticketStat.getFullCount());
    }

}



