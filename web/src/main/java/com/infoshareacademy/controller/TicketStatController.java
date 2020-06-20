package com.infoshareacademy.controller;

import com.infoshareacademy.domain.stat.TicketRatio;
import com.infoshareacademy.service.stat.TicketStatService;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @POST
    @Path("/eve1")
    public Response incrementEve1(){
        ticketStatService.updateSingleTicketStat(1L,true);
        return Response.status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/eve1f")
    public Response incrementEve1f(){
        ticketStatService.updateSingleTicketStat(1L,false);
        return Response.status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/eve2")
    public Response incrementEve2(){
        ticketStatService.updateSingleTicketStat(2L,true);
        return Response.status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/eve2f")
    public Response incrementEve2f(){
        ticketStatService.updateSingleTicketStat(2L,false);
        return Response.status(Response.Status.OK)
                .build();
    }
}