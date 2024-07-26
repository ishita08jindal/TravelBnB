package com.travelBnb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length=100)
    private String name;

    @Column(name = "username", nullable = false, unique = true, length=100)
    private String username;

    @Column(name = "email", nullable = false, unique = true,length=100)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    private String role;

}