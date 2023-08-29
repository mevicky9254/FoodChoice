package com.foodchoice.service;

import java.util.List;

import com.foodchoice.model.Recipe;

public interface MealService {
	
	 List<Recipe> getMealSuggestions(String username);
	 List<Recipe> generateWeeklyMealPlan(String username);

}
