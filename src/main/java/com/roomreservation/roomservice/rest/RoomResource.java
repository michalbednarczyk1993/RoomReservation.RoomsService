package com.roomreservation.roomservice.rest;

import com.roomreservation.roomservice.core.dto.RoomDto;
import com.roomreservation.roomservice.core.service.RoomService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@OpenAPIDefinition(
        info = @Info(
                title = "Room Controller",
                description = "Kontroler do zarządzania pokojami",
                version = "0.1"))
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    RoomService roomService;

    @Inject
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
    public Response getAllRooms(@Positive Integer page, @Positive Integer size) {
        return Response
                .status(Response.Status.OK)
                .entity(roomService.getAllRooms(page, size))
                .build();
    }

    @Operation(description = "Zwraca listę wszystkich typów pokoi sorotowane rosnąco po wielkości pokoju")
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
        roomService.createRoom(room);
        return Response
                .status(Response.Status.OK)
                .entity("Sukces")
                .build();
    }

    @Operation(description = "Aktualizuje informacje o konkretnym pokoju")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera")
    })
    @PATCH
    public Response updateRoom(@QueryParam("id") Long id, RoomDto room) {
        roomService.updateRoom(id, room);
        return Response
                .status(Response.Status.OK)
                .entity("Sukces")
                .build();
    }

    @Operation(description = "Usuwa konkretny pokój")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera")
    })
    @DELETE
    public Response deleteRoom(@QueryParam("id") Long id) {
        roomService.deleteRoom(id);
        return Response
                .status(Response.Status.OK)
                .entity("Sukces")
                .build();
    }

    @GET
    @Path("/mock")
    public String mockedRequest() {
        return "response from room-service";
    }

}
