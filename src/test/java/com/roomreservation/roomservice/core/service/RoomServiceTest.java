package com.roomreservation.roomservice.core.service;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class RoomServiceTest {

    @Inject
    RoomService roomService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllRooms() {
        roomService.getAllRooms(1,10);
    }
}