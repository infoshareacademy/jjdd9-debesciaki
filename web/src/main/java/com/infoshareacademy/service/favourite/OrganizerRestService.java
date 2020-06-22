package com.infoshareacademy.service.favourite;

import com.infoshareacademy.repository.OrganizerDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class OrganizerRestService {
    @Inject
    OrganizerDao organizerDao;

    public Response addOrganizer(String designation) {
        organizerDao.create(designation);
        return Response.status(Response.Status.OK).build();
    }
}
