package com.infoshareacademy.controller;

import com.infoshareacademy.service.AddAvailableTicketsService;
import com.infoshareacademy.service.stat.TicketStatService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/add-tickets")
public class AddAvailableTicketsController {

    @Inject
    private AddAvailableTicketsService addAvailableTicketsService;

    @GET
    public Response addTickets() {
        return Response.status(Response.Status.OK)
                .entity(addAvailableTicketsService.update())
                .build();
    }

}