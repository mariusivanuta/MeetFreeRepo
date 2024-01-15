package com.storage.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ThirdPartyControllerTest {

    @Test
    @TestSecurity(user = "admin", roles = {"Admin", "User"})
    void getAllProductsWithAminRole() {
        given().contentType(ContentType.JSON)
                .when().get("/warehouse/thirdparties/allThirdParties")
                .then()
                .statusCode(200);
    }

    @Test
    void getAllProductsWithoutAminRole() {
        given().contentType(ContentType.JSON)
                .when().get("/warehouse/thirdparties/allThirdParties")
                .then()
                .statusCode(401);
    }
}