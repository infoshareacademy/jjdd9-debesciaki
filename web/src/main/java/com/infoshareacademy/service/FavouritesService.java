package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@Stateless
public class FavouritesService {

    @Inject
    EventDao eventDao;

    @Inject
    UserDao userDao;

    public void delete(Long eventId) {
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if(eventOptional.isPresent()){
            Event event = eventOptional.get();
            for(User user : event.getUsers()) {
                Set<Event> events = user.getEvents();
                events.remove(event);
                user.setEvents(events);
                userDao.update(user);
            }
        }
    }
}
