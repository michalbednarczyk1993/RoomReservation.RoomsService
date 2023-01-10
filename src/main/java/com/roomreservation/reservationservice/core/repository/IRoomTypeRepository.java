package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.domain.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {
}
