package com.RatingPractice.controller;

import com.RatingPractice.entity.Rating;
import com.RatingPractice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating createdRating = ratingService.createRating(rating);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long ratingId, @RequestBody Rating rating) {
        Rating updatedRating = ratingService.updateRating(ratingId, rating);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long ratingId) {
        Rating rating = ratingService.findByRatingId(ratingId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId) {
        List<Rating> ratings = ratingService.findByUserId(userId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable Long hotelId) {
        List<Rating> ratings = ratingService.findByHotelId(hotelId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.findAllRating();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
