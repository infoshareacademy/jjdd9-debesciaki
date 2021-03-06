package com.infoshareacademy.controller;

import com.infoshareacademy.service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("/request-reservation")
@Produces(MediaType.TEXT_PLAIN)
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    @GET
    @Path("/{userMail}/f/{eventId}")
    public Response requestReservationFull(@PathParam("userMail") String mail, @PathParam("eventId") Long eventId) {
        return Response.status(Response.Status.OK)
                .entity(reservationService.requestReservation(eventId, mail, true))
                .build();
    }

    @GET
    @Path("/{userMail}/r/{eventId}")
    public Response requestReservationRedu(@PathParam("userMail") String mail, @PathParam("eventId") Long eventId) {
        return Response.status(Response.Status.OK)
                .entity(reservationService.requestReservation(eventId, mail, false))
                .build();
    }

    @GET
    @Path("/consume/{token}")
    public Response useToken(@PathParam("token") String token) throws URISyntaxException {

        java.net.URI location = new java.net.URI("http://debesciaki.jjdd9.is-academy.pl/token?msg="+reservationService.consumeToken(token));
        return Response.temporaryRedirect(location).build();
    }

    @DELETE
    @Path("/{token}")
    public Response deleteReservation(@PathParam("token") String token) {
        return Response.status(Response.Status.OK)
                .entity(reservationService.deleteReservationManualFromList(token))
                .build();
    }

}