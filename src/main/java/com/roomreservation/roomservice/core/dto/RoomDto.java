package com.roomreservation.roomservice.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoomDto(
        @Positive Integer number,
        @NotBlank String name,
        @NotNull Long roomTypeId,
        @NotNull String roomTypeName
) {

}
