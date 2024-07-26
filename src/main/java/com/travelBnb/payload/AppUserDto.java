package com.travelBnb.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AppUserDto {
    private Long id;

    @NotNull
    @Size(min = 2, message = "Should be atleast 2 characters")
    private String name;

    @NotNull
    @Size(min = 3, message = "Should be atleast 3 characters")
    private String username;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 4, message = "Should be atleast 4 characters")
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Email
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @Size(min = 2, message = "Should be atleast 2 characters") String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 2, message = "Should be atleast 2 characters") String name) {
        this.name = name;
    }

    public @NotNull @Size(min = 3, message = "Should be atleast 3 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 3, message = "Should be atleast 3 characters") String username) {
        this.username = username;
    }

    public @NotNull @Size(min = 4, message = "Should be atleast 4 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 4, message = "Should be atleast 4 characters") String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }
}
