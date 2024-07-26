package com.travelBnb.controller;

import com.travelBnb.payload.CountryDto;
import com.travelBnb.repository.CountryRepository;
import com.travelBnb.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/countries")
public class AddCountryController {
    private CountryRepository countryRepository;
    private CountryService countryService;

    public AddCountryController(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(
            @Valid @RequestBody CountryDto dto,
            BindingResult result
            ){
       if(result.hasErrors()){
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
       }
       CountryDto addCountry = countryService.addCountry(dto);
       return new ResponseEntity<>(addCountry, HttpStatus.CREATED);
    }
}
