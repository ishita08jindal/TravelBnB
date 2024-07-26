package com.travelBnb.payload;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.PropertyEntity;
import lombok.Data;

@Data
public class FavoriteDto {

    private Long id;
    private Boolean status;
    private PropertyEntity property;
    private AppUserEntity appUser;
}