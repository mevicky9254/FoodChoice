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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    
    
    @OneToMany(mappedBy = "user")
    private List<SavedRecipe> savedRecipes = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<CommunityForumPost> forumPosts = new ArrayList<>(); 
    
    @OneToMany(mappedBy = "user")
    private List<ForumComment> forumComments = new ArrayList<>(); 
    
   
}

