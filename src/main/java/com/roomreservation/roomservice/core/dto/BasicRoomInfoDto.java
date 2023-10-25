package com.roomreservation.roomservice.core.dto;


import com.roomreservation.roomservice.core.domain.RoomEntity;

public record BasicRoomInfoDto(
        Long id,
        String number,
        String roomName,
        String typeName,
        Integer typeCapacity,
        Double typeBasePrice
) {

    public static BasicRoomInfoDto toDto(RoomEntity entity) {
        return new BasicRoomInfoDto(
                entity.getId(),
                entity.getNumber(),
                entity.getName(),
                entity.getType().getName(),
                entity.getType().getCapacity(),
                entity.getType().getBasePrice());
    }

}
