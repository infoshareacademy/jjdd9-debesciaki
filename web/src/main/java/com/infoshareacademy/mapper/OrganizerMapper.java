package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.OrganizerJSON;
import com.infoshareacademy.entityDomain.Organizer;

import javax.ejb.Stateless;

@Stateless
public class OrganizerMapper {
    public OrganizerJSON daoToJson(Organizer organizer) {
        OrganizerJSON jsonOrganizer = new OrganizerJSON();
        jsonOrganizer.setId(organizer.getId());
        jsonOrganizer.setDesignation(organizer.getDesignation());
        return jsonOrganizer;
    }

    public Organizer jsonToDao(OrganizerJSON organizer) {
        Organizer daoOrganizer = new Organizer();
        daoOrganizer.setId(organizer.getId());
        daoOrganizer.setDesignation(organizer.getDesignation());
        return daoOrganizer;
    }
}
