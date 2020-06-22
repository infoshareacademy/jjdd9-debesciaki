package com.infoshareacademy.service.favourite;

import com.infoshareacademy.domain.entity.*;
import com.infoshareacademy.repository.*;
import com.infoshareacademy.service.FavouritesService;
import com.infoshareacademy.service.ReservationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventRestService {
    private final String reason = "Wydarzenie odwołano.";

    @Inject
    private EventDao eventDao;

    @Inject
    private ViewStatDao viewStatDao;

    @Inject
    private TicketStatDao ticketStatDao;

    @Inject
    private AttachmentDao attachmentDao;

    @Inject
    private FavouritesService favouritesService;

    @Inject
    private ReservationService reservationService;

    @Inject
    private ReservationDao reservationDao;

    public Response deleteEvent(Long eventId) {
        Event event = eventDao.findById(eventId).get();
        removeAttachments(eventId);
        removeViewStats(eventId);
        removeTicketStats(eventId);
        favouritesService.deleteEventFromFavouritesUsersLists(eventId);
        removeReservationsForEvent(eventId);
        event.setPlace(null);
        event.setOrganizer(null);
        event.setCategory(null);
        event.setUsers(null);
        event.setAttachments(null);
        event.setTicket(null);
        eventDao.delete(event);
        return Response.status(Response.Status.OK).build();
    }

    private void removeReservationsForEvent(Long eventId) {
        List<Reservation> reservationList = reservationDao.findByEventId(eventId);
        for (Reservation reservation : reservationList) {
            reservationService.delete(reservation, reason);
        }
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

    private void removeTicketStats(Long eventId) {
        Optional<TicketStat> optionalTicketStat = ticketStatDao.findById(eventId);
        if (optionalTicketStat.isPresent()) {
            TicketStat ticketStat = optionalTicketStat.get();
            ticketStat.setEvent(null);
            ticketStatDao.delete(ticketStat);
        }
    }

}
