package com.travelBnb.controller;

import com.travelBnb.payload.AppUserDto;
import com.travelBnb.payload.JwtTokenDto;
import com.travelBnb.payload.LoginDto;
import com.travelBnb.repository.AppUserRepository;
import com.travelBnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;
    private AppUserRepository appUserRepository;

   // Constructor injection ensures that UserService and AppUserRepository are provide when UserController is instantiated.
    public UserController(UserService userService,
                          AppUserRepository appUserRepository) {
    // Dependencies are assigned through the constructor, promoting immutability.
    // Immutability refers to the concept where an object's state cannot be modified after it has been created.
        this.userService = userService;
        this.appUserRepository = appUserRepository;
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(
            @RequestBody AppUserDto dto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }
        ResponseEntity<?> createUser = userService.existingAppUser(dto);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody  LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if(token!=null){
            JwtTokenDto jwtToken =new JwtTokenDto();
            jwtToken.setType("JWT Token");
            jwtToken.setToken(token);
            return  new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Invalid token", HttpStatus.OK);
    }
}
