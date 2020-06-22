package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.mail.FavouriteDeletedMail;
import com.infoshareacademy.mail.MailService;
import com.infoshareacademy.mail.ReservationDeletedMail;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.UserDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@Stateless
public class FavouritesService {
    private final String reason = "Wydarzenie odwołano.";

    @Inject
    private EventDao eventDao;

    @Inject
    private UserDao userDao;

    @Inject
    private MailService mailService;

    public void deleteEventFromFavouritesUsersLists(Long eventId) {
        Optional<Event> eventOptional = eventDao.findById(eventId);
        if(eventOptional.isPresent()){
            Event event = eventOptional.get();
            for(User user : event.getUsers()) {
                Set<Event> events = user.getEvents();
                events.remove(event);
                user.setEvents(events);
                mailService.sendEmail(new FavouriteDeletedMail(event.getName(),reason),user.getMail());
                userDao.update(user);
            }
        }
    }
}
