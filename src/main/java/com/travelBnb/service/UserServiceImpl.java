package com.travelBnb.service;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.payload.AppUserDto;
import com.travelBnb.payload.LoginDto;
import com.travelBnb.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private AppUserRepository appUserRepository;
    private JwtService jwtService;
    public UserServiceImpl(AppUserRepository appUserRepository, JwtService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto createUser(AppUserDto dto) {
        AppUserEntity entity = dtoToEntity(dto);
        //saving entity
        AppUserEntity createdAppUser = appUserRepository.save(entity);
        AppUserDto auo = entityToDto(createdAppUser);
        return auo;
    }

    @Override
    public ResponseEntity<?> existingAppUser(AppUserDto dto) {
        if(appUserRepository.existsByUsername(dto.getUsername())){
            return new ResponseEntity<>("Error:username already exists", HttpStatus.BAD_REQUEST);
        }if(appUserRepository.existsByEmail(dto.getEmail())){
            return new ResponseEntity<>("Error:email already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createUser(dto), HttpStatus.CREATED);
    }


    AppUserEntity dtoToEntity(AppUserDto dto) {
        AppUserEntity entity = new AppUserEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        return entity;
    }
    AppUserDto entityToDto(AppUserEntity entity) {
        AppUserDto auo = new AppUserDto();
        auo.setId(entity.getId());
        auo.setName(entity.getName());
        auo.setEmail(entity.getEmail());
        auo.setRole(entity.getRole());
        auo.setUsername(entity.getUsername());
        auo.setPassword(entity.getPassword());
        return auo;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUserEntity> opUserName = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUserName.isPresent()){
            AppUserEntity appUser = opUserName.get();
            if( BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())){
                String token =jwtService.generateToken(appUser);
                return token;
            }
        }
        return null;
    }

}
