package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodchoice.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}

