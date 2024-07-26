package com.travelBnb.payload;

import com.travelBnb.entity.CountryEntity;
import com.travelBnb.entity.LocationEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PropertyDto {
    private Long id;
    @NotNull
    @Size(min = 2, message = "Should be atleast 2 characters")
    private String name;
    private int nightlyPrice;
    private int noGuests;
    private int noBedrooms;
    private int noBathrooms;
    private CountryEntity country;
    private LocationEntity location;
}
