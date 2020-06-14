package com.infoshareacademy.controller;

import com.infoshareacademy.service.EventMapLiveSearch;
import com.infoshareacademy.service.SneakPeakService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sneak-peak")
@Produces(MediaType.APPLICATION_JSON)
public class SneakPeakController {

    @Inject
    SneakPeakService sneakPeakService;

    @GET
    @Path("{email}")
    public Response upcoming(@PathParam("email") String email) {
        return Response.status(Response.Status.OK)
                .entity(sneakPeakService.upcomingEvent(email).get())
                .build();
    }
}
