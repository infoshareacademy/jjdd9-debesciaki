package com.infoshareacademy.controller;

import com.infoshareacademy.service.OrganizerViewService;
import com.infoshareacademy.service.favourite.OrganizerRestService;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/organizers")
public class OrganizerController {
    @Inject
    OrganizerRestService organizerRestService;

    @POST
    public Response addOrganizer(@FormParam("designation") String designation) {
        return organizerRestService.addOrganizer(designation);
    }
}
