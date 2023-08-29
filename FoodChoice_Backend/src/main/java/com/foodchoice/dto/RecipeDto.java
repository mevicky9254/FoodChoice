package com.foodchoice.dto;

import java.util.List;

public class RecipeDto {
	
    private String title;
    private List<String> ingredients;
    private List<String> cookingInstructions;
    private String cuisine;
    
	public RecipeDto() {
		super();
	}

	public RecipeDto(String title, List<String> ingredients, List<String> cookingInstructions, String cuisine) {
		super();
		this.title = title;
		this.ingredients = ingredients;
		this.cookingInstructions = cookingInstructions;
		this.cuisine = cuisine;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getCookingInstructions() {
		return cookingInstructions;
	}

	public void setCookingInstructions(List<String> cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
    
}
