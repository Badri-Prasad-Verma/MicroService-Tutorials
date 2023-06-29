package com.UserPractice.externalService;

import com.UserPractice.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Service
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotelById(@PathVariable Long hotelId);


}
