package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.entity.Organizer;

import javax.ejb.Stateless;

@Stateless
public class OrganizerMapper {

    public OrganizerJSON daoToJson(Organizer organizer) {
        OrganizerJSON jsonOrganizer = new OrganizerJSON();
        jsonOrganizer.setId(organizer.getApiId());
        jsonOrganizer.setDesignation(organizer.getDesignation());
        return jsonOrganizer;
    }

    public Organizer jsonToDao(OrganizerJSON organizer) {
        Organizer daoOrganizer = new Organizer();
        daoOrganizer.setApiId(organizer.getId());
        daoOrganizer.setDesignation(organizer.getDesignation());
        return daoOrganizer;
    }
}
