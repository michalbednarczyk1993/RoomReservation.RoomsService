package com.roomreservation.roomservice.core.repository;

import com.roomreservation.roomservice.core.domain.RoomTypeEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public interface RoomTypeRepository extends PanacheRepository<RoomTypeEntity> {
    Optional<RoomTypeEntity> findByNameOptional(String name);
}
