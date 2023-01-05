package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/rooms")
@Controller
public class RoomController {
    private final RoomService roomService;

    RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * JSON structure:
     * {
     *     name: String,
     *     capacity: Integer,
     *     basePrice: Double,
     *     photo: href_to_data_storage
     * }
     *
     *
     * @return
     */
    @GetMapping("/all")
    public List<Object> getAllRoomTypes() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomType}/details")
    public String getRoomTypeDetails(@RequestParam String roomType) {
        return roomService.
    }
}
