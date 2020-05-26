package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.EventJSON;
import com.infoshareacademy.entityDomain.Event;

import javax.ejb.Stateless;
import javax.inject.Inject;

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

    public EventJSON daoToJson(Event event){
        EventJSON jsonEvent = new EventJSON();
        jsonEvent.setId(event.getId());
        jsonEvent.setName(event.getName());
        jsonEvent.setEndDate(event.getEndDate());
        jsonEvent.setStartDate(event.getStartDate());
        jsonEvent.setActive(event.getActive());
        jsonEvent.setDescLong(event.getDescLong());
        jsonEvent.setDescShort(event.getDescShort());
        jsonEvent.getAttachments(attachmentMapper.daoToJson(event.getAttachments()));
        jsonEvent.getPlace(placeMapper.daoToJson(event.getPlace()));
        jsonEvent.getOrganizer(organizerMapper.daoToJson(event.getOrganizer()));
        jsonEvent.getCategory(categoryMapper.daoToJson(event.getCategory());
        return jsonEvent;
    }
    public Event jsonToDao(Event event){
        Event daoEvent = new Event();
        daoEvent.setName(event.getName());
        daoEvent.setEndDate(event.getEndDate());
        daoEvent.setStartDate(event.getStartDate());
        daoEvent.setActive(event.getActive());
        daoEvent.setDescLong(event.getDescLong());
        daoEvent.setDescShort(event.getDescShort());
        daoEvent.getAttachments(attachmentMapper.jsonToDao(event.getAttachments()));
        daoEvent.getPlace(placeMapper.jsonToDao(event.getPlace()));
        daoEvent.getOrganizer(organizerMapper.jsonToDao(event.getOrganizer()));
        daoEvent.getCategory(categoryMapper.jsonToDao(event.getCategory());
        return daoEvent;
    }
}
