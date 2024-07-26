package com.travelBnb.controller;

import com.travelBnb.payload.LocationDto;
import com.travelBnb.repository.LocationRepository;
import com.travelBnb.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {
    private LocationRepository locationRepository;
    private LocationService locationService;

    public LocationController(LocationRepository locationRepository, LocationService locationService) {
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }
    @PostMapping("/addLocation")
    public ResponseEntity<?> addLocation(@Valid @RequestBody LocationDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        LocationDto addedLocation = locationService.addLocation(dto);
        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
    }
}
