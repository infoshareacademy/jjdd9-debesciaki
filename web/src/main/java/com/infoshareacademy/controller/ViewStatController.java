package com.infoshareacademy.controller;

import com.infoshareacademy.domain.stat.DatesForm;
import com.infoshareacademy.service.stat.ViewStatService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    @Path("/global")
    public Response provideGlobalClicksPerEvent() {
        return Response.status(Response.Status.OK)
                .entity(viewStatService.provideGlobalClicksPerEvent())
                .build();
    }

    @POST
    @Path("/period")
    public Response providePeriodClicksPerEvent(DatesForm datesForm) {
        return Response.status(Response.Status.OK)
                .entity(viewStatService.providePeriodClicksPerEvent(datesForm.getDate1(), datesForm.getDate2()))
                .build();
    }

    @GET
    @Path("/global/org")
    public Response provideGlobalClicksPerOrganizer() {
        return Response.status(Response.Status.OK)
                .entity(viewStatService.provideGlobalClicksPerOrganizer())
                .build();
    }

    @POST
    @Path("/period/org")
    public Response providePeriodClicksPerOrganizer(DatesForm datesForm) {
        return Response.status(Response.Status.OK)
                .entity(viewStatService.providePeriodClicksPerOrganizer(datesForm.getDate1(), datesForm.getDate2()))
                .build();
    }


}