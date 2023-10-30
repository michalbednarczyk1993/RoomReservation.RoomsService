package com.roomreservation.roomservice.core.dto;


import com.roomreservation.roomservice.core.domain.RoomEntity;

public record BasicRoomInfoDto(
        Long id,
        Integer number,
        String roomName,
        String typeName,
        Integer typeCapacity,
        Double typeBasePrice
) {

    public static BasicRoomInfoDto toDto(RoomEntity entity) {
        return new BasicRoomInfoDto(
                entity.id,
                entity.number,
                entity.name,
                entity.type.name,
                entity.type.capacity,
                entity.type.basePrice);
    }

}
