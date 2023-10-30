package com.roomreservation.roomservice.core.dto;

import com.roomreservation.roomservice.core.domain.PhotoLinkEntity;
import com.roomreservation.roomservice.core.domain.RoomTypeEntity;

import java.util.List;

public record RoomTypeDto(
        Long id,
        Double basePrice,
        Integer capacity,
        String name,
        String description,
        List<PhotoLinkEntity> photoLinks,
        Integer rooms
) {
    public static RoomTypeDto toDto(RoomTypeEntity entity) {
        return new RoomTypeDto(
                entity.id,
                entity.basePrice,
                entity.capacity,
                entity.name,
                entity.description,
                entity.photoLinks,
                entity.rooms.size());
    }

}
