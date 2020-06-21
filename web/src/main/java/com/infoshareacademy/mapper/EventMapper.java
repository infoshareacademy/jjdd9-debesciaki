package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.AttachmentJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.entity.*;
import com.infoshareacademy.repository.CategoryDao;
import com.infoshareacademy.repository.OrganizerDao;
import com.infoshareacademy.repository.PlaceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Stateless
public class EventMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(EventMapper.class.getName());

    private Random random = new Random();
    private final static int MIN = 10;
    private final static int MAX = 150;

    @Inject
    private AttachmentMapper attachmentMapper;

    @Inject
    private TicketMapper ticketMapper;

    @Inject
    private UrlMapper urlMapper;

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private PlaceDao placeDao;

    @Inject
    private OrganizerDao organizerDao;

    public Event jsonToDao(EventJSON event) {
        Event daoEvent = new Event();
        daoEvent.setApiId(event.getId());
        daoEvent.setName(event.getName());
        if(event.getEndDate().getSecond() == 59) {
            daoEvent.setEndDate(event.getEndDate().minus(59, ChronoUnit.SECONDS));
        } else {
            daoEvent.setEndDate(event.getEndDate());
        }
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

        daoEvent.setTicketAmount((long) random.nextInt((MAX - MIN) + 1) + MIN);

        daoEvent.setTicket(ticketMapper.jsonToDao(event.getTickets()));

        Place place = placeDao.findByApiId(event.getPlace().getId()).get();
        daoEvent.setPlace(place);

        Organizer organizer = organizerDao.findByApiId(event.getOrganizer().getId()).get();
        daoEvent.setOrganizer(organizer);
        Category category = new Category();
        if (!categoryDao.findById(event.getCategoryId()).isEmpty()) {
            category = categoryDao.findById(event.getCategoryId()).get();
        } else {
            category = null;
        }
        daoEvent.setCategory(category);
        daoEvent.setUrls(urlMapper.jsonToDao(event.getUrls()));


        STDLOG.info("Success in mapping json to dao");
        return daoEvent;
    }
}
