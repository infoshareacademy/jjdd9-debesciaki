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

    @Inject
    PlaceDao placeDao;

    public void updateEvent(ReqMapEventDTO reqMapEventDTO) {
        Event event = combineEvent(reqMapEventDTO);
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

            Optional<Organizer> optionalOrganizer = organizerDao.findByDesignation(reqMapEventDTO.getOrganizerDesignation());
            Organizer organizer = optionalOrganizer.get();
            organizer.setDesignation(reqMapEventDTO.getOrganizerDesignation());
            event.setOrganizer(organizer);

            Optional<Category> optionalCategory = categoryDao.findByEventId(reqMapEventDTO.getId());
            Category category = optionalCategory.get();
            category.setName(reqMapEventDTO.getCategoryName());
            event.setCategory(category);

            Optional<Place> optionalPlace = placeDao.findByEventId(reqMapEventDTO.getId());
            Place place = optionalPlace.get();
            Address address = place.getAddress();
            place.setName(reqMapEventDTO.getPlaceName());
            place.setSubname(reqMapEventDTO.getPlaceSubname());
            address.setStreet(reqMapEventDTO.getStreet());
            address.setCity(reqMapEventDTO.getCity());
            address.setZipcode(reqMapEventDTO.getZipCode());
            place.setAddress(address);
            event.setPlace(place);

            return event;
        }

        return eventOptional.get();
    }
}
