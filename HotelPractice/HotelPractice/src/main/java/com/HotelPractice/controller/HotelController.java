package com.HotelPractice.controller;

import com.HotelPractice.entity.Hotel;
import com.HotelPractice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/save")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(hotelId, hotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long hotelId) {
        Hotel hotel = hotelService.getById(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/get")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.findAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}
