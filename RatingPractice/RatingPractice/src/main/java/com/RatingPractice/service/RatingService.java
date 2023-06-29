package com.RatingPractice.service;

import com.RatingPractice.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);
    Rating updateRating(Long ratingId,Rating rating);
    Rating findByRatingId(Long ratingId);
    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(Long hotelId);

    List<Rating> findAllRating();
}
