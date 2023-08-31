package com.foodchoice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodchoice.exception.UserException;
import com.foodchoice.model.Recipe;
import com.foodchoice.model.SavedRecipe;
import com.foodchoice.model.User;
import com.foodchoice.repository.UserRepository;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeService recipeService;

    @Override
    public List<Recipe> getMealSuggestions(String username) throws UserException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("User not found!");
        }

        // Get the user's saved recipes
        List<SavedRecipe> savedRecipes = user.getSavedRecipes();

        // Collect the types of recipes in the saved list
        Set<String> savedRecipeTypes = new HashSet<>();
        for (SavedRecipe savedRecipe : savedRecipes) {
            Recipe recipe = savedRecipe.getRecipe();
            savedRecipeTypes.add(recipe.getType());
        }

        // Get meal suggestions based on the types of recipes in the saved list
        List<Recipe> suggestedMeals = new ArrayList<>();
        for (String recipeType : savedRecipeTypes) {
            List<Recipe> mealSuggestions = recipeService.getRecipesByType(recipeType);
            suggestedMeals.addAll(mealSuggestions);
        }

        return suggestedMeals;
    }

    @Override
    public List<Recipe> generateWeeklyMealPlan(String username) {
        // TODO: Implement the logic to generate a weekly meal plan
        return null;
    }
}
