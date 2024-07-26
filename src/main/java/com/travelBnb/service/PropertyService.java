package com.travelBnb.service;

import com.travelBnb.entity.CountryEntity;
import com.travelBnb.entity.LocationEntity;
import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.payload.AppUserDto;
import com.travelBnb.payload.PropertyDto;
import com.travelBnb.repository.CountryRepository;
import com.travelBnb.repository.LocationRepository;
import com.travelBnb.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private LocationRepository locationRepository;
    public PropertyService(PropertyRepository propertyRepository, CountryRepository countryRepository, LocationRepository locationRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
    }

    public ResponseEntity<?> addProperty(PropertyDto dto) {
        PropertyEntity entity = dtoToEntity(dto);
        PropertyEntity propertyDetails = propertyRepository.save(entity);
        PropertyDto pdo = entityToDto(propertyDetails);
        return new ResponseEntity<>(pdo, HttpStatus.CREATED);
    }

       public ResponseEntity<?> existingProperty(PropertyDto dto) {
        if (propertyRepository.existsByName(dto.getName())) {
            return new ResponseEntity<>("Error: Property already exists", HttpStatus.BAD_REQUEST);
        }
        return addProperty(dto);
    }

    PropertyEntity dtoToEntity(PropertyDto dto) {
        PropertyEntity entity = new PropertyEntity();
        entity.setName(dto.getName());
        entity.setNightlyPrice(dto.getNightlyPrice());
        entity.setNoBathrooms(dto.getNoBathrooms());
        entity.setNoGuests(dto.getNoGuests());
        entity.setNoBedrooms(dto.getNoBedrooms());
        entity.setCountry(dto.getCountry());
        entity.setLocation(dto.getLocation());
        return entity;
    }

    PropertyDto entityToDto(PropertyEntity entity) {
        PropertyDto pdo = new PropertyDto();
        pdo.setId(entity.getId());
        pdo.setName(entity.getName());
        pdo.setNightlyPrice(entity.getNightlyPrice());
        pdo.setNoBathrooms(entity.getNoBathrooms());
        pdo.setNoGuests(entity.getNoGuests());
        pdo.setNoBedrooms(entity.getNoBedrooms());
        Optional<CountryEntity> byIdCountry = countryRepository.findById(entity.getCountry().getId());
        pdo.setCountry(byIdCountry.get());
        Optional<LocationEntity> byIdLocation = locationRepository.findById(entity.getLocation().getId());
        pdo.setLocation(byIdLocation.get());
        return pdo;
    }
}
