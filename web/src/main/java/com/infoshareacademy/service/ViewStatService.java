package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.entity.ViewStat;
import com.infoshareacademy.domain.view.stat.ViewStatView;
import com.infoshareacademy.domain.view.stat.chart.ClicksPerEvent;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.UserDao;
import com.infoshareacademy.repository.ViewStatDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class ViewStatService {

    private static final Logger STDLOG = LoggerFactory.getLogger(ViewStatService.class.getName());

    @Inject
    private ViewStatDao viewStatDao;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    public void persistSingleView(String email, Long eventId) {
        STDLOG.info("Persisting of a single view stat......");
        viewStatDao.save(joinViewStat(email, eventId));
    }

    public List<ViewStatView> provideAllViewStatsNonAggregated() {
        List<ViewStatView> statList = new ArrayList<>();
        for (ViewStat v : viewStatDao.findAll()) {
            statList.add(viewStatMapper(v));
        }
        return statList;
    }

    public List<ClicksPerEvent> provideGlobalClicksPerEvent() {
        List<ClicksPerEvent> list = viewStatDao.findGlobalClicksPerEvent();
        return viewStatDao.findGlobalClicksPerEvent();
    }

    private ViewStat joinViewStat(String email, Long eventId) {
        ViewStat viewStat = new ViewStat();
        STDLOG.info("Preparation of a single view stat entity......");
        Optional<User> userOptional = userDao.findByEmail(email);
        if (userOptional.isPresent()) {
            viewStat.setUser(userOptional.get());
        }
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if (eventOptional.isPresent()) {
            viewStat.setEvent(eventOptional.get());
        }
        viewStat.setViewDate(LocalDateTime.now());
        return viewStat;
    }

    private ViewStatView viewStatMapper(ViewStat viewStat) {
        ViewStatView viewStatView = new ViewStatView();
        viewStatView.setEventName(viewStat.getEvent().getName());
        viewStatView.setViewDate(viewStat.getViewDate());
        return viewStatView;
    }

}
