package com.UserPractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rating {
    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;

}
