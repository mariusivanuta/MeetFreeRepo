package com.storage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.storage.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/one")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AssociationController {

    @Inject
    ProductRepository productRepository;

    @Inject
    EntityManager entityManager;

    @GET
    public Response demoSerialDeserial() throws JsonProcessingException {


        return Response.ok().build();
    }
}
