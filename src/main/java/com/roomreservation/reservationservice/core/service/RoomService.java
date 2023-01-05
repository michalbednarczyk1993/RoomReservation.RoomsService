package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.domain.RoomEntity;
import com.roomreservation.reservationservice.core.repository.IRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoomService {
    private final IRoomRepository roomRepo;

    RoomService(IRoomRepository repository) {
        this.roomRepo = repository;
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
        List<RoomEntity> roomList = filterRooms(guestsNum, daysNum, startDate);
        JSONArray result = new JSONArray();

        for (RoomEntity room : roomList) {
            JSONObject record = new JSONObject();
            record.put("name", room.getName());
            record.put("capacity", room.getCapacity());
            record.put("basePrice", room.getBasePrice());
            record.put("photo", new JSONArray()); //TODO: store an array with links to photos
            result.put(record);
        }

        return result.toList();
    }

    private List<RoomEntity> filterRooms(Integer guestsNum, Integer daysNum, Date startDate) {
        List<RoomEntity> filteredRooms = roomRepo.findAll()
                .stream()
                .filter(room -> room.getCapacity() >= guestsNum)
                //TODO: should be also at least one room of selected type since startDate for selected number of days (daysNum)
                .toList();

        if(filteredRooms.isEmpty()) {
            log.info("There are no rooms that match selected filters");
        }

        return filteredRooms;

    }
}























