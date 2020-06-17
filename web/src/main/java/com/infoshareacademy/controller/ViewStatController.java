package com.infoshareacademy.controller;

import com.infoshareacademy.domain.view.stat.chart.ClicksPerEvent;
import com.infoshareacademy.domain.view.stat.chart.ClicksPerOrganizer;
import com.infoshareacademy.domain.view.stat.chart.DatesForm;
import com.infoshareacademy.service.ViewStatService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<ClicksPerEvent> list = viewStatService.provideGlobalClicksPerEvent();
        return Response.status(Response.Status.OK)
                .entity(viewStatService.provideGlobalClicksPerEvent())
                .build();
    }

    @POST
    @Path("/period")
    public Response providePeriodClicksPerEvent(DatesForm datesForm) {
        List<ClicksPerEvent> list = viewStatService.providePeriodClicksPerEvent(datesForm.getDate1(), datesForm.getDate2());
        return Response.status(Response.Status.OK)
                .entity(viewStatService.providePeriodClicksPerEvent(datesForm.getDate1(), datesForm.getDate2()))
                .build();
    }

    @GET
    @Path("/global/org")
    public Response provideGlobalClicksPerOrganizer() {
        List<ClicksPerOrganizer> list = viewStatService.provideGlobalClicksPerOrganizer();
        return Response.status(Response.Status.OK)
                .entity(viewStatService.provideGlobalClicksPerOrganizer())
                .build();
    }

    @POST
    @Path("/period/org")
    public Response providePeriodClicksPerOrganizer(DatesForm datesForm) {
        List<ClicksPerOrganizer> list = viewStatService.providePeriodClicksPerOrganizer(datesForm.getDate1(), datesForm.getDate2());
        return Response.status(Response.Status.OK)
                .entity(viewStatService.providePeriodClicksPerOrganizer(datesForm.getDate1(), datesForm.getDate2()))
                .build();
    }


}