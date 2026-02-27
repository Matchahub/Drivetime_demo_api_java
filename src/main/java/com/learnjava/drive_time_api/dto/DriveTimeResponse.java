//The DTO (data transfer object) carries data between processes.
//The below code is a simple java object that represents the JSON structure that gets sent to the frontend.
//In other words, the DTO carries data back to user in a formatted way, as opposed to just the raw response.

package com.learnjava.drive_time_api.dto;

public class DriveTimeResponse {
    private String duration;
    private String formattedDuration;
    private String distanceMeters;
    private String distanceKilometers;
    private String distanceMiles;

    // Constructor
    public DriveTimeResponse(String duration, String formattedDuration, String distanceMeters, String distanceKilometers, String distanceMiles) {
        this.duration = duration;
        this.formattedDuration = formattedDuration;
        this.distanceMeters = distanceMeters;
        this.distanceKilometers = distanceKilometers;
        this.distanceMiles = distanceMiles;
    }

    //Below are the getters; no setters as no data input from frontend expected.

    public String getDuration() {
        return duration;
    }

    public String getFormattedDuration() {
        return formattedDuration;
    }

    public String getDistanceMeters() {
        return distanceMeters;
    }


    public String getDistanceKilometers() {
        return distanceKilometers;
    }

    public String getDistanceMiles() {
        return distanceMiles;
    }
}