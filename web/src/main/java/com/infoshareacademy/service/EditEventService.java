package com.infoshareacademy.service;

import com.infoshareacademy.domain.ReqMapDTO;
import com.infoshareacademy.domain.entity.*;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.UrlsDao;
import com.infoshareacademy.util.StringToLocalDateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Stateless
public class EditEventService {
    @Inject
    EventDao eventDao;

    @Inject
    UrlsDao urlsDao;

    public void updateEvent(Event event){
        eventDao.update(event);
    }

    public Event combineEvent(ReqMapDTO reqMapDTO) {

        Optional<Event> eventOptional = eventDao.findById(reqMapDTO.getId());

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setName(reqMapDTO.getName());
            event.setDescLong(reqMapDTO.getDescLong());

            event.setStartDate(reqMapDTO.getStartDate());
            event.setEndDate(reqMapDTO.getEndDate());

            urlsDao
            urls.setWww(req.getParameter("url"));

            Ticket ticket = event.getTicket();
            ticket.setType(req.getParameter("typeOfTicket"));
            if (req.getParameter("typeOfTicket").equals("tickets")) {
                ticket.setStartTicket(Integer.valueOf(req.getParameter("reducedTicket")));
                ticket.setEndTicket(Integer.valueOf(req.getParameter("normalTicket")));
            }

            event.setTicketAmount((long) Integer.valueOf(req.getParameter("numberOfTickets")));

            Organizer organizer = event.getOrganizer();
            organizer.setDesignation("organizersDesignation");


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
