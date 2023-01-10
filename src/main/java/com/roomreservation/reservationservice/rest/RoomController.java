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
     * Wyświetl listę typów pokoi
     *
     * Przypadek umożliwia [użytkownikowi] wykonanie zapytania do [systemu] o listę dostępnych w hotelu [typach pokoi].
     * W efekcie system pobiera listę [typów pokoi] spełniających podane kryteria wyszukiwania. Lista ta jest skonstruowana
     * w taki sposób, że każdy z elementów zawiera zdjęcie poglądowe [typu pokoju], informacje o maksymalnej [ilości osób],
     * [cenie bazowej typu pokoju] i odnośnik do kolejnego przypadku użycia - [(Wyświetl szczegółowe informacje o pokoju)].
     * Istnieje możliwość zawężenia wyszukiwania tak aby spełniało podane kryteria: [ilość osób], [ilość nocy],
     * [data rozpoczęcia]. W przypadku pominięcia filtrów podawane są ich [domyśle wartości].
     *
     * Return List of JSON
     * if guestNumber is equal to null, then default value (1) is provided
     * if (daysNumber or startDate) is equal to null, then system will return all possible values
     *
     * Output JSON structure:
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
        List<Object> result;
        if (guestsNum == null) guestsNum = 1;

        try {
            if (daysNum == null || startDate == null)
                result = roomService.getAllRooms(guestsNum);
            else
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
     * temat wybranego [typu pokoju]. System prezentuje następujące informację: przejrzeć poglądowe zdjęcia dla wybranego
     * [typu pokoju], przeczytać opis [typu pokoju], przejrzeć i wybrać możliwe [formy pobytu], zobaczyć obliczoną
     * [cenę pobytu], przejrzeć dostępne terminy. [(Obliczenie ceny pobytu)] jest wykonywane automatycznie przy zmianie
     * zaznaczonych [form pobytu].
     */
//    @GetMapping("/{roomType}/details")
//    public String getRoomTypeDetails(@RequestParam String roomType) {
//        return roomService.
//    }

}
