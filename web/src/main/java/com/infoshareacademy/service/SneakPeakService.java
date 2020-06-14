package com.infoshareacademy.service;

import com.infoshareacademy.comparator.EventViewComparators;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.domain.view.EventView;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class SneakPeakService {
    @Inject
    UserDao userDao;

    @Inject
    EventViewService eventViewService;

    public Optional<EventView> upcomingEvent(String email) {
        Optional<User> userOpt = userDao.findByEmail(email);
        Set<EventView> events = new HashSet<>();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            for (Event event : user.getEvents()) {
                events.add(eventViewService.mapper(event));
            }
            if (events.size() > 0) {
                return events.stream().min(EventViewComparators.EventDateComparatorAsc);
            }
        }
        EventView eventView = new EventView();
        eventView.setName("NONE");
        return Optional.ofNullable(eventView);
    }
}
