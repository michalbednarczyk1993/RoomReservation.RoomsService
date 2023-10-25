package com.roomreservation.roomservice.rest;

import com.roomreservation.roomservice.core.dto.BasicRoomInfoDto;
import com.roomreservation.roomservice.core.dto.RoomDto;
import com.roomreservation.roomservice.core.service.RoomService;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Room Controller",
                description = "Kontroler do zarządzania pokojami",
                version = "0.1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    private final RoomService roomService;

    public RoomResource(RoomService roomService) {
        this.roomService = roomService;
    }


    @Operation(description = "Zwraca listę wszystkich pokoi")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "500", description = "Błąd serwera")
    })
    @GET
    public Response getAllRooms() {
        return Response
                .status(Response.Status.OK)
                .entity(roomService.getAllRooms())
                .build();
    }

    @Operation(description = "Zwraca listę wszystkich typów pokoi")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "500", description = "Błąd serwera")
    })
    @GET
    public Response getAllRoomTypes() {
        return Response
                .status(Response.Status.OK)
                .entity(roomService.getAllRoomTypes())
                .build();
    }

    @Operation(description = "Zwraca szczegółowe informacje na temat konkretnego pokoju")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera")
    })
    @GET
    public Response getRoom(@NotNull @QueryParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(roomService.getRoomDetails(id))
                .build();
    }

    @Operation(description = "Tworzy nowy pokój")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
    })
    @POST
    public Response createRoom(@NotNull RoomDto room) {
        return Response
                .status(Response.Status.NOT_IMPLEMENTED)
                .entity("Usługa jeszcze nie jest gotowa")
                .build();
    }

    @Operation(description = "Aktualizuje informacje o konkretnym pokoju")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
            @APIResponse(responseCode = "501", description = "Usługa jeszcze nie jest gotowa")
    })
    @PATCH
    public Response updateRoom(@QueryParam("id") Long id, RoomDto room) {
        return Response
                .status(Response.Status.NOT_IMPLEMENTED)
                .entity("Usługa jeszcze nie jest gotowa")
                .build();
    }

    @Operation(description = "Usuwa konkretny pokój")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
            @APIResponse(responseCode = "501", description = "Usługa jeszcze nie jest gotowa")
    })
    @DELETE
    public Response deleteRoom(@QueryParam("id") Long id) {
        return Response
                .status(Response.Status.NOT_IMPLEMENTED)
                .entity("Usługa jeszcze nie jest gotowa")
                .build();
    }

    @GET
    @Path("/mock")
    public String mockedRequest() {
        return "response from room-service";
    }


//            /**
//             * Return all room types available in hotel with selected criteria in JSON format.
//             * In case when guestNumber is equal to null, then default value (1) is provided.
//             * In case when daysNumber or startDate is equal to null, then system will return all possible values
//             * that suit to guestNumber condition.
//             * JSON structure:
//             * {
//             *     room_type.name: String,
//             *     room_type.capacity: Integer,
//             *     room_type.basePrice: Double,
//             *     room_type.main_photo: href_to_data_storage
//             * }
//             *
//             * @param guestsNum - number of guests that will be placed in one room;
//             * @param daysNum - number of days that will take reservation;
//             * @param startDate - first day of reservation (YYYY-MM-DD);
//             * @return list of JSON objects that represent room types which satisfy conditions
//             */
//    @ApiOperation(
//            "Return all room types available in hotel with selected criteria in JSON format.\n" +
//            "In case when guestNumber is equal to null, then default value (1) is provided.\n" +
//            "In case when daysNumber or startDate is equal to null, then system will return all possible values\n" +
//            "that suit to guestNumber condition.\n" +
//            "JSON structure:\n" +
//            "{\n" +
//            "    room_type.name: String,\n" +
//            "    room_type.capacity: Integer,\n" +
//            "    room_type.basePrice: Double,\n" +
//            "    room_type.main_photo: href_to_data_storage")
//    @GetMapping("")
//    public List<Object> getAllRoomTypes(
//            @RequestBody(required = false) Integer guestsNum,
//            @RequestBody(required = false) Integer daysNum,
//            @RequestBody(required = false) Date startDate) {
//        List<Object> result;
//        try {
//             result = roomService.getAllRooms(guestsNum, daysNum, startDate);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
//        }
//
//        return result;

//    }

//    /**
//     * Return detail information about selected room type in JSON format.
//     * In case of currency will be null, then default value (PLN) is provided.
//     * In case of selectedServices will be null none of them will be counted in.
//     * In case of selectedDate will be null, then first possible day will be chosen.
//     * In case of numberOfDays will be null, then default value (1) will be provided.
//     * In case of selectedMonth will be null, then month of first possible date will be chosen.
//     * INFO: Service could return selected_date that differ from provided selectedDate
//     * if those was not available for this room type.
//     * JSON structure:
//     * {
//     *      room_type.name: room type name
//     *      room_type.capacity: room type capacity
//     *      room_type.description: room type description
//     *      room_type.photos: list of hrefs to data storage with room type photo
//     *      room_type.counted_price: total price of reservation
//     *      room_type.selected_date: selected date of stay (YYYY-MM-DD)
//     *      room_type.number_of_days: number of days chosen for stay
//     *      room_type.free_dates: list of comma separated free dates (in selected_month and next) of selected rooms
//     * }
//     *
//     * @param roomTypeId - id of selected room type
//     * @param currency - currency in which user want to pay for reservation. Possible values: PLN, EUR, USD, CHF
//     * @param selectedServices - comma separated extra paid services:
//     *                      bb - breakfast included into price
//     *                      di - dinner included into price
//     *                      allIn - everything included into price
//     *                      clean - everyday cleaning service included into price
//     *                      sport - sport equipment rental included into price
//     * @param selectedDate - selected date of stay (YYYY-MM-DD)
//     * @param numberOfDays - number of days chosen for stay
//     * @param selectedMonth - selected month to return "free_dates"
//     * @return JSON object that represent detailed information about selected room type
//     */
//    @GetMapping("/{roomTypeId}/details")
//    public String getRoomTypeDetails(
//            @PathVariable Long roomTypeId,
//            @RequestBody(required = false) String currency,
//            @RequestBody(required = false) String selectedServices,
//            @RequestBody(required = false) Date selectedDate,
//            @RequestBody(required = false) Integer numberOfDays,
//            @RequestBody(required = false) Integer numberOfGuests,
//            @RequestBody(required = false) Integer selectedMonth) {
//        return roomService.getRoomTypeDetails(
//                roomTypeId,
//                currency,
//                selectedServices,
//                selectedDate,
//                numberOfDays,
//                numberOfGuests,
//                selectedMonth);
//    }
}
