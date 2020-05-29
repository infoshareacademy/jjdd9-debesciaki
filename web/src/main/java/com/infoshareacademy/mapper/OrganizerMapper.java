package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.entity.Organizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class OrganizerMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(OrganizerMapper.class.getName());

    public OrganizerJSON daoToJson(Organizer organizer) {
        OrganizerJSON jsonOrganizer = new OrganizerJSON();
        jsonOrganizer.setId(organizer.getApiId());
        jsonOrganizer.setDesignation(organizer.getDesignation());
        STDLOG.info("Success in mapping dao to json");
        return jsonOrganizer;
    }

    public Organizer jsonToDao(OrganizerJSON organizer) {
        Organizer daoOrganizer = new Organizer();
        daoOrganizer.setApiId(organizer.getId());
        daoOrganizer.setDesignation(organizer.getDesignation());
        STDLOG.info("Success in mapping json to dao");
        return daoOrganizer;
    }
}
