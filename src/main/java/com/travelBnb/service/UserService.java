package com.travelBnb.service;

import com.travelBnb.payload.AppUserDto;
import com.travelBnb.payload.LoginDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public AppUserDto createUser(AppUserDto dto);
    public ResponseEntity<?> existingAppUser(AppUserDto dto);

    public String verifyLogin(LoginDto loginDto);
}
