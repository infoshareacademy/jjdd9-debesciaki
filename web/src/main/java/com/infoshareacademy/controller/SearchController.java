package com.infoshareacademy.controller;

import com.infoshareacademy.service.event.EventMapLiveSearch;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchController {

    @Inject
    EventMapLiveSearch eventMapLiveSearch;

    @GET
    @Path("/eve/{phrase}")
    public Response eve(@PathParam("phrase") String phrase) {
        return Response.status(Response.Status.OK)
                .entity(eventMapLiveSearch.searchThenAndMapEve(phrase))
                .build();
    }

    @GET
    @Path("/eve/{phrase}/{start}/{end}")
    public Response eveDate(@PathParam("phrase") String phrase,
                            @PathParam("start") String start,
                            @PathParam("end") String end) {

        return Response.status(Response.Status.OK)
                .entity(eventMapLiveSearch.searchThenAndMapEveDate(phrase, start, end))
                .build();
    }

    @GET
    @Path("/org/{phrase}")
    public Response org(@PathParam("phrase") String phrase) {
        return Response.status(Response.Status.OK)
                .entity(eventMapLiveSearch.searchThenAndMapOrg(phrase))
                .build();
    }

    @GET
    @Path("/org/{phrase}/{start}/{end}")
    public Response orgDate(@PathParam("phrase") String phrase,
                            @PathParam("start") String start,
                            @PathParam("end") String end) {

        return Response.status(Response.Status.OK)
                .entity(eventMapLiveSearch.searchThenAndMapOrgDate(phrase, start, end))
                .build();
    }

    @GET
    @Path("/eve-org/{phrase}")
    public Response eveOrg(@PathParam("phrase") String phrase) {
        return Response.status(Response.Status.OK)
                .entity(eventMapLiveSearch.searchThenAndMapEveOrg(phrase))
                .build();
    }

    @GET
    @Path("/eve-org/{phrase}/{start}/{end}")
    public Response eveOrgDate(@PathParam("phrase") String phrase,
                               @PathParam("start") String start,
                               @PathParam("end") String end) {

        return Response.status(Response.Status.OK)
                .entity(eventMapLiveSearch.searchThenAndMapEveOrgDate(phrase, start, end))
                .build();
    }
}
