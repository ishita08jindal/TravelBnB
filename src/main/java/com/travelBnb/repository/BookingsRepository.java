package com.travelBnb.repository;

import com.travelBnb.entity.BookingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<BookingsEntity, Long> {
}