package com.learnjava.drive_time_api.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnjava.drive_time_api.clients.RoutesClient;
import com.learnjava.drive_time_api.dto.DriveTimeResponse;

@Service
public class DriveTimeService {
    private final RoutesClient routesClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DriveTimeService(RoutesClient routesClient) {
        this.routesClient = routesClient;
    }
    public DriveTimeResponse getDriveTime(String origin, String destination) {
        String rawResponse = routesClient.fetchRoutes(origin, destination);
        String duration = extractDuration(rawResponse);
        String formattedDuration = formatDuration(duration);
        String distanceMeters = extractDistanceMeters(rawResponse);
        String distanceKilometers = toKilometers(distanceMeters);
        String distanceMiles = toMiles(distanceMeters);
        return new DriveTimeResponse(
            duration,
            formattedDuration,
            distanceMeters,
            distanceKilometers,
            distanceMiles
        );
    }

    private String extractDuration(String rawResponse) {
        if (rawResponse == null) {
            return null;
        }
        try {
            JsonNode root = objectMapper.readTree(rawResponse);
            JsonNode routes = root.path("routes");
            if (!routes.isArray() || routes.isEmpty()) {
                return null;
            }

            JsonNode durationNode = routes.get(0).path("duration");
            if (durationNode.isMissingNode()) {
                return null;
            }

            String duration = durationNode.asText(null);
            if (duration == null) {
                return null;
            }

            return duration;
        } catch (Exception ex) {
            return null;
        }
    }

    private String extractDistanceMeters(String rawResponse) {
        if (rawResponse == null) {
            return null;
        }
        try {
            JsonNode root = objectMapper.readTree(rawResponse);
            JsonNode routes = root.path("routes");
            if (!routes.isArray() || routes.isEmpty()) {
                return null;
            }

            JsonNode distanceNode = routes.get(0).path("distanceMeters");
            return distanceNode.asText(null);
        } catch (Exception ex) {
            return null;
        }
    }

    private String formatDuration(String duration) {
        if (duration == null || !duration.endsWith("s")) {
            return null;
        }

        String secondsText = duration.substring(0, duration.length() - 1);
        long seconds;
        try {
            seconds = Long.parseLong(secondsText);
        } catch (NumberFormatException ex) {
            return null;
        }

        long minutes = seconds / 60;
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;

        if (hours > 0) {
            return hours + "h " + remainingMinutes + "m";
        }
        return minutes + "m";
    }

    private String toKilometers(String distanceMeters) {
        if (distanceMeters == null) {
            return null;
        }

        try {
            double meters = Double.parseDouble(distanceMeters);
            double kilometers = meters / 1000.0;
            return String.format("%.2f", kilometers);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private String toMiles(String distanceMeters) {
        if (distanceMeters == null) {
            return null;
        }

        try {
            double meters = Double.parseDouble(distanceMeters);
            double miles = meters / 1609.34;
            return String.format("%.2f", miles);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
    
}