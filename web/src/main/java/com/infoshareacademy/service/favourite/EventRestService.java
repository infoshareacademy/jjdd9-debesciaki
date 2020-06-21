package com.infoshareacademy.service.favourite;

import com.infoshareacademy.domain.entity.Attachment;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.repository.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class EventRestService {

    @Inject
    EventDao eventDao;

    @Inject
    ViewStatDao viewStatDao;

    @Inject
    AttachmentDao attachmentDao;

    @Inject
    FavouritesDao favouritesDao;

    public Response deleteEvent(Long eventId) {
        Event event = eventDao.findById(eventId).get();
        attachmentDao.delete(event.getId());
        viewStatDao.delete(event.getId());
        event.setPlace(null);
        event.setOrganizer(null);
        event.setCategory(null);
        event.setUsers(null);
        event.setAttachments(null);
        event.setTicket(null);
        eventDao.delete(event);
        return Response.status(Response.Status.OK).build();
    }

    public Response editEvent(Long eventId) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
