package com.HotelPractice.service;

import com.HotelPractice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    Hotel updateHotel(Long hotelId,Hotel hotel);
    Hotel getById(Long hotelId);
    List<Hotel> findAllHotels();
}
