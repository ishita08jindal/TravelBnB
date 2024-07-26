package com.travelBnb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

}