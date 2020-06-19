package com.infoshareacademy.controller;

import com.infoshareacademy.domain.stat.TicketRatio;
import com.infoshareacademy.service.stat.TicketStatService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ticket-stat")
@Produces(MediaType.APPLICATION_JSON)
public class TicketStatController {

    @Inject
    private TicketStatService ticketStatService;

    @GET
    public Response provideTicketRatio() {
        return Response.status(Response.Status.OK)
                .entity(ticketStatService.findAll())
                .build();
    }
}