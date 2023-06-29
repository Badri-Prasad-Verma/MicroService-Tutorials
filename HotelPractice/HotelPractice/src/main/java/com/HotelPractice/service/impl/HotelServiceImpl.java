package com.HotelPractice.service.impl;

import com.HotelPractice.entity.Hotel;
import com.HotelPractice.repository.HotelRepository;
import com.HotelPractice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        Hotel save = hotelRepository.save(hotel);
        return save;
    }

    @Override
    public Hotel updateHotel(Long hotelId, Hotel hotel) {
        Hotel hotel1 = hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException("Hotel Id not found"));
        hotel1.setName(hotel.getName());
        hotel1.setLocation(hotel.getLocation());
        hotel1.setAbout(hotel.getAbout());
        Hotel save = hotelRepository.save(hotel1);
        return save;
    }

    @Override
    public Hotel getById(Long hotelId) {
        Hotel hotel1 = hotelRepository.findById(hotelId).orElseThrow(() -> new EntityNotFoundException("Hotel Id not found"));
        return hotel1;
    }

    @Override
    public List<Hotel> findAllHotels() {
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }
}
