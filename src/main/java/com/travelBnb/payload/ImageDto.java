package com.travelBnb.payload;

import com.travelBnb.entity.PropertyEntity;
import lombok.Data;

@Data
public class ImageDto {

    private Long id;
    private String imageUrl;
    private PropertyEntity property;
}