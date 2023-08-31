package com.foodchoice.service;

import java.util.List;

import com.foodchoice.exception.UserException;
import com.foodchoice.model.Recipe;

public interface MealService {
	
	 List<Recipe> getMealSuggestions(String username) throws UserException;
	 List<Recipe> generateWeeklyMealPlan(String username);

}
