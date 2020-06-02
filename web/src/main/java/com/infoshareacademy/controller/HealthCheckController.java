package com.infoshareacademy.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthCheckController {
    @GET
    public Response checkHealth() {
        return Response.ok().build();
    }
}
