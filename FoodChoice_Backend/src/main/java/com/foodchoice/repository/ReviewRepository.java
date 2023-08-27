package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}

