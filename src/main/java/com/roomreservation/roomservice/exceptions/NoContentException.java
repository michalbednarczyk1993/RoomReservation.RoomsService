package com.roomreservation.roomservice.exceptions;


import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class NoContentException extends WebApplicationException {

    public NoContentException() {
        super(Response.status(Response.Status.NO_CONTENT)
                .entity("")
                .type("application/json")
                .build());
    }
}
