package com.roomreservation.roomservice.rest;

import com.roomreservation.roomservice.core.service.RetryableRequestService;
import com.roomreservation.roomservice.core.service.TestService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class TestResource {

    @Inject
    @ConfigProperty(name = "service.room.url")
    String roomServiceUrl;

    final TestService testService;

    final RetryableRequestService retryService;

    public TestResource(TestService testService, RetryableRequestService retryService) {
        this.testService = testService;
        this.retryService = retryService;
    }

    @POST
    @Path("/test/save")
    @Operation(description = "Zapisuje dane testowe w bazie")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
    })
    public Response saveData() {
        testService.saveRecord();
        return Response
                .status(Response.Status.OK)
                .entity("Sukces")
                .build();
    }

    @GET
    @Path("/test/read")
    @Operation(description = "Odczytuje ostatnio zapisane dane testowe w bazie")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
    })
    public Response readData() {
        Object data = testService.readData();
        return Response
                .status(Response.Status.OK)
                .entity("Sukces" + data)
                .build();
    }

    @GET
    @Path("/testNetworking")
    @Operation(description = "Testuje połączenie z innym mikroserwisem")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
            @APIResponse(responseCode = "503", description = "Błąd połączenia z zewnętrznym serwisem")
    })
    public Response testNetworking() {
        String url = roomServiceUrl + "/mock";
        return retryService.executeWithRetry(url, String.class);
    }
}
