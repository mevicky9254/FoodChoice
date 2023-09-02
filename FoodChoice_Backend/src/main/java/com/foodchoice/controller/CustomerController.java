package com.foodchoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodchoice.config.JwtTokenProvider;
import com.foodchoice.dto.CustomerUpdateDto;
import com.foodchoice.dto.ReviewDto;
import com.foodchoice.exception.ForumPostNotFoundException;
import com.foodchoice.exception.RecipeException;
import com.foodchoice.exception.ReviewException;
import com.foodchoice.exception.UserException;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.Customer;
import com.foodchoice.model.ForumComment;
import com.foodchoice.model.Recipe;
import com.foodchoice.model.Review;
import com.foodchoice.model.SavedRecipe;
import com.foodchoice.service.CommunityForumService;
import com.foodchoice.service.RecipeService;
import com.foodchoice.service.UserService;

@RestController
@RequestMapping("/user")

public class CustomerController {

    @Autowired
    private UserService userService;
    
    @Autowired
	 private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CommunityForumService communityForumService;
    
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private JwtTokenProvider jwtProvider;

    @GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
    
    
    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer user) {
    	
        try {
        	Customer eUser=userService.getUserByUsername(user.getUsername());
        	
        	Customer newUser=null;
        	
        	if(eUser==null) {
        		
        		user.setPassword(passwordEncoder.encode(user.getPassword()));
        		user.setRole("ROLE_USER");
        		user.setUsername(user.getEmail());
        		newUser=userService.createUser(user);
        		
        	}
        	
        	return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
           
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    

    @GetMapping("/getUser/{username}")
    public ResponseEntity<Customer> getUserByUsername(@PathVariable String username) {
        try {
        	
            Customer user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("updateUser/{username}")
    public ResponseEntity<Customer> updateUserProfile(@PathVariable String username, @RequestBody CustomerUpdateDto userUpdateDto) {
        try {
            Customer updatedUser = userService.updateUserProfile(username, userUpdateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.noContent().build();
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{username}/saved-recipes")
    public ResponseEntity<List<SavedRecipe>> getSavedRecipes(@PathVariable String username) {
        try {
            List<SavedRecipe> savedRecipes = userService.getSavedRecipes(username);
            return new ResponseEntity<>(savedRecipes, HttpStatus.OK);
        } catch (UserException | RecipeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{username}/save-recipe/{recipeId}")
    public ResponseEntity<Void> saveRecipe(@PathVariable String username, @PathVariable Long recipeId) {
        try {
            userService.saveRecipe(username, recipeId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserException | RecipeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{username}/unsave-recipe/{recipeId}")
    public ResponseEntity<Void> unsaveRecipe(@PathVariable String username, @PathVariable Long recipeId) {
        try {
            userService.unsaveRecipe(username, recipeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserException | RecipeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{username}/reviews")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable String username) throws ReviewException {
        try {
            List<Review> reviews = userService.getUserReviews(username);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{username}/leave-review")
    public ResponseEntity<Void> leaveReview(@PathVariable String username, @RequestBody ReviewDto reviewDto) throws RecipeException {
        try {
            userService.leaveReview(username, reviewDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}/forum-posts")
    public ResponseEntity<List<CommunityForumPost>> getUserForumPosts(@PathVariable String username) {
        try {
            List<CommunityForumPost> forumPosts = userService.getUserForumPosts(username);
            return new ResponseEntity<>(forumPosts, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
   
   
    @GetMapping("/forum-posts/{postId}/comments")
    public ResponseEntity<List<ForumComment>> getPostComments(@PathVariable Long postId) {
        try {
            List<ForumComment> comments = userService.getPostComments(postId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (ForumPostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (RecipeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("recipe/by-type/{recipeType}")
    public ResponseEntity<List<Recipe>> getRecipesByType(@PathVariable String recipeType) {
        List<Recipe> recipes = recipeService.getRecipesByType(recipeType);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("recipe/by-ingredients")
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestParam List<String> ingredientNames) {
        try {
            List<Recipe> recipes = recipeService.getRecipesByIngredients(ingredientNames);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (RecipeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

   

    

}

