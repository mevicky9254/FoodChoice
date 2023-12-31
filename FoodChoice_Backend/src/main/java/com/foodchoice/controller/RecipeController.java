package com.foodchoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodchoice.config.JwtTokenProvider;
import com.foodchoice.dto.RecipeDto;
import com.foodchoice.exception.RecipeException;
import com.foodchoice.model.Recipe;
import com.foodchoice.service.RecipeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    
    @Autowired
	private JwtTokenProvider jwtProvider;

    @PostMapping("/create-recipe")
	public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDto recipeDto, HttpServletRequest request) {
	    try {
	        String jwtToken = request.getHeader("Authorization");
	      
	        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
	            jwtToken = jwtToken.substring(7); 
	            
	            String userName = jwtProvider.getEmailFromJwtToken(jwtToken);
	            
	            System.out.println("User name for jwt" +userName);
	            
	            Recipe createdRecipe = recipeService.createRecipe(recipeDto, userName);
	            return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
	        } else {
	        	
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	        }
	    } catch (RecipeException e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
   
    
    
    @GetMapping("/recipes/{userName}")
    public ResponseEntity<List<Recipe>> getRecipesByUserName(@PathVariable String userName) throws RecipeException{
    	
    	List<Recipe> recipeList=recipeService.getRecipesByUserName(userName);
    	
    	return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }
    

    @GetMapping("recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (RecipeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    

    @GetMapping("recipe/by-type/{recipeType}")
    public ResponseEntity<List<Recipe>> getRecipesByType(@PathVariable String recipeType) {
        List<Recipe> recipes = recipeService.getRecipesByType(recipeType);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("recipe/by-ingredients")
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestParam List<String> ingredientNames) {
        try {
            List<Recipe> recipes = recipeService.getRecipesByIngredients(ingredientNames);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (RecipeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    @DeleteMapping("recipe/delete/{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable Long id) throws RecipeException{
    	
    	Recipe recipe=recipeService.deleteRecipe(id);
    	
    	return new ResponseEntity<>(recipe, HttpStatus.OK);
    }
    
   
}
