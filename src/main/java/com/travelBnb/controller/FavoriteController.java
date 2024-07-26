package com.travelBnb.controller;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.FavoriteEntity;
import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.repository.FavoriteRepository;
import com.travelBnb.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/favorites")
public class FavoriteController {
    private FavoriteRepository favoriteRepository;
    private PropertyRepository propertyRepository;

    public FavoriteController(FavoriteRepository favoriteRepository, PropertyRepository propertyRepository) {
        this.favoriteRepository = favoriteRepository;
        this.propertyRepository = propertyRepository;
    }
    @PostMapping("/addFavorite")
    public ResponseEntity<FavoriteEntity>  addFavorite(
            @AuthenticationPrincipal AppUserEntity user,
            @RequestParam long propertyId,
            @RequestBody FavoriteEntity favorite
            ){
        PropertyEntity property = propertyRepository.findById(propertyId).get();
        favorite.setAppUser(user);
        favorite.setProperty(property);
        FavoriteEntity savedEntity = favoriteRepository.save(favorite);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
}
