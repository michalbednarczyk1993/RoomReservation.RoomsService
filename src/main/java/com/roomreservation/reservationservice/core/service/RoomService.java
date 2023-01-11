package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.domain.RoomTypeEntity;
import com.roomreservation.reservationservice.core.repository.IPhotoLinkRepository;
import com.roomreservation.reservationservice.core.repository.IRoomRepository;
import com.roomreservation.reservationservice.core.repository.IRoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RoomService {
    private final IRoomRepository roomRepo;
    private final IRoomTypeRepository roomTypeRepo;
    private final IPhotoLinkRepository photoLinkRepo;

    RoomService(IRoomRepository roomRepository, IRoomTypeRepository roomTypeRepository, IPhotoLinkRepository photoLinkRepository) {
        this.roomRepo = roomRepository;
        this.roomTypeRepo = roomTypeRepository;
        this.photoLinkRepo = photoLinkRepository;
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

    public String getRoomTypeDetails(Long roomTypeId) {
        RoomTypeEntity roomType = roomTypeRepo.getReferenceById(roomTypeId);
        JSONObject result = new JSONObject();

        result.put("name", roomType.getName());
        result.put("description", roomType.getDescription());
        result.put("photos", roomType.getPhotoLinks());
        // TODO: add "additional service" options
        // TODO: count price at another service

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
}
