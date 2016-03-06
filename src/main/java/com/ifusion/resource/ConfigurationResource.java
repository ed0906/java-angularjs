package com.ifusion.resource;

import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Configuration")
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ConfigurationResource {

    @GET
    @Path("/healthcheck")
    @ApiOperation("Returns a 200 if the application is up")
    public Response healthCheck() {
        return Response.ok().build();
    }
}
