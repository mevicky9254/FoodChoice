package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.SavedRecipe;

public interface SavedRecipeRepository extends JpaRepository<SavedRecipe, Long> {
	
}