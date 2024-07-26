package com.travelBnb.controller;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.payload.BookingsDto;
import com.travelBnb.repository.AppUserRepository;
import com.travelBnb.repository.BookingsRepository;
import com.travelBnb.repository.PropertyRepository;
import com.travelBnb.service.BookingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {
    private PropertyRepository propertyRepository;
    private BookingsRepository bookingsRepository;
    private BookingsService bookingsService;

    public BookingsController(PropertyRepository propertyRepository, BookingsRepository bookingsRepository, BookingsService bookingsService) {
        this.propertyRepository = propertyRepository;
        this.bookingsRepository = bookingsRepository;
        this.bookingsService = bookingsService;
    }

    @PostMapping("/createBookings")
    public ResponseEntity<BookingsDto> createBookings(
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUserEntity appUser,
            @RequestBody BookingsDto bookingsDto
            ){
        BookingsDto savedBookings = bookingsService.createBookings(propertyId,appUser, bookingsDto);
        return new ResponseEntity<>(savedBookings, HttpStatus.CREATED);
    }
}
