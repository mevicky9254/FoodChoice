package com.foodchoice.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @JsonIgnore
    private String username;
    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password")
    @NotNull
    private String password;
   
   
    @JsonIgnore
    private String role;
    

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<SavedRecipe> savedRecipes = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CommunityForumPost> forumPosts = new ArrayList<>(); 
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ForumComment> forumComments = new ArrayList<>();
	
   
}

