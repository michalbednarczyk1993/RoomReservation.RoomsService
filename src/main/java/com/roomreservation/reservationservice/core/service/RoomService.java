package com.roomreservation.reservationservice.core.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.roomreservation.reservationservice.core.domain.RoomEntity;
import com.roomreservation.reservationservice.core.repository.IRoomRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Object> getAllRooms() {
        List<RoomEntity> roomList = roomRepo.findAll();
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
}























