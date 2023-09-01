package com.foodchoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodchoice.dto.CommunityForumPostUpdateDto;
import com.foodchoice.dto.ForumCommentDto;
import com.foodchoice.dto.ForumCommentUpdateDto;
import com.foodchoice.dto.ReviewDto;
import com.foodchoice.dto.UserUpdateDto;
import com.foodchoice.exception.ForumCommentNotFoundException;
import com.foodchoice.exception.ForumPostNotFoundException;
import com.foodchoice.exception.RecipeException;
import com.foodchoice.exception.ReviewException;
import com.foodchoice.exception.UnauthorizedUserException;
import com.foodchoice.exception.UserException;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.ForumComment;
import com.foodchoice.model.Review;
import com.foodchoice.model.SavedRecipe;
import com.foodchoice.model.User;
import com.foodchoice.service.CommunityForumService;
import com.foodchoice.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CommunityForumService communityForumService;

    @GetMapping("/hello")
    public String getHello() {
    	return "hello";
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
    	
        try {
        	User eUser=userService.getUserByUsername(user.getUsername());
        	
        	User newUser=null;
        	
        	if(eUser==null) {
        		
        		user.setPassword(passwordEncoder.encode(user.getPassword()));
        		user.setRole("ROLE_USER");
        		newUser=userService.createUser(user);
        		
        	}
        	
        	return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
           
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUserProfile(@PathVariable String username, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            User updatedUser = userService.updateUserProfile(username, userUpdateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{username}")
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

   

    

}

