package com.infoshareacademy.controller;

import com.infoshareacademy.service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/request-reservation")
@Produces(MediaType.TEXT_PLAIN)
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    @GET
    @Path("/{userMail}/{eventId}")
    public Response requestReservation(@PathParam("userMail") String mail, @PathParam("eventId") Long eventId) {
        return Response.status(Response.Status.OK)
                .entity(reservationService.requestReservation(eventId, mail))
                .build();
    }

    @GET
    @Path("/consume/{token}")
    public Response useToken(@PathParam("token") String token) {
        return Response.status(Response.Status.OK)
                .entity(reservationService.consumeToken(token))
                .build();
    }

}