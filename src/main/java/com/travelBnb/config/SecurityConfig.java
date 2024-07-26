package com.travelBnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private JWTReqeustFilter jwtReqeustFilter;
    public SecurityConfig(JWTReqeustFilter jwtReqeustFilter) {
        this.jwtReqeustFilter = jwtReqeustFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtReqeustFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("api/v1/user/createUser","api/v1/user/login","api/v1/properties/searchProperties")
                .permitAll()
                .requestMatchers("ap1/v1/photos/uploadPhoto","api/v1/properties/addProperty","api/v1/location/addLocation","api/v1/countries/addCountry"
                        ,"/api/v1/bucket/upload/file/{bucketName}", "/api/v1/image/upload/file/{bucketName}/property/{propertyId}","/api/v1/bookings/createBookings")
                .hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated();
        return http.build(); //build here forms an object because http is a reference
    }
}
