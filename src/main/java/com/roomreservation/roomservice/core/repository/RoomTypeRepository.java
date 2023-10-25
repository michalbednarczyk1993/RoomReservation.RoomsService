package com.roomreservation.roomservice.core.repository;

import com.roomreservation.roomservice.core.domain.RoomTypeEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface RoomTypeRepository extends PanacheRepository<RoomTypeEntity> {
}
