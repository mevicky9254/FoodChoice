package com.foodchoice.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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
    
    @OneToMany(mappedBy = "recipe")
    private List<Instruction> instructions=new ArrayList<>();
    
    
    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private List<Review> reviews = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private List<SavedRecipe> savedByUsers = new ArrayList<>();
    
    
}
