package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
    
}