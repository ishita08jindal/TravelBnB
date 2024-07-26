package com.travelBnb.service;

import com.travelBnb.controller.BookingsController;
import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.BookingsEntity;
import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.payload.BookingsDto;
import com.travelBnb.repository.BookingsRepository;
import com.travelBnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookingsService {
    private BookingsRepository  bookingsRepository;
    private PropertyRepository propertyRepository;
    private PDFService pdfService;
    private BucketService bucketService;
    private TwilioService twilioService;

    public BookingsService(BookingsRepository bookingsRepository, PropertyRepository propertyRepository, PDFService pdfService, BucketService bucketService, TwilioService twilioService) {
        this.bookingsRepository = bookingsRepository;
        this.propertyRepository = propertyRepository;
        this.pdfService = pdfService;
        this.bucketService = bucketService;
        this.twilioService = twilioService;
    }

    public BookingsDto createBookings(long propertyId, AppUserEntity user, BookingsDto bookingsDto) {
        PropertyEntity property = propertyRepository.findById(propertyId).get();
        bookingsDto.setProperty(property);
        bookingsDto.setAppUser(user);
        int nightlyPrice = property.getNightlyPrice();
        int totalPrice = nightlyPrice * bookingsDto.getTotalNights();
        int gstAmount = (totalPrice * 18) / 100;
        int finalPrice = totalPrice + gstAmount;
        bookingsDto.setPrice(finalPrice);
        BookingsEntity booking = dtoToEntity(bookingsDto);
        BookingsEntity savedBooking = bookingsRepository.save(booking);
        boolean b = pdfService.generatePDF("E://TravelBnb//travelBnb//Bookings//" + "Booking-Confirmation-ID" + savedBooking.getId() + ".pdf", bookingsDto);
        if (b){
            try {
                MultipartFile file = bucketService.convert("E://TravelBnb//travelBnb//Bookings//" + "Booking-Confirmation-ID" + savedBooking.getId() + ".pdf");
                String uploadedFileUrl = bucketService.uploadFile(file, "aws0805");
                System.out.println(uploadedFileUrl);
                //for sms
                String smsId = twilioService.sendSms(bookingsDto.getMobile(), "Your booking is confirmed..Click for details: " + uploadedFileUrl);
                System.out.println(smsId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        BookingsDto dto = entityToDto(savedBooking);
        return dto;
    }

    private BookingsEntity dtoToEntity(BookingsDto bookingsDto) {
        BookingsEntity booking = new BookingsEntity();
        booking.setName(bookingsDto.getName());
        booking.setEmail(bookingsDto.getEmail());
        booking.setMobile(bookingsDto.getMobile());
        booking.setPrice(bookingsDto.getPrice());
        booking.setTotalNights(bookingsDto.getTotalNights());
        booking.setProperty(bookingsDto.getProperty());
        booking.setAppUser(bookingsDto.getAppUser());
        return booking;
    }

    private BookingsDto entityToDto(BookingsEntity booking) {
        BookingsDto dto = new BookingsDto();
        dto.setId(booking.getId());
        dto.setName(booking.getName());
        dto.setEmail(booking.getEmail());
        dto.setMobile(booking.getMobile());
        dto.setTotalNights(booking.getTotalNights());
        dto.setPrice(booking.getPrice());
        dto.setProperty(booking.getProperty());
        dto.setAppUser(booking.getAppUser());
        return dto;
    }
}


