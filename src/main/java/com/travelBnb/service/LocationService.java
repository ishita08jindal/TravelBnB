package com.travelBnb.service;

import com.travelBnb.entity.LocationEntity;
import com.travelBnb.payload.LocationDto;
import com.travelBnb.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private LocationRepository  locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationDto addLocation(LocationDto dto) {
        LocationEntity entity = dtoToEntity(dto);
        LocationEntity addedLocation = locationRepository.save(entity);
        return entityToDto(addedLocation);
    }

    private LocationEntity dtoToEntity(LocationDto dto) {
        LocationEntity entity = new LocationEntity();
        entity.setName(dto.getName());
        // Add other fields as necessary
        return entity;
    }

    private LocationDto entityToDto(LocationEntity entity) {
        LocationDto ldo = new LocationDto();
        ldo.setId(entity.getId());
        ldo.setName(entity.getName());
        // Add other fields as necessary
        return ldo;
    }
}
