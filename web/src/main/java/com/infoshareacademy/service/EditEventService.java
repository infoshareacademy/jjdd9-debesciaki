package com.infoshareacademy.service;

import com.infoshareacademy.domain.ReqMapEventDTO;
import com.infoshareacademy.domain.entity.*;
import com.infoshareacademy.repository.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class EditEventService {
    @Inject
    EventDao eventDao;

    @Inject
    UrlsDao urlsDao;

    @Inject
    TicketDao ticketDao;

    @Inject
    OrganizerDao organizerDao;

    @Inject
    CategoryDao categoryDao;

    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    public Event combineEvent(ReqMapEventDTO reqMapEventDTO) {

        Optional<Event> eventOptional = eventDao.findById(reqMapEventDTO.getId());

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setName(reqMapEventDTO.getName());
            event.setDescLong(reqMapEventDTO.getDescLong());

            event.setStartDate(reqMapEventDTO.getStartDate());
            event.setEndDate(reqMapEventDTO.getEndDate());

            Optional<Urls> optionalUrls = urlsDao.findByEventId(reqMapEventDTO.getId());
            Urls urls = optionalUrls.get();
            urls.setWww(reqMapEventDTO.getUrl());
            event.setUrls(urls);

            Optional<Ticket> optionalTicket = ticketDao.findByEventId(reqMapEventDTO.getId());
            Ticket ticket = optionalTicket.get();

            ticket.setType(reqMapEventDTO.getTypeOfTicket());
            if (ticket.getType().equals("tickets")) {
                ticket.setStartTicket(Integer.valueOf(reqMapEventDTO.getReducedTicket()));
                ticket.setEndTicket(Integer.valueOf(reqMapEventDTO.getNormalTicket()));
            }

            event.setTicket(ticket);

            event.setTicketAmount(reqMapEventDTO.getTicketAmount());

            Optional<Organizer> optionalOrganizer = organizerDao.findByEventId(reqMapEventDTO.getId());
            Organizer organizer = optionalOrganizer.get();
            organizer.setDesignation(reqMapEventDTO.getOrganizerDesignation());
            event.setOrganizer(organizer);

            Optional<Category> optionalCategory = categoryDao.findByEventId(reqMapEventDTO.getId());
            Category category = optionalCategory.get();
            category.setName(reqMapEventDTO.getCategoryName());
            event.setCategory(category);


            Category category = event.getCategory();
            category.setName(req.getParameter("category"));

            Place place = event.getPlace();
            place.setName(req.getParameter("placeName"));
            place.setSubname(req.getParameter("placeSubname"));

            Address address = place.getAddress();
            address.setCity(req.getParameter("city"));
            address.setStreet(req.getParameter("street"));
            address.setZipcode(req.getParameter("zipCode"));
            place.setAddress(address);

            event.setCategory(category);
            event.setOrganizer(organizer);
            event.setPlace(place);

            return event;
        }

        return eventOptional.get();
    }
}
