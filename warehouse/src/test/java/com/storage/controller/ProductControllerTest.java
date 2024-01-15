package com.storage.controller;

import com.storage.entity.Product;
import com.storage.repository.ProductRepository;
import global.exception.ErrorResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class ProductControllerTest {

    @Inject
    ProductRepository productRepository;

    @Test
    @Order(1)
    void getAllProducts() {
        given().contentType(ContentType.JSON)
                .when().get("/warehouse/products")
                .then()
                .statusCode(200)
                .and()
                .body("size()", is(2));
    }

    @Test
    @Order(1)
    void getProduct() {
        given().contentType(ContentType.JSON)
                .when().get("/warehouse/products/1")
                .then()
                .statusCode(200)
                .and()
                .body("productName", is("Product 1"));
    }

    @Test
    @Order(1)
    void getInexistentProductShouldThrowAnException() {
        ErrorResponse errorResponse = given().contentType(ContentType.JSON)
                .when().get("/warehouse/products/100")
                .then()
                .extract().jsonPath().getObject("", ErrorResponse.class);
        assertThat(errorResponse, hasProperty("message", equalTo("Product not found")));
    }

    @Test
    @Order(2)
    void addProduct() {
        JSONObject newProduct = new JSONObject();
        newProduct.put("productName", "new Product");
        newProduct.put("productDescription", "description ofnew Product");
        newProduct.put("productPrice", 1.00);

        given().body(newProduct.toString()).contentType(ContentType.JSON)
                .when().post("/warehouse/products")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(3)
    void updateProduct() {
        JSONObject updateProduct = new JSONObject();
        updateProduct.put("id", "1");
        updateProduct.put("productName", "Update Product 1");
        updateProduct.put("productDescription", "Update Description Product 1");
        updateProduct.put("productPrice", 10.00);

        given().body(updateProduct.toString()).contentType(ContentType.JSON)
                .when().put("/warehouse/products/1")
                .then()
                .statusCode(202);
        Product p = productRepository.findById(1L).orElseThrow();
        assertThat(p.getProductName(), is(updateProduct.getString("productName")));
    }

    @Test
    @Order(4)
    void deleteProduct() {
        given().contentType(ContentType.JSON)
                .when().delete("/warehouse/products/1")
                .then()
                .statusCode(202);

        Product p = productRepository.findById(1L).orElseThrow();
        assertThat(p.isActive(), is(false));
    }
}