package com.UserPractice.controller;

import com.UserPractice.entity.User;
import com.UserPractice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get


    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable Long userId) {
        logger.info("Get Single User Handler: UserController");
//        logger.info("Retry count: {}", retryCount);

        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    //creating fall back  method for circuit breaker


    public ResponseEntity<User> ratingHotelFallback(Long userId, Exception ex) {
//        logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        User user = User.builder().email("dummy@gmail.com").name("Dummy").userId(3L).build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }


    //all user get
    @GetMapping("/find")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.findAllUser();
        return ResponseEntity.ok(allUser);
    }
}
