package com.foodchoice.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    
   
    //@OneToMany(mappedBy = "recipe")
    private List<String> instructions=new ArrayList<>();
    
   
    //@OneToMany(mappedBy = "recipe")
    private List<String> ingredients = new ArrayList<>();
    
    @JsonIgnore
    @ManyToOne
    private Customer customer;
    
    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private List<Review> reviews = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private List<SavedRecipe> savedByUsers = new ArrayList<>();

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", type=" + type + ", image=" + image + ", description="
				+ description + ", instructions=" + instructions + ", ingredients=" + ingredients + "]";
	}
    
    
    
    
    
}
