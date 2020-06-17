package com.infoshareacademy.service;

import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.entity.Organizer;
import com.infoshareacademy.repository.OrganizerDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrganizerViewService {

    @Inject
    OrganizerDao organizerDao;

    public void save(String designation) {
        organizerDao.create(designation);
    }

    public List<OrganizerJSON> prepareOrganizersToShow() {

        List<OrganizerJSON> organizersList = new ArrayList<>();

        for (Organizer organizer : organizerDao.allOrganizersList()) {
            organizersList.add(mapper(organizer));
        }

        Comparator<OrganizerJSON> compareByDesignation = (OrganizerJSON o1, OrganizerJSON o2) -> o1.getDesignation().compareTo(o2.getDesignation());
        Collections.sort(organizersList, compareByDesignation);

        return organizersList;
    }

    public List<OrganizerJSON> prepareOrganizersToShow(int firstResult) {

        List<OrganizerJSON> organizersList = new ArrayList<>();

        for (Organizer organizer : organizerDao.organizersListWithLimit(firstResult)) {
            organizersList.add(mapper(organizer));
        }

        return organizersList;
    }

    public Integer listSize() {
        return organizerDao.sizeList();
    }

    public OrganizerJSON mapper(Organizer organizer) {
        OrganizerJSON organizerJSON = new OrganizerJSON();
        organizerJSON.setId(organizer.getId());
        organizerJSON.setDesignation(organizer.getDesignation());
        return organizerJSON;
    }
}
