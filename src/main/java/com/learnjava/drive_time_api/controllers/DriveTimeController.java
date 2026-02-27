package com.learnjava.drive_time_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnjava.drive_time_api.dto.DriveTimeResponse;
import com.learnjava.drive_time_api.services.DriveTimeService;

@RestController
public class DriveTimeController {
    private final DriveTimeService driveTimeService;

    public DriveTimeController(DriveTimeService driveTimeService) {
        this.driveTimeService = driveTimeService;
    }

    @GetMapping("/drivetime")
    public DriveTimeResponse driveTime(
        //origin and destination must be defined by user
        @RequestParam(required = true) String origin, 
        @RequestParam(required = true) String destination
    ) {
        return driveTimeService.getDriveTime(origin, destination);
    }
}