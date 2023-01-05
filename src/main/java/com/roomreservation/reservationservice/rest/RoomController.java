package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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
    public List<Object> getAllRoomTypes(
            @RequestBody(required = false) Integer guestsNum,
            @RequestBody(required = false) Integer daysNum,
            @RequestBody(required = false) Date startDate) {
        if (guestsNum == null || guestsNum == 0) {
            guestsNum = 1;
        }
        return roomService.getAllRooms(guestsNum, daysNum, startDate);
    }

    @GetMapping("/{roomType}/details")
    public String getRoomTypeDetails(@RequestParam String roomType) {
        return roomService.
    }
}
