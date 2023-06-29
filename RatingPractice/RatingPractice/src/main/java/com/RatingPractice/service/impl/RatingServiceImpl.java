package com.RatingPractice.service.impl;

import com.RatingPractice.entity.Rating;
import com.RatingPractice.repository.RatingRepository;
import com.RatingPractice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long ratingId, Rating rating) {
        Rating existingRating = findByRatingId(ratingId);
        existingRating.setUserId(rating.getUserId());
        existingRating.setHotelId(rating.getHotelId());
        existingRating.setRating(rating.getRating());
        return ratingRepository.save(existingRating);
    }

    @Override
    public Rating findByRatingId(Long ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found with id: " + ratingId));
    }

    @Override
    public List<Rating> findByUserId(Long userId) {
        List<Rating> byUserId = ratingRepository.findByUserId(userId);
        return byUserId;
    }

    @Override
    public List<Rating> findByHotelId(Long hotelId) {
        List<Rating> byHotelId = ratingRepository.findByHotelId(hotelId);
        return byHotelId;
    }

    @Override
    public List<Rating> findAllRating() {
        return ratingRepository.findAll();
    }
}
