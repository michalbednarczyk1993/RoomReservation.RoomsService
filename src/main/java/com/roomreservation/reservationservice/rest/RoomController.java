package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
     * Return all room types available in hotel with selected criteria.
     * In case when guestNumber is equal to null, then default value (1) is provided.
     * In case when daysNumber or startDate is equal to null, then system will return all possible values
     * that suit to guestNumber condition.
     * JSON structure:
     * {
     *     room_type.name: String,
     *     room_type.capacity: Integer,
     *     room_type.basePrice: Double,
     *     room_type.main_photo: href_to_data_storage
     * }
     *
     * @param guestsNum - number of guests that will be placed in one room;
     * @param daysNum - number of days that will take reservation;
     * @param startDate - first day of reservation (YYYY-MM-DD);
     * @return list of JSON objects that represent room types which satisfy conditions
     */
    @GetMapping("/all")
    public List<Object> getAllRoomTypes(
            @RequestBody(required = false) Integer guestsNum,
            @RequestBody(required = false) Integer daysNum,
            @RequestBody(required = false) Date startDate) {
        List<Object> result;
        try {
             result = roomService.getAllRooms(guestsNum, daysNum, startDate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
        }

        return result;
    }

    /**
     * Wyświetl szczegółowe informacje o typie pokoju
     *
     * Przypadek umożliwia [użytkownikowi] przejrzenie większej ilości informacji na temat [typu pokoju].
     * W efekcie [użytkownik] jest przekierowany do innego widoku gdzie ma dostęp do większej ilości informacji na
     * temat wybranego [typu pokoju].
     * System prezentuje następujące informację: przejrzeć poglądowe zdjęcia dla wybranego [typu pokoju],
     * przeczytać opis [typu pokoju], przejrzeć i wybrać możliwe [formy pobytu], zobaczyć obliczoną [cenę pobytu],
     * przejrzeć dostępne terminy. [(Obliczenie ceny pobytu)] jest wykonywane automatycznie przy zmianie zaznaczonych
     * [form pobytu].
     */
    @GetMapping("/{roomTypeId}/details")
    public String getRoomTypeDetails(
            @PathVariable Long roomTypeId,
            @RequestBody String currency,
            @RequestBody String chosenOption) {

        String result = roomService.getRoomTypeDetails(roomTypeId);

        return null;
    }

}
