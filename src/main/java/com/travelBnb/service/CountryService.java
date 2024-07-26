package com.travelBnb.service;

import com.travelBnb.entity.CountryEntity;
import com.travelBnb.payload.CountryDto;
import com.travelBnb.repository.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryDto addCountry(CountryDto dto) {
        CountryEntity entity = dtoToEntity(dto);
        CountryEntity addingCountry = countryRepository.save(entity);
        CountryDto cdo = entityToDto(addingCountry);
        return cdo;
    }
    CountryEntity dtoToEntity(CountryDto dto) {
        CountryEntity entity = new CountryEntity();
        entity.setName(dto.getName());
        return entity;
    }
    CountryDto entityToDto(CountryEntity entity) {
        CountryDto cdo = new CountryDto();
        cdo.setId(entity.getId());
        cdo.setName(entity.getName());
        return cdo;
    }
}
