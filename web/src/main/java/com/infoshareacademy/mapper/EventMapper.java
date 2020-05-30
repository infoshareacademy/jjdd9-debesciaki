package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.AttachmentJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.entity.*;
import com.infoshareacademy.query.CategoryQuery;
import com.infoshareacademy.query.OrganizerQuery;
import com.infoshareacademy.query.PlaceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(EventMapper.class.getName());

    @Inject
    AttachmentMapper attachmentMapper;

    @Inject
    PlaceMapper placeMapper;

    @Inject
    OrganizerMapper organizerMapper;

    @Inject
    CategoryMapper categoryMapper;

    @Inject
    TicketMapper ticketMapper;

    @Inject
    UrlMapper urlMapper;

    @Inject
    CategoryQuery categoryQuery;

    @Inject
    PlaceQuery placeQuery;

    @Inject
    OrganizerQuery organizerQuery;

    public EventJSON daoToJson(Event event) {
        EventJSON jsonEvent = new EventJSON();
        jsonEvent.setId(event.getApiId());
        jsonEvent.setName(event.getName());
        jsonEvent.setEndDate(event.getEndDate());
        jsonEvent.setStartDate(event.getStartDate());
        jsonEvent.setActive(event.getActive());
        jsonEvent.setDescLong(event.getDescLong());
        jsonEvent.setDescShort(event.getDescShort());
        List<AttachmentJSON> attachments = new ArrayList<>();
        for (Attachment a : event.getAttachments()) {
            attachments.add(attachmentMapper.daoToJson(a));
        }
        jsonEvent.setAttachments(attachments);
        jsonEvent.setPlace(placeMapper.daoToJson(event.getPlace()));
        jsonEvent.setOrganizer(organizerMapper.daoToJson(event.getOrganizer()));
        jsonEvent.setCategoryId(categoryMapper.daoToJson(event.getCategory()).getId());
        STDLOG.info("Success in mapping dao to json");
        return jsonEvent;
    }

    public Event jsonToDao(EventJSON event) {
        Event daoEvent = new Event();
        daoEvent.setApiId(event.getId());
        daoEvent.setName(event.getName());
        daoEvent.setEndDate(event.getEndDate());
        daoEvent.setStartDate(event.getStartDate());
        daoEvent.setActive(event.getActive());
        daoEvent.setDescLong(event.getDescLong());
        daoEvent.setDescShort(event.getDescShort());
        List<Attachment> attachments = new ArrayList<>();
        for (AttachmentJSON a : event.getAttachments()) {
            Attachment attachment = attachmentMapper.jsonToDao(a);
            attachment.setEvent(daoEvent);
            attachments.add(attachment);
        }
        daoEvent.setAttachments(attachments);

        daoEvent.setTicket(ticketMapper.jsonToDao(event.getTickets()));

        Place place = placeQuery.findByApiId(event.getPlace().getId()).get();
        daoEvent.setPlace(place);

        Organizer organizer = organizerQuery.findByApiId(event.getOrganizer().getId()).get();
        daoEvent.setOrganizer(organizer);
        Category category = new Category();
        if (!categoryQuery.findById(event.getCategoryId()).isEmpty()) {
            category = categoryQuery.findById(event.getCategoryId()).get();
        } else {
            category = null;
        }
        daoEvent.setCategory(category);
        daoEvent.setUrls(urlMapper.jsonToDao(event.getUrls()));


        STDLOG.info("Success in mapping json to dao");
        return daoEvent;
    }
}
