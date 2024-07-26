package com.travelBnb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ratings", nullable = false)
    private Integer ratings;

    @Column(name = "description", length = 600)
    private String description;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUserEntity appUser;

}