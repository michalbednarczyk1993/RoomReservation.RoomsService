package com.roomreservation.roomservice.rest;


import io.quarkus.test.common.QuarkusTestResource;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.h2.H2DatabaseTestResource;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
class TestOfResourceTest {


    @Test
    @Order(1)
    void hello() {
        given()
                .when().get("/test-rooms/hello")
                .then()
                .statusCode(200)
                .body(is("Sukces"));
    }

    @Test
    @Order(1)
    void saveData() {
        given()
                .contentType(ContentType.JSON)
                .when().post("/test-rooms/save")
                .then()
                .statusCode(200)
                .body(is("Sukces"));
    }

    @Test
    @Order(2)
    void getTestRoomsWithCorrectData() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("text", "sample")
                .when().get("/test-rooms")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("sample"));
    }

    @Test
    @Order(2)
    void getTestRoomsWithNoExistedContent() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("text", "incorrect")
                .when().get("/test-rooms")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    @Order(2)
    void getTestRoomsWithWrongData() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("text", 1)
                .when().get("/test-rooms")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .body(is("Nieprawidłowe dane w żądaniu"));
    }

    @Test
    void testThrowConstraintViolationExceptionMapper() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("exception", "ConstraintViolationException")
                .when().get("/test-rooms/throw")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .body(is("Nieprawidłowe dane w żądaniu"));
    }

    @Test
    void testThrowNoContentExceptionMapper() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("exception", "NoContentException")
                .when().get("/test-rooms/throw")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    void testThrowDefaultExceptionMapper() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("exception", "DefaultException")
                .when().get("/test-rooms/throw")
                .then()
                .statusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .body(is("Błąd serwera"));
    }
}