package com.travelBnb.service;

import com.travelBnb.entity.ImageEntity;
import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.payload.ImageDto;
import com.travelBnb.repository.ImageRepository;
import com.travelBnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImageService {
    private ImageRepository imageRepository;
    private PropertyRepository propertyRepository;
    private BucketService bucketService;

    public ImageService(ImageRepository imageRepository, PropertyRepository propertyRepository, BucketService bucketService) {
        this.imageRepository = imageRepository;
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
    }

    public ImageDto uploadImage(MultipartFile file, long propertyId, String bucketName) {
        PropertyEntity property = propertyRepository.findById(propertyId).get();
        String imageUrl = bucketService.uploadFile(file, bucketName);
        ImageEntity image = dtoToEntity(file, property, imageUrl);
        ImageEntity savedImage = imageRepository.save(image);
        ImageDto dto = entityToDto(savedImage);
        return dto;
    }

    private ImageEntity dtoToEntity(MultipartFile file, PropertyEntity property, String imageUrl) {
        ImageEntity entity = new ImageEntity();
        entity.setProperty(property);
        entity.setImageUrl(imageUrl);
        return entity;
    }

    private ImageDto entityToDto(ImageEntity entity) {
        ImageDto dto = new ImageDto();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setProperty(entity.getProperty());
        return dto;
    }
}