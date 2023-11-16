package com.roomreservation.roomservice.rest;

import com.roomreservation.roomservice.core.domain.TestEntity;
import com.roomreservation.roomservice.core.service.RetryableRequestService;
import com.roomreservation.roomservice.core.service.TestService;
import io.quarkus.arc.profile.UnlessBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@OpenAPIDefinition(
        info = @Info(
                title = "Room Test Controller",
                description = "Testowy kontroler z pakietu Rooms Service",
                version = "0.1"))
@Path("/test-rooms")
@UnlessBuildProfile("prod")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TestResource {

    @Inject
    @ConfigProperty(name = "service.room.url")
    String userServiceUrl;

    final TestService testService;

    final RetryableRequestService retryService;

    @Inject
    public TestResource(TestService testService, RetryableRequestService retryService) {
        this.testService = testService;
        this.retryService = retryService;
    }

    @Operation(description = "Odpowiedź z serwisu")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "500", description = "Błąd serwera")
    })
    @GET
    @Path("/hello")
    public Response hello() {
        return Response
                .status(Response.Status.OK)
                .entity("Sukces")
                .build();
    }

    @Operation(description = "Zapisuje dane testowe w bazie")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
    })
    @POST
    @Path("/save")
    public Response saveData() {
        testService.saveRecord();
        return Response
                .status(Response.Status.OK)
                .entity("Sukces")
                .build();
    }

    @Operation(description = "Odczytuje dane testowe w bazie, które mają parametr text równy przesłanemu")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
    })
    @GET
    public Response readData(@QueryParam("text") @NotEmpty String text) {
        String data = testService.readData(text);
        return Response
                .status(Response.Status.OK)
                .entity(data)
                .build();
    }

    @Operation(description = "Testuje działanie mapowania wyjątków")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Brak dostępnych zasobów spełniających kryteria"),
            @APIResponse(responseCode = "400", description = "Nieprawidłowe dane w żądaniu"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
    })
    @GET
    @Path("/throw")
    public Response throwException(@QueryParam("exception") @NotEmpty String exception) {
        testService.throwException(exception);
        return Response
                .status(Response.Status.OK)
                .entity("Żaden wyjątek nie został rzucony")
                .build();
    }

    @Operation(description = "Testuje połączenie z innym mikroserwisem")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sukces"),
            @APIResponse(responseCode = "500", description = "Błąd serwera"),
            @APIResponse(responseCode = "503", description = "Błąd połączenia z zewnętrznym serwisem")
    })
    @GET
    @Path("/networking")
    public Response testNetworking() {
        String url = userServiceUrl + "/mock";
        return retryService.executeWithRetry(url, String.class);
    }
}
