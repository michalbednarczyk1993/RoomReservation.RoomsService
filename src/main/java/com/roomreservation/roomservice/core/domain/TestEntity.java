package com.roomreservation.roomservice.core.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEST")
public class TestEntity extends PanacheEntity {
}
