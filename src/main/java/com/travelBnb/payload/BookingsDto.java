package com.travelBnb.payload;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.PropertyEntity;
import lombok.Data;

@Data
public class BookingsDto {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private int price;
    private PropertyEntity property;
    private AppUserEntity appUser;
    private Integer totalNights;
}
