package com.roomreservation.roomservice.core.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TestEntityTest {

    @Test
    @Transactional
    void testPersistAndFind() {
        TestEntity testEntity = new TestEntity();
        testEntity.text = "test";
        testEntity.persist();

        assertTrue(testEntity.isPersistent());
    }

    @Test
    void testFindByTextOptionalPassCase() {
        Optional<TestEntity> result = TestEntity.findByTextOptional("sample");
        assertTrue(result.isPresent());
    }

    @Test
    void testFindByTextOptionalFailedCase() {
        Optional<TestEntity> result = TestEntity.findByTextOptional("incorrect");
        assertFalse(result.isPresent());
    }
}