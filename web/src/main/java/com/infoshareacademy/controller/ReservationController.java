package com.infoshareacademy.controller;

import com.infoshareacademy.service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/request-reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    @GET
    @Path("/{userMail}/{eventId}")
    public Response requestReservation(@PathParam("userMail") String mail, @PathParam("eventId") Long eventId) {
        reservationService.requestReservation(eventId, mail);
        return Response.status(Response.Status.OK)
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