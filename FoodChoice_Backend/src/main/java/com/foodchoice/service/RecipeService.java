package com.foodchoice.service;

import java.util.List;

import com.foodchoice.dto.RecipeDto;
import com.foodchoice.model.Recipe;

public interface RecipeService {
	
	Recipe createRecipe(RecipeDto recipeDto);
    Recipe getRecipeById(Long id);
    List<Recipe> getRecipesByCuisine(String cuisine);
    List<Recipe> getRecipesByIngredients(List<String> ingredientNames);

}
