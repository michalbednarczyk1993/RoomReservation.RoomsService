package com.roomreservation.roomservice.core.service;

import com.roomreservation.roomservice.core.domain.RoomEntity;
import com.roomreservation.roomservice.core.domain.RoomTypeEntity;
import com.roomreservation.roomservice.core.dto.BasicRoomInfoDto;
import com.roomreservation.roomservice.core.dto.RoomDetailsDto;
import com.roomreservation.roomservice.core.dto.RoomTypeDto;
import com.roomreservation.roomservice.core.repository.RoomRepository;
import com.roomreservation.roomservice.core.repository.RoomTypeRepository;
import com.roomreservation.roomservice.exceptions.NoContentException;
import jakarta.enterprise.context.RequestScoped;

import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class RoomService {
    private final RoomTypeRepository roomTypeRepo;
    private final RoomRepository roomRepo;

    public RoomService(RoomTypeRepository roomTypeRepo, RoomRepository roomRepo) {
        this.roomTypeRepo = roomTypeRepo;
        this.roomRepo = roomRepo;
    }

    public List<BasicRoomInfoDto> getAllRooms() {
        List<BasicRoomInfoDto> result = roomRepo.listAll()
                .stream()
                .map(BasicRoomInfoDto::toDto)
                .collect(Collectors.toList());

        if (result.isEmpty()) throw new NoContentException();
        return result;
    }

    public List<RoomTypeDto> getAllRoomTypes() {
        List<RoomTypeDto> result = roomTypeRepo.listAll()
                .stream()
                .map(RoomTypeDto::toDto)
                .collect(Collectors.toList());

        if (result.isEmpty()) throw new NoContentException();
        return result;
    }

    public RoomDetailsDto getRoomDetails(Long id) {
        RoomEntity entity = roomRepo.findByIdOptional(id).orElseThrow(NoContentException::new);
        return RoomDetailsDto.toDto(entity);
    }

    public String getRoomTypeDetails(Long roomTypeId, String currency, String selectedServices, Date selectedDate,
                                     Integer numberOfDays, Integer numberOfGuests, Integer selectedMonth)
    {
        JSONObject result = new JSONObject();
        getRoomTypeData(roomTypeId).forEach(pair -> result.put(pair.getFirst().toString(), pair.getSecond()));
        result.put("room_type.counted_price", getCountedPrice(roomTypeId, currency, selectedServices, numberOfDays, numberOfGuests));
        getReservationData(selectedDate, numberOfDays, selectedMonth).forEach(pair -> result.put((String) pair.getFirst(), pair.getSecond()));

        return result.toString();
    }

    private List<RoomTypeEntity> filterRoomTypes(Integer guestsNum, Integer daysNum, Date startDate) {
        if (guestsNum <= 0 || daysNum   <= 0 || startDate.before(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Invalid search parameters");
        }

        List<RoomTypeEntity> roomTypes = roomTypeRepo.findAll().stream()
                .filter(roomType -> roomType.getCapacity() >= guestsNum).toList();
        /*
          TODO: add connection to RoomReservation.OperationsAtReservations service.
           There should be also at least one room of selected type since startDate for selected number of days (daysNum).
         */
        log.info("There are " + roomTypes.size() + " rooms that match selected filters");

        return roomTypes;
    }

    private List<RoomTypeEntity> filterRoomTypes(Integer guestsNum) {
        if (guestsNum <= 0) {
            throw new IllegalArgumentException("Invalid search parameters");
        }

        List<RoomTypeEntity> roomTypes = roomTypeRepo.findAll().stream()
                .filter(roomType -> roomType.getCapacity() >= guestsNum).toList();
        log.info("There are " + roomTypes.size() + " rooms that match selected filters");

        return roomTypes;
    }

    private List<Pair<?,?>> getRoomTypeData(Long roomTypeId) {
        RoomTypeEntity roomType = roomTypeRepo.getReferenceById(roomTypeId);
        List<Pair<?,?>> result = new ArrayList<>();
        result.add(Pair.of("room_type.name", roomType.getName()));
        result.add(Pair.of("room_type.name", roomType.getName()));
        result.add(Pair.of("room_type.capacity", roomType.getCapacity()));
        result.add(Pair.of("room_type.description", roomType.getDescription()));
        result.add(Pair.of("room_type.photos", roomType.getPhotoLinks()));
        return result;
    }

    private List<Pair<?,?>> getReservationData(Date selectedDate, Integer numberOfDays, Integer selectedMonth) {
        List<Pair<?,?>> result = new ArrayList<>();
        //TODO refer to Rest API of ReservationService
        result.add(Pair.of("reservation_data", "connection not ready yet"));
        return result;
    }

    private Double getCountedPrice(Long roomTypeId, String currency, String selectedServices,
                                   Integer numberOfDays, Integer numberOfGuests) {
        //TODO refer to Rest API of PaymentService
        return roomTypeRepo.getReferenceById(roomTypeId).getBasePrice();
    }
}
