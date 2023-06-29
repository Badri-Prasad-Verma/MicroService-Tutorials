package com.UserPractice.repository;

import com.UserPractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServiceRepository extends JpaRepository<User,Long> {
}
