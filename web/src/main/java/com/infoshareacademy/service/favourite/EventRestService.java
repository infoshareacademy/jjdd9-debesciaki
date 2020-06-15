package com.infoshareacademy.service.favourite;

import com.infoshareacademy.repository.EventDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class EventRestService {

    @Inject
    EventDao eventDao;

    public Response deleteEvent(Long eventId) {
        eventDao.delete(eventDao.findById(eventId).get());
        return Response.status(Response.Status.OK).build();
    }

    public Response editEvent(Long event) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
