package com.foodchoice.service;

import java.util.List;

import com.foodchoice.model.Ingredient;
import com.foodchoice.model.Recipe;

public interface SeasonalService {

	List<Ingredient> getSeasonalIngredients();
    List<Recipe> getRecipesWithSeasonalIngredients();
}
