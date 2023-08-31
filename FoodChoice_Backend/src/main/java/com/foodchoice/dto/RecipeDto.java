package com.foodchoice.dto;

import java.util.List;

import com.foodchoice.model.Ingredient;

public class RecipeDto {
	
    private String title;
    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;
   
    
	public RecipeDto() {
		super();
	}

	public RecipeDto(String title, List<Ingredient> ingredients, List<String> cookingInstructions) {
		super();
		this.title = title;
		this.ingredients = ingredients;
		this.cookingInstructions = cookingInstructions;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(List<String> cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	
    
}
