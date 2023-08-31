package com.foodchoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodchoice.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
   List<Recipe> findByTypeIgnoreCase(String recipeType);
    
}
