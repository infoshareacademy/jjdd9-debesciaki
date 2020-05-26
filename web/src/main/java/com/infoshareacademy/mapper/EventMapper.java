package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.AttachmentJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.entity.Attachment;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.repository.CategoryRepositoryBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventMapper {
    @Inject
    AttachmentMapper attachmentMapper;

    @Inject
    PlaceMapper placeMapper;

    @Inject
    OrganizerMapper organizerMapper;

    @Inject
    CategoryMapper categoryMapper;

    @Inject
    CategoryRepositoryBean categoryRepositoryBean;

    public EventJSON daoToJson(Event event) {
        EventJSON jsonEvent = new EventJSON();
        jsonEvent.setId(event.getId());
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
        return jsonEvent;
    }

    public Event jsonToDao(EventJSON event) {
        Event daoEvent = new Event();
        daoEvent.setName(event.getName());
        daoEvent.setEndDate(event.getEndDate());
        daoEvent.setStartDate(event.getStartDate());
        daoEvent.setActive(event.getActive());
        daoEvent.setDescLong(event.getDescLong());
        daoEvent.setDescShort(event.getDescShort());
        List<Attachment> attachments = new ArrayList<>();
        for (AttachmentJSON a : event.getAttachments()) {
            attachments.add(attachmentMapper.jsonToDao(a));
        }
        daoEvent.setAttachments(attachments);
        daoEvent.setPlace(placeMapper.jsonToDao(event.getPlace()));
        daoEvent.setOrganizer(organizerMapper.jsonToDao(event.getOrganizer()));
        daoEvent.setCategory(categoryRepositoryBean.findById(event.getCategoryId()).get());
        return daoEvent;
    }
}
