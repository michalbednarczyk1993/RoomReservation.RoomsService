package com.roomreservation.roomservice.core.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Optional;

@Entity
@Table(name = "TEST")
public class TestEntity extends PanacheEntity {
    public String text;

    public static Optional<TestEntity> findByTextOptional(String text) {
        return find("text", text).firstResultOptional();
    }
}
