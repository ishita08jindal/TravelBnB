package com.travelBnb.repository;

import com.travelBnb.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    boolean existsByName(String name);

    @Query("SELECT p FROM PropertyEntity p JOIN p.location l JOIN p.country c WHERE l.name = :locationName OR c.name = :locationName")
    List<PropertyEntity> searchProperty(@Param("locationName") String locationName);

}