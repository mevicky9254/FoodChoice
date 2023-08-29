package com.foodchoice.service;

import java.util.List;

import com.foodchoice.dto.ReviewDto;
import com.foodchoice.model.Review;

public interface ReviewService {
	
	Review leaveReview(String username, ReviewDto reviewDto);
    List<Review> getReviewsByRecipe(Long recipeId);

}
