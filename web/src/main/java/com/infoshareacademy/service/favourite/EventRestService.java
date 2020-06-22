package com.infoshareacademy.service.favourite;

import com.infoshareacademy.domain.entity.Attachment;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.ViewStat;
import com.infoshareacademy.repository.AttachmentDao;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.ViewStatDao;
import com.infoshareacademy.service.FavouritesService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class EventRestService {

    @Inject
    EventDao eventDao;

    @Inject
    ViewStatDao viewStatDao;

    @Inject
    AttachmentDao attachmentDao;

    @Inject
    FavouritesService favouritesService;

    public Response deleteEvent(Long eventId) {
        Event event = eventDao.findById(eventId).get();
        removeAttachments(event.getId());
        removeViewStats(event.getId());
        favouritesService.deleteEventFromFavouritesUsersLists(eventId);
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

    private void removeAttachments(Long eventId) {
        List<Attachment> attachments = eventDao.findById(eventId).get().getAttachments();
        for (Attachment a : attachments) {
            a.setEvent(null);
            attachmentDao.delete(a);
        }
    }

    private void removeViewStats(Long eventId) {
        List<ViewStat> viewStats = viewStatDao.findAll();
        for (ViewStat el : viewStats) {
            if (el.getEvent().getId().equals(eventId)) {
                el.setEvent(null);
                el.setUser(null);
                el.setViewDate(null);
                viewStatDao.delete(el);
            }
        }
    }

}
