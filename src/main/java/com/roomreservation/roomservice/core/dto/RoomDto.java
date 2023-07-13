package com.roomreservation.roomservice.core.dto;

import com.roomreservation.roomservice.core.domain.RoomEntity;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.NonNull;

@Builder
@ApiModel(description = "Szczegóły na temat pokoi")
public record RoomDto() {
    public interface OnCreate{}
    public interface OnUpdate{}

    public static RoomDto toDto(@NonNull RoomEntity entity) {
        return RoomDto.builder()
                // wypełnij pola
                .build();
    }

    public RoomEntity toEntity() {
        return RoomEntity.builder()
                // wypełnij pola
                .build();
    }

    public RoomEntity updateEntity(RoomEntity entity) {
        //if (name != null) entity.setName(name);
        return entity;
    }
}
