package com.infoshareacademy.controller;

import com.infoshareacademy.service.favourite.FavouriteRestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/favourite")
@Produces(MediaType.APPLICATION_JSON)
public class FavouritesController {
    @Inject
    FavouriteRestService favouriteRestService;

    @GET
    @Path("{userEmail}/event/{eventId}")
    public Response eve(@PathParam("userEmail") String userEmail,
                        @PathParam("eventId") Long eventId) {
        return Response.status(Response.Status.OK)
                .entity(favouriteRestService.addFavourite(userEmail, eventId))
                .build();
    }

}
