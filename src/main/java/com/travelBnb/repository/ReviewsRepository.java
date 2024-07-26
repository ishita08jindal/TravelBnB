package com.travelBnb.repository;

import com.travelBnb.entity.AppUserEntity;
import com.travelBnb.entity.PropertyEntity;
import com.travelBnb.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {
    @Query("select r from ReviewsEntity r where r.appUser=:user and r.property=:property")
    ReviewsEntity findReviewByUser(@Param("user")AppUserEntity user, @Param("property") PropertyEntity property);
}