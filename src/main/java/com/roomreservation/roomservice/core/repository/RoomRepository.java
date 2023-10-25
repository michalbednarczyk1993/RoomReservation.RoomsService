package com.roomreservation.roomservice.core.repository;

import com.roomreservation.roomservice.core.domain.RoomEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface RoomRepository extends PanacheRepository<RoomEntity> {
}
