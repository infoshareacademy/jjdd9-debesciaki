package com.infoshareacademy.controller;

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
    public Response addFavourite(@PathParam("userEmail") String userEmail,
                                 @PathParam("eventId") Long eventId) {
        return favouriteRestService.addFavourite(userEmail, eventId);
    }

    @DELETE
    @Path("{userEmail}/event/{eventId}")
    public Response removeFavourite(@PathParam("userEmail") String userEmail,
                                    @PathParam("eventId") Long eventId) {
        return favouriteRestService.removeFavourite(userEmail, eventId);
    }

    @GET
    @Path("{userEmail}/event/{eventId}")
    public Response isFavourite(@PathParam("userEmail") String userEmail,
                                @PathParam("eventId") Long eventId) {
        return favouriteRestService.isFavourite(userEmail, eventId);
    }


}
