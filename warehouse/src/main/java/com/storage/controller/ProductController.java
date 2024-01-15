package com.storage.controller;

import com.storage.dto.ProductDto;
import com.storage.dto.ProductMapper;
import com.storage.entity.Product;
import com.storage.service.ProductService;
import global.exception.EntityNotFoundException;
import global.exception.WrongUsageException;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProductController {

    private static final String PRODUCT_NOT_FOUND = "Product not found";
    @Inject
    ProductService productService;

    @Inject
    ProductMapper productMapper;

    @GET
    @PermitAll
    public Response getAllProducts() {
        List<ProductDto> productsDtoList = productMapper.productListToProductDtoList(productService.getAllProducts());
        return Response.ok(productsDtoList).build();
    }

    @GET
    @Path("/{productId}")
    @PermitAll
    public Response getProduct(@PathParam("productId") Long productId) {
        Product productFound = productService.findById(productId).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
        ProductDto productDto = productMapper.productToProductDto(productFound);
        return Response.ok(productDto).build();
    }

    @GET
    @Path("/another/{productId}")
    @PermitAll
    public Response getProductAnotherView(@PathParam("productId") Long productId) {
        Product productFound = productService.findById(productId).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
        ProductDto productDto = productMapper.productToProductDto(productFound);
        return Response.ok(productDto).build();
    }

    @POST
    @Transactional
    @PermitAll
    public Response addProduct(@Valid ProductDto productDto, @Context UriInfo uriInfo) {
        Product product = productMapper.productDtoToProduct(productDto);
        product.setProductName(product.getProductName().strip());
        Product productSaved = productService.save(product);
        URI uri = uriInfo.getAbsolutePathBuilder().path(productSaved.getId().toString()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{productId}")
    @Transactional
    @PermitAll
    public Response updateProduct(@Valid ProductDto productDto, @PathParam("productId") Long productId) {
        if (!Objects.equals(productId, productDto.getId())) {
            throw new WrongUsageException("ProductId does not match for update");
        }
        Product productToUpdate = productService.findById(productDto.getId()).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
        productMapper.updateProductFromProductDto(productDto, productToUpdate);
        productService.save(productToUpdate);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Transactional
    @Path("/{productId}")
    @PermitAll
    public Response deleteProduct(@PathParam("productId") Long productId) {
        Product productToUpdate = productService.findById(productId).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND));
        productService.inactivate(productToUpdate);
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
