package com.roomreservation.roomservice.core.dto;

import com.roomreservation.roomservice.core.domain.PhotoLinkEntity;
import com.roomreservation.roomservice.core.domain.RoomEntity;

import java.util.List;

public record RoomDetailsDto(
        Long id,
        String number,
        String roomName,
        String typeName,
        Double basePrice,
        Integer capacity,
        String description,
        List<PhotoLinkEntity> photoLinks
) {

    public static RoomDetailsDto toDto(RoomEntity entity) {
        return new RoomDetailsDto(
                entity.getId(),
                entity.getNumber(),
                entity.getName(),
                entity.getType().getName(),
                entity.getType().getBasePrice(),
                entity.getType().getCapacity(),
                entity.getType().getDescription(),
                entity.getType().getPhotoLinks());
    }
}
