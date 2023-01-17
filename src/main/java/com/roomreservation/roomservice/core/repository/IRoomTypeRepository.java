package com.roomreservation.roomservice.core.repository;

import com.roomreservation.roomservice.core.domain.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {
}
