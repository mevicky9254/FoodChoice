package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    
}
