package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.domain.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoomRepository extends JpaRepository<RoomEntity, Long> {

}
