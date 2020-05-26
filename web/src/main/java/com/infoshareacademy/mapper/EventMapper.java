package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.EventJSON;
import com.infoshareacademy.entityDAO.Event;

import javax.ejb.Stateless;

@Stateless
public class EventMapper {
    public EventJSON daoToJson(Event event){
        EventJSON jsonEvent = new EventJSON();
        return jsonEvent;
    }
    public Event jsonToDao(Event event){
        Event daoEvent = new Event();
        daoEvent.setActive(event.getActive());
        //TODO attachment mapper do zaaplikowania
        daoEvent.setAttachments(event.getAttachments());
        //TODO category mapper do zaaplikowania
        daoEvent.setCategory(event.getCategory());
        return daoEvent;
    }
}
