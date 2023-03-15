package com.roomreservation.roomservice.core.repository;

import com.roomreservation.roomservice.core.domain.PhotoLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoLinkRepository extends JpaRepository<PhotoLinkEntity, Long> {
}
