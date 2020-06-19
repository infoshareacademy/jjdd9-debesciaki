package com.infoshareacademy.service.stat;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.TicketStat;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.stat.TicketCount;
import com.infoshareacademy.domain.stat.TicketRatio;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.TicketStatDao;
import com.infoshareacademy.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class TicketStatService {

    private static final Logger STDLOG = LoggerFactory.getLogger(TicketStatService.class.getName());

    @Inject
    private TicketStatDao ticketStatDao;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    public void persistSingleTicketStat(String email, Long eventId, String ticketType) {
        STDLOG.info("Persisting of a single view stat......");
        ticketStatDao.save(joinTicketStat(email, eventId, ticketType));
    }

    public List<TicketRatio> provideTicketRatioStat() {
        List<TicketCount> reduList = ticketStatDao.findRedu();
        List<TicketCount> fullList = ticketStatDao.findFull();
        return mapCountToRatio(reduList, fullList);
    }

    private List<TicketRatio> mapCountToRatio(List<TicketCount> reduList, List<TicketCount> fullList) {
        List<TicketRatio> ratioList = new ArrayList<>();
        for (TicketCount r : reduList) {
            for (TicketCount f : fullList) {
                if (r.getEventName().equals(f.getEventName())) {

                    TicketRatio ticketRatio = new TicketRatio();
                    ticketRatio.setEventName(r.getEventName());

                    if (r.getCount() == 0 || f.getCount() == 0) {
                        ticketRatio.setRatio(0.0);
                    } else {
                        Double ratio = (double) r.getCount() / f.getCount();
                        ticketRatio.setRatio(ratio);
                    }

                    ratioList.add(ticketRatio);
                }
            }
        }
        return ratioList;
    }


    private TicketStat joinTicketStat(String email, Long eventId, String ticketType) {
        TicketStat ticketStat = new TicketStat();
        STDLOG.info("Preparation of a single ticket stat entity......");
        Optional<User> userOptional = userDao.findByEmail(email);
        if (userOptional.isPresent()) {
            ticketStat.setUser(userOptional.get());
        }
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if (eventOptional.isPresent()) {
            ticketStat.setEvent(eventOptional.get());
        }
        ticketStat.setTicketType(ticketType);
        return ticketStat;
    }
}
