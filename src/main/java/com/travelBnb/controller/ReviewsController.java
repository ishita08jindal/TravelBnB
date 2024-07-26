package com.travelBnb.controller;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.entity.ReviewsEntity;
import com.travelBnb.payload.AppUserDto;
import com.travelBnb.payload.ReviewsDto;
import com.travelBnb.repository.AppUserRepository;
import com.travelBnb.repository.PropertyRepository;
import com.travelBnb.repository.ReviewsRepository;
import com.travelBnb.service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewsController {
    private ReviewsRepository reviewsRepository;
    private ReviewsService reviewsService;
    private AppUserRepository appUserRepository;
    private PropertyRepository propertyRepository;

    public ReviewsController(ReviewsRepository reviewsRepository, ReviewsService reviewsService, AppUserRepository appUserRepository, PropertyRepository propertyRepository) {
        this.reviewsRepository = reviewsRepository;
        this.reviewsService = reviewsService;
        this.appUserRepository = appUserRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(
            @AuthenticationPrincipal AppUserEntity user,
            @RequestParam long propertyId,
            @RequestBody ReviewsEntity review,
            BindingResult result
            ){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        Optional<PropertyEntity> opProperty = propertyRepository.findById(propertyId);
        PropertyEntity property = opProperty.get();
        if(reviewsRepository.findReviewByUser(user, property)!=null) {
            return new ResponseEntity<>("review exists", HttpStatus.OK);
        }else{
            review.setAppUser(user);
            review.setProperty(property);
            reviewsRepository.save(review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
    }
}
