package com.roomreservation.roomservice.exceptions;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOGGER = Logger.getLogger(ConstraintViolationExceptionMapper.class.getName());

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOGGER.log(Level.INFO, exception.toString());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Nieprawidłowe dane w żądaniu")
                .type("application/json")
                .build();
    }
}
