package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.domain.RoomTypeEntity;
import com.roomreservation.reservationservice.core.repository.IRoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RoomService {
    private final IRoomTypeRepository roomTypeRepo;

    RoomService(IRoomTypeRepository roomTypeRepository) {
        this.roomTypeRepo = roomTypeRepository;
    }

    public List<Object> getAllRooms(Integer guestsNum, Integer daysNum, Date startDate) {
        List<RoomTypeEntity> roomList;

        if (guestsNum == null) guestsNum = 1;
        if (daysNum == null || startDate == null)
            roomList = filterRoomTypes(guestsNum);
        else
            roomList = filterRoomTypes(guestsNum, daysNum, startDate);

        JSONArray result = new JSONArray();

        for (RoomTypeEntity roomType : roomList) {
            JSONObject record = new JSONObject();
            record.put("room_type.name", roomType.getName());
            record.put("room_type.capacity", roomType.getCapacity());
            record.put("room_type.basePrice", roomType.getBasePrice());
            record.put("room_type.main_photo", roomType.getMainPhoto());
            result.put(record);
        }

        return result.toList();
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
}
