package com.foodchoice.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodchoice.dto.RecipeDto;
import com.foodchoice.exception.RecipeException;
import com.foodchoice.model.Ingredient;
import com.foodchoice.model.Instruction;
import com.foodchoice.model.Recipe;
import com.foodchoice.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	

    @Autowired
    private RecipeRepository recipeRepo;

    
   
    	@Override
    	public Recipe createRecipe(RecipeDto recipeDto) throws RecipeException {
    	    Recipe recipe = new Recipe();
    	    recipe.setTitle(recipeDto.getTitle());
    	    recipe.setType(recipeDto.getType());
    	    recipe.setImage(recipeDto.getImage());
    	    recipe.setDescription(recipeDto.getDescription());
    	    
    	    List<String> ingredientNames = recipeDto.getIngredients();
    	    List<String> instructionSteps = recipeDto.getInstructions();
    	   
    	    List<Ingredient> ingredients = new ArrayList<>();
    	    for (String ingredientName : ingredientNames) {
    	        Ingredient ingredient = new Ingredient();
    	        ingredient.setName(ingredientName);
    	        ingredient.setRecipe(recipe);
    	        ingredients.add(ingredient);
    	    }
    	    
    	  
    	    List<Instruction> instructions = new ArrayList<>();
    	    for (String instructionStep : instructionSteps) {
    	        Instruction instruction = new Instruction();
    	        instruction.setInstruction(instructionStep);
    	        instruction.setRecipe(recipe);
    	        instructions.add(instruction);
    	    }
    	    
    	    recipe.setIngredients(ingredients);
    	    recipe.setInstructions(instructions);
    	    
    	    return recipeRepo.save(recipe);
    	}

    
    

    @Override
    public Recipe getRecipeById(Long id) throws RecipeException {
        Optional<Recipe> opRecipe = recipeRepo.findById(id);
        if (opRecipe.isEmpty()) {
            throw new RecipeException("Recipe not found with ID: " + id);
        }
        return opRecipe.get();
    }

    
   
    @Override
    public List<Recipe> getRecipesByIngredients(List<String> ingredientNames) throws RecipeException {
//        List<Recipe> recipes = recipeRepo.findByIngredientsIgnoreCase(ingredientNames);
//        if (recipes.isEmpty()) {
//            throw new RecipeException("No recipes found with the given ingredients.");
//        }
        return null;
    }


    @Override
    public List<Recipe> getRecipesByType(String recipeType) {
    	
    	return recipeRepo.findByTypeIgnoreCase(recipeType);
       
    }
    
    public Recipe deleteRecipe(Long id) throws RecipeException{
    	
    	  Optional<Recipe> opRecipe = recipeRepo.findById(id);
          if (opRecipe.isEmpty()) {
              throw new RecipeException("Recipe not found with ID: " + id);
          }
         
          recipeRepo.deleteById(id);
          
          return opRecipe.get();
    }
    

}
