package com.roomreservation.roomservice.core.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.net.URI;

@RegisterRestClient
public interface RetryableRestClient {
    @GET
    Response get(URI url);
}

