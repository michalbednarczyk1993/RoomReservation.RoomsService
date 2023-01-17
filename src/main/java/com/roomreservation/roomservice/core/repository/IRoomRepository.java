package com.roomreservation.roomservice.core.repository;

import com.roomreservation.roomservice.core.domain.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoomRepository extends JpaRepository<RoomEntity, Long> {

}
