package com.roomreservation.roomservice.rest;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class RoomResourceTest {

    @Test
    void testGetAllRoomsWithCorrectData() {
        int page = 0;
        int pageSize = 10;

        given()
                .queryParam("page", page)
                .queryParam("size", pageSize)
                .when()
                .get("/rooms")
                .then()
                .statusCode(200);
    }

    @Test
    void testGetAllRoomsWithNoData() {
        int page = 2;
        int pageSize = 10;

        given()
                .queryParam("page", page)
                .queryParam("size", pageSize)
                .when()
                .get("/rooms")
                .then()
                .statusCode(200);
    }


}