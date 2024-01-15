package com.storage.controller;

import com.storage.dto.StockMovementMapper;
import com.storage.service.StockMovementService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stock_movements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StockMovementController {

    @Inject
    StockMovementService stockMovementService;

    @Inject
    StockMovementMapper stockMovementMapper;

    @GET
    @Transactional
    public Response getProduct(@QueryParam("productId") Long productId) {
        var stockMovementList = stockMovementService.getStockMovementForId(productId);
        var stockMovementDtos = stockMovementMapper.stockMovementListToStockMovementDtoList(stockMovementList);
        return Response.ok(stockMovementDtos).build();
    }

}
