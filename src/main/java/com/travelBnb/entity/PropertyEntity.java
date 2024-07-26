package com.travelBnb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Property")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "nightly_price")
    private Integer nightlyPrice;

    @Column(name = "no_bedrooms")
    private Integer noBedrooms;

    @Column(name = "no_bathrooms")
    private Integer noBathrooms;

    @Column(name = "no_guests")
    private Integer noGuests;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}