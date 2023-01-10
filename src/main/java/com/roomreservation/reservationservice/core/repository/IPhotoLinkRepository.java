package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.domain.PhotoLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoLinkRepository extends JpaRepository<PhotoLinkEntity, Long> {
}
