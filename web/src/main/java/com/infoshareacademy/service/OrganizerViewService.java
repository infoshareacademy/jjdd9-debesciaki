package com.infoshareacademy.service;

import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.entity.Organizer;
import com.infoshareacademy.mapper.OrganizerMapper;
import com.infoshareacademy.query.OrganizerQuery;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrganizerViewService {

    @Inject
    OrganizerQuery organizerQuery;

    public List<OrganizerJSON> prepareOrganizersToShow(int firstResult) {

        List<OrganizerJSON> organizersList = new ArrayList<>();

        for(Organizer organizer : organizerQuery.organizersListWithLimit(firstResult)) {
            organizersList.add(mapper(organizer));
        }

        return organizersList;
    }

    public Integer listSize() {
        return organizerQuery.sizeList();
    }

    public OrganizerJSON mapper(Organizer organizer) {
        OrganizerJSON organizerJSON = new OrganizerJSON();
        organizerJSON.setId(organizer.getId());
        organizerJSON.setDesignation(organizer.getDesignation());
        return organizerJSON;
    }
}
