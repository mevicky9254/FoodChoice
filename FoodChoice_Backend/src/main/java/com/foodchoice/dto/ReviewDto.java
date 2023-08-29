package com.foodchoice.dto;

public class ReviewDto {
	
	 private Long recipeId;
	 private String comment;
	 private int rating;
	 
	 public ReviewDto() {
		super();
	 }

	public ReviewDto(Long recipeId, String comment, int rating) {
		super();
		this.recipeId = recipeId;
		this.comment = comment;
		this.rating = rating;
	}

	

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	 
}
