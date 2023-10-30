package com.roomreservation.roomservice.core.dto;

import com.roomreservation.roomservice.core.domain.PhotoLinkEntity;
import com.roomreservation.roomservice.core.domain.RoomEntity;

import java.util.List;

public record RoomDetailsDto(
        Long id,
        Integer number,
        String roomName,
        String typeName,
        Double basePrice,
        Integer capacity,
        String description,
        List<PhotoLinkEntity> photoLinks
) {

    public static RoomDetailsDto toDto(RoomEntity entity) {
        return new RoomDetailsDto(
                entity.id,
                entity.number,
                entity.name,
                entity.type.name,
                entity.type.basePrice,
                entity.type.capacity,
                entity.type.description,
                entity.type.photoLinks);
    }
}
