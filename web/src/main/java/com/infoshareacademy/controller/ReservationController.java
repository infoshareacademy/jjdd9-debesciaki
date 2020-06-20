package com.infoshareacademy.controller;

import com.infoshareacademy.domain.stat.DatesForm;
import com.infoshareacademy.service.ReservationService;
import com.infoshareacademy.service.stat.ViewStatService;

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
    public Response provideGlobalClicksPerEvent(@PathParam("userMail") String mail, @PathParam("eventId") Long eventId) {
        reservationService.requestReservation(eventId,mail);
        return Response.status(Response.Status.OK)
                .build();
    }

}