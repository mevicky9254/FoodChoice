package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodchoice.model.SavedRecipe;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipe, Long> {
	
}