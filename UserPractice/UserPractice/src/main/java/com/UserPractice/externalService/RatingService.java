package com.UserPractice.externalService;

import com.UserPractice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating rating);
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable Long ratingId,Rating rating);
}
