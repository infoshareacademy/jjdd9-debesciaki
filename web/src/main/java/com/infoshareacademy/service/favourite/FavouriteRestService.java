package com.infoshareacademy.service.favourite;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.UserDao;
import com.infoshareacademy.service.MailService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Stateless
public class FavouriteRestService {

    @Inject
    UserDao userDao;

    @Inject
    EventDao eventDao;

    @Inject
    MailService mailService;

    public Response addFavourite(String email, Long eventId) {
        Optional<User> userOptional = userDao.findByEmail(email);
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Set<Event> events = user.getEvents();
            Event event = eventOptional.get();
            events.add(event);
            user.setEvents(events);
            userDao.update(user);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response removeFavourite(String email, Long eventId) {
        Optional<User> userOptional = userDao.findByEmail(email);
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Set<Event> events = user.getEvents();
            Event event = eventOptional.get();
            events.remove(event);
            user.setEvents(events);
            userDao.update(user);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response isFavourite(String email, Long eventId) {
        Optional<User> userOptional = userDao.findByEmail(email);
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Set<Event> events = user.getEvents();
            Event event = eventOptional.get();
            if (events.contains(event)) {
                return Response.status(Response.Status.OK)
                        .entity(Boolean.TRUE)
                        .build();
            } else {
                return Response.status(Response.Status.OK)
                        .entity(Boolean.FALSE)
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

