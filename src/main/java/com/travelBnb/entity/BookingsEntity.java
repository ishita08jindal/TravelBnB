package com.travelBnb.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "bookings")
public class BookingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "total_nights", nullable = false)
    private Integer totalNights;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUserEntity appUser;

}