package com.travelBnb.payload;
import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.PropertyEntity;
import lombok.Data;

@Data
public class ReviewsDto {
    private Long id;
    private int ratings;
    private String description;
    private AppUserEntity appUser;
    private PropertyEntity property;
}
