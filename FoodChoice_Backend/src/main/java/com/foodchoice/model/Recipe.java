package com.foodchoice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String type;
    private String image;
    private String description;
    private List<String> instructions;
    
    @OneToMany(mappedBy = "recipe")
    private List<Review> reviews = new ArrayList<>();
    
    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();
    
    @OneToMany(mappedBy = "recipe")
    private List<SavedRecipe> savedByUsers = new ArrayList<>();
    
    
}
