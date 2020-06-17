package com.infoshareacademy.service.favourite;

import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class EventRestService {

    @Inject
    EventDao eventDao;

    public Response deleteEvent(Long eventId) {
        Event event = eventDao.findById(eventId).get();
        event.setPlace(null);
        event.setOrganizer(null);
        event.setCategory(null);
        event.setUsers(null);
        eventDao.delete(event);
        return Response.status(Response.Status.OK).build();
    }

    public Response editEvent(Long eventId) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
