package com.storage.controller;

import com.storage.dto.ThirdPartyMapper;
import com.storage.service.ThirdPartyService;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/thirdparties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ThirdPartyController {
    @Inject
    ThirdPartyService thirdPartyService;

    @Inject
    ThirdPartyMapper thirdPartyMapper;

    @Inject
    Logger log;

    @GET
    @Path("/allThirdParties")
    @RolesAllowed({"Admin"})
    public Response getAllThirdParties(@Context SecurityContext ctx) {
        log.info("Logged in: " + ctx.getUserPrincipal().getName());
        var thirdPartyDtoList = thirdPartyMapper.thirdPartyListToThirdPartyDtoList(thirdPartyService.getAllThirdParty());
        return Response.ok(thirdPartyDtoList).build();
    }


    @GET
    @Path("/allThirdPartiesForUser")
    @RolesAllowed({"User", "PowerUser"})
    public Response getAllProducts(@Context SecurityContext ctx) {
        var thirdPartyDtoList = thirdPartyMapper.thirdPartyListToThirdPartyDtoList(thirdPartyService.getAllThirdPartyForUser(ctx.isUserInRole("PowerUser")));
        return Response.ok(thirdPartyDtoList).build();
    }

}
