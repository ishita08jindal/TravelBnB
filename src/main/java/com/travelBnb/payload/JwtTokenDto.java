package com.travelBnb.payload;

import lombok.Data;

@Data
public class JwtTokenDto {
    private String type;
    private String token;
}
