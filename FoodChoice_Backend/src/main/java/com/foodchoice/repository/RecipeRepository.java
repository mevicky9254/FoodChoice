package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
