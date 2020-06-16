package com.infoshareacademy.controller;

import com.infoshareacademy.service.ViewStatService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/view-stat")
@Produces(MediaType.APPLICATION_JSON)
public class ViewStatController {

    @Inject
    private ViewStatService viewStatService;

    @GET
    public Response provideAll() {
        return Response.status(Response.Status.OK)
                .entity(viewStatService.provideAllViewStatsNonAggregated())
                .build();
    }

    @GET
    @Path("/global")
    public Response provideGlobalClicksPerEvent() {
        return Response.status(Response.Status.OK)
                .entity(viewStatService.provideGlobalClicksPerEvent())
                .build();
    }
}