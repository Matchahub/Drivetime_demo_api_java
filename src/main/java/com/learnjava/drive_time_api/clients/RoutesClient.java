package com.learnjava.drive_time_api.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RoutesClient {
    @Value("${google_maps_api_key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    public String fetchRoutes(String origin, String destination) {
        String requestJson = String.format(
            "{" +
                "\"origin\": { \"address\": \"%s\" }," +
                "\"destination\": { \"address\": \"%s\" }," +
                "\"travelMode\": \"DRIVE\"" +
            "}",
            origin,
            destination
        );

        return restClient.post()
            .uri("https://routes.googleapis.com/directions/v2:computeRoutes")
            .header("X-Goog-Api-Key", apiKey)
            .header("X-Goog-FieldMask", "routes.duration,routes.distanceMeters")
            .body(requestJson)
            .retrieve()
            .body(String.class);
    }
}
