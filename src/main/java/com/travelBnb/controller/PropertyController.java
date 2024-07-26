package com.travelBnb.controller;

import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.payload.PropertyDto;
import com.travelBnb.repository.AppUserRepository;
import com.travelBnb.repository.CountryRepository;
import com.travelBnb.repository.LocationRepository;
import com.travelBnb.repository.PropertyRepository;
import com.travelBnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private LocationRepository locationRepository;

    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository, CountryRepository countryRepository, LocationRepository locationRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<?> addProperty(
            @RequestBody PropertyDto dto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return propertyService.existingProperty(dto);
    }
    @GetMapping("/searchProperties")
    public ResponseEntity<List<PropertyEntity>> searchProperties(@RequestParam String name){
        List<PropertyEntity> properties = propertyRepository.searchProperty(name);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}

