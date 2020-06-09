package com.infoshareacademy.controller;

import com.infoshareacademy.domain.entity.User;
import com.infoshareacademy.service.favourite.FavouriteRestService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/favourite")
@Produces(MediaType.APPLICATION_JSON)
public class FavouritesController {
    @Inject
    FavouriteRestService favouriteRestService;

    @POST
    @Path("{userEmail}/event/{eventId}")
    public Response eve(@PathParam("userEmail") String userEmail,
                        @PathParam("eventId") Long eventId) {
        return Response.status(Response.Status.OK)
                .entity(favouriteRestService.addFavourite(userEmail, eventId))
                .build();
    }

}
