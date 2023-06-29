package com.UserPractice.service;

import com.UserPractice.entity.Hotel;
import com.UserPractice.entity.Rating;
import com.UserPractice.entity.User;
import com.UserPractice.externalService.HotelService;
import com.UserPractice.repository.UserServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    UserServiceRepository userServiceRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user){
        User save = userServiceRepository.save(user);
        return save;
    }

    public User updateUser(Long userId,User user){
        User user1 = userServiceRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found"));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setCity(user.getCity());
        user1.setPassword(user.getPassword());

        User save = userServiceRepository.save(user1);
        return save;
    }

    public User findById(Long userId){
        User user1 = userServiceRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found"));
        Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE:8083/ratings/users/"+user1.getUserId(), Rating[].class);
        logger.info("{}",forObject);

        List<Rating> ratings = Arrays.stream(forObject).collect(Collectors.toList());

        List<Rating> ratingList = ratings.stream().map(rating -> {
      //      ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE:8082/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel =hotelService.getHotelById(rating.getHotelId());
           // logger.info(" ",forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user1.setRatings(ratingList);
        return user1;
    }

    public List<User> findAllUser(){
        List<User> all = userServiceRepository.findAll();
        return all;
    }

}
