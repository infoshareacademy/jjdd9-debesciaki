package com.infoshareacademy.controller;

import com.infoshareacademy.domain.stat.TicketRatio;
import com.infoshareacademy.service.stat.TicketStatService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @GET
    @Path("/eve1")
    public void incrementEve1(){
        ticketStatService.updateSingleTicketStat(1L,true);
    }

    @GET
    @Path("/eve1f")
    public void incrementEve1f(){
        ticketStatService.updateSingleTicketStat(1L,false);
    }

    @GET
    @Path("/eve2")
    public void incrementEve2(){
        ticketStatService.updateSingleTicketStat(2L,true);
    }

    @GET
    @Path("/eve2f")
    public void incrementEve2f(){
        ticketStatService.updateSingleTicketStat(2L,false);
    }
}