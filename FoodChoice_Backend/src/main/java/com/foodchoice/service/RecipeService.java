package com.foodchoice.service;

import java.util.List;

import com.foodchoice.dto.RecipeDto;
import com.foodchoice.exception.RecipeException;
import com.foodchoice.model.Recipe;

public interface RecipeService {
	
	Recipe createRecipe(RecipeDto recipeDto,String userName)throws RecipeException;
    Recipe getRecipeById(Long id)throws RecipeException;
    List<Recipe> getRecipesByIngredients(List<String> ingredientNames)throws RecipeException;
	List<Recipe> getRecipesByType(String recipeType);
    Recipe deleteRecipe(Long id) throws RecipeException;
	List<Recipe> getRecipesByUserName(String userName) throws RecipeException;

}
