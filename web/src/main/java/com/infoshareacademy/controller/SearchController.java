package com.infoshareacademy.controller;

import com.infoshareacademy.service.EventQueryRestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchController {

    @Inject
    EventQueryRestService eventQueryRestService;

    @GET
    @Path("/eve")
    public Response eve(String phrase) {
        return Response.status(Response.Status.OK)
                .entity(eventQueryRestService.findByEve(1, phrase, true))
                .build();
    }

    @GET
    @Path("/org")
    public Response org(String phrase) {
        return Response.status(Response.Status.OK)
                .entity(eventQueryRestService.findByOrg(1, phrase, true))
                .build();
    }

    @GET
    @Path("/eve-org")
    public Response eveOrg(String phrase) {
        return Response.status(Response.Status.OK)
                .entity(eventQueryRestService.findByEveOrg(1, phrase, true))
                .build();
    }
}
