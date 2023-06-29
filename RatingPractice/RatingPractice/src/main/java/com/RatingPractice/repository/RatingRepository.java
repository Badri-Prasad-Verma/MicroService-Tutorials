package com.RatingPractice.repository;

import com.RatingPractice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByHotelId(Long hotelId);

    List<Rating> findByUserId(Long userId);
}
