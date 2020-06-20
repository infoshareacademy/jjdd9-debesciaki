package com.infoshareacademy.service.stat;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.TicketStat;
import com.infoshareacademy.domain.entity.User;
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
import java.util.Optional;

@Stateless
public class TicketStatService {

    private static final Logger STDLOG = LoggerFactory.getLogger(TicketStatService.class.getName());

    @Inject
    private TicketStatDao ticketStatDao;

    @Inject
    private EventDao eventDao;

    public void updateSingleTicketStat(Long eventId, Boolean isFull) {
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if (eventOptional.isPresent()) {

            Optional<TicketStat> ticketStatOptional = ticketStatDao.findById(eventOptional.get());

            if (ticketStatOptional.isPresent()) {

                TicketStat ticketStat = ticketStatOptional.get();

                if (isFull) {
                    ticketStat.setFullCount(ticketStat.getFullCount() + 1L);
                } else {
                    ticketStat.setReduCount(ticketStat.getReduCount() + 1L);
                }

                ticketStatDao.update(ticketStat);
                STDLOG.info("Success in updating ticket statistics");
                return;

            } else {

                TicketStat ticketStat = new TicketStat();
                ticketStat.setEvent(eventOptional.get());

                if (isFull) {
                    ticketStat.setFullCount(1L);
                    ticketStat.setReduCount(0L);
                } else {
                    ticketStat.setFullCount(0L);
                    ticketStat.setReduCount(1L);
                }
                ticketStatDao.save(ticketStat);
                STDLOG.info("Success in saving ticket in statistics");
                return;
            }
        }
        STDLOG.info("Fail in saving ticket in statistics");
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



