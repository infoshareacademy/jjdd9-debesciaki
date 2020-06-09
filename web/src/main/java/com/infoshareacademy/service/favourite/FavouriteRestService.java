package com.infoshareacademy.service.favourite;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@Stateless
public class FavouriteRestService {

    @Inject
    UserDao userDao;

    @Inject
    EventDao eventDao;

    public Optional<User> addFavourite(String email, Long eventId) {

        Optional<User> userOptional = userDao.findByEmail(email);
        Optional<Event> eventOptional = eventDao.findById(eventId);

        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Set<Event> events = user.getEvents();
            Event event = eventOptional.get();
            events.add(event);
            user.setEvents(events);
            userDao.update(user);
            return Optional.ofNullable(user);
        } else {
            return Optional.empty();
        }
    }


}

