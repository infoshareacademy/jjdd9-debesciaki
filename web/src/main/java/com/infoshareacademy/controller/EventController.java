package com.infoshareacademy.controller;

import com.infoshareacademy.service.favourite.EventRestService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventController {
    @Inject
    EventRestService eventRestService;

    @DELETE
    @Path("/{eventId}")
    public Response deleteEvent(@PathParam("eventId") Long eventId) {
        return eventRestService.deleteEvent(eventId);
    }
}
