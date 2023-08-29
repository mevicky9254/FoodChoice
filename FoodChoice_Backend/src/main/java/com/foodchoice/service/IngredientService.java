package com.foodchoice.service;

import java.util.List;

import com.foodchoice.model.Ingredient;

public interface IngredientService {

	List<Ingredient> suggestSubstitutions(String ingredientName);	
}
