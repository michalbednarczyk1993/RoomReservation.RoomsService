package com.roomreservation.roomservice.core.service;

import com.roomreservation.roomservice.core.domain.RoomEntity;
import com.roomreservation.roomservice.core.domain.RoomTypeEntity;
import com.roomreservation.roomservice.core.dto.BasicRoomInfoDto;
import com.roomreservation.roomservice.core.dto.RoomDetailsDto;
import com.roomreservation.roomservice.core.dto.RoomDto;
import com.roomreservation.roomservice.core.dto.RoomTypeDto;
import com.roomreservation.roomservice.exceptions.NoContentException;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class RoomService {

    public List<BasicRoomInfoDto> getAllRooms(Integer page, Integer size) {
        List<RoomEntity> rooms = RoomEntity.findAll().page(Page.of(page, size)).list();
        List<BasicRoomInfoDto> result = rooms.stream().map(
                BasicRoomInfoDto::toDto).collect(Collectors.toList());

        if (result.isEmpty()) throw new NoContentException();
        return result;
    }

    public List<RoomTypeDto> getAllRoomTypes() {
        List<RoomTypeEntity> roomTypes = RoomTypeEntity.findAll(Sort.ascending("capacity")).list();
        List<RoomTypeDto> result = roomTypes
                .stream()
                .map(RoomTypeDto::toDto)
                .collect(Collectors.toList());

        if (result.isEmpty()) throw new NoContentException();
        return result;
    }

    public RoomDetailsDto getRoomDetails(Long id) {
        RoomEntity entity = (RoomEntity) RoomEntity.findByIdOptional(id).orElseThrow(NoContentException::new);
        return RoomDetailsDto.toDto(entity);
    }

    public void createRoom(RoomDto roomDto) {
        RoomEntity entity = new RoomEntity(
                roomDto.number(),
                roomDto.name(),
                (RoomTypeEntity) RoomTypeEntity.findByIdOptional(roomDto.roomTypeId()).orElseThrow(BadRequestException::new));
        RoomEntity.persist(entity);
        RoomEntity.flush();
    }

    public void updateRoom(Long id, RoomDto newData) {
        RoomEntity entity = (RoomEntity) RoomEntity.findByIdOptional(id).orElseThrow(NoContentException::new);
        if (newData.number() != null) entity.number = newData.number();
        if (newData.name() != null) entity.name = newData.name();
        if (newData.roomTypeId() != null) entity.type = (RoomTypeEntity) RoomTypeEntity.findByIdOptional(newData.roomTypeId()).orElse(
                RoomTypeEntity.findByNameOptional(newData.name()).orElseThrow(BadRequestException::new));
        RoomEntity.persist(entity);
        RoomEntity.flush();
    }

    public void deleteRoom(Long id) {
        RoomEntity entity = (RoomEntity) RoomEntity.findByIdOptional(id).orElseThrow(NoContentException::new);
        entity.delete();
    }

}
