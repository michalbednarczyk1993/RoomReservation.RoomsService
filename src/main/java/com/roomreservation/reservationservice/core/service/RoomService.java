package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.domain.RoomEntity;
import com.roomreservation.reservationservice.core.domain.RoomTypeEntity;
import com.roomreservation.reservationservice.core.repository.IPhotoLinkRepository;
import com.roomreservation.reservationservice.core.repository.IRoomRepository;
import com.roomreservation.reservationservice.core.repository.IRoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    /**
     *      * JSON structure:
     *      * {
     *      *     name: String,
     *      *     capacity: Integer,
     *      *     basePrice: Double,
     *      *     photo: href_to_data_storage
     *      * }
     * @return
     */
    public List<Object> getAllRooms(Integer guestsNum, Integer daysNum, Date startDate) {
        if (guestsNum == null || guestsNum <= 0 ||
            daysNum   == null || daysNum   <= 0 ||
            startDate == null || startDate.before(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Invalid search parameters"); //TODO: Create custom exception
        }

        List<RoomTypeEntity> roomList = filterRoomTypes(guestsNum, daysNum, startDate);
        JSONArray result = new JSONArray();

        for (RoomTypeEntity roomType : roomList) {
            JSONObject record = new JSONObject();
            record.put("name", roomType.getName());
            record.put("capacity", roomType.getCapacity());
            record.put("basePrice", roomType.getBasePrice());
            //record.put("photo", roomType.getPhotoLinks()); //TODO: store an array with links to photos
            result.put(record);
        }

        return result.toList();
    }

    public List<Object> getAllRooms(Integer guestNum) {
        return null;
    }

    /**
     *  Get all rooms types that match selected properties:
     *  - guest number is grater that provided
     *  - room is available at selected date (there is need to get data from reservation service)
     *  - get room types that does not duplicate
     *
     * @param guestsNum
     * @param daysNum
     * @param startDate
     * @return
     */
    private List<RoomTypeEntity> filterRoomTypes(Integer guestsNum, Integer daysNum, Date startDate) {
        List<RoomTypeEntity> roomTypes = roomTypeRepo.findAll().stream()
                .filter(roomType -> roomType.getCapacity() >= guestsNum).toList();
                //TODO: should be also at least one room of selected type since startDate for selected number of days (daysNum)

        if(roomTypes.isEmpty()) {
            log.info("There are no rooms that match selected filters");
        }

        return roomTypes;

    }
}























