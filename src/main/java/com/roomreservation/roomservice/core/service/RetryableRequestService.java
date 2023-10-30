package com.roomreservation.roomservice.core.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class RetryableRequestService {
    @Inject
    @ConfigProperty(name = "app.retry.max.count")
    int maxRetryCount;

    @Inject
    @ConfigProperty(name = "app.retry.delay.ms")
    int retryDelay;

    @Inject
    @RestClient
    RetryableRestClient retryableRestClient;

    public <T> Response executeWithRetry(String url, Class<T> responseType) {
        int retryCount = 0;
        String message = "";
        Response response = null;

        while (retryCount < maxRetryCount) {
            try {
                response = retryableRestClient.get(new URI(url));
                if (response.getStatus() >= 200 && response.getStatus() < 300) {
                    break;
                }
            } catch (WebApplicationException e) {
                System.out.println("Server error, retrying...: " + e.getMessage());
                message = e.getMessage();
            } catch (Exception e) {
                System.out.println("Client error, retrying...: " + e.getMessage());
                message = e.getMessage();
            }

            retryCount++;
            if (retryCount < maxRetryCount) {
                try {
                    TimeUnit.MILLISECONDS.sleep(retryDelay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (response != null && response.getStatus() >= 200 && response.getStatus() < 300) {
            System.out.println("Successful response: " + response.readEntity(responseType));
        } else {
            System.out.println("Failed to get a successful response after " + retryCount + " retries.");
            throw new WebApplicationException(message);
        }
        return response;
    }

    @ApplicationScoped
    @RestClient
    public interface RetryableRestClient {
        @GET
        Response get(URI url);
    }
}
