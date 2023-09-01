package com.foodchoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodchoice.dto.CommunityForumPostDto;
import com.foodchoice.dto.CommunityForumPostUpdateDto;
import com.foodchoice.dto.ForumCommentDto;
import com.foodchoice.dto.ForumCommentUpdateDto;
import com.foodchoice.dto.ReviewDto;
import com.foodchoice.dto.UserUpdateDto;
import com.foodchoice.exception.ForumCommentNotFoundException;
import com.foodchoice.exception.ForumPostNotFoundException;
import com.foodchoice.exception.RecipeException;
import com.foodchoice.exception.UnauthorizedUserException;
import com.foodchoice.exception.UserException;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.ForumComment;
import com.foodchoice.model.Recipe;
import com.foodchoice.model.Review;
import com.foodchoice.model.SavedRecipe;
import com.foodchoice.model.User;
import com.foodchoice.repository.CommunityForumPostRepository;
import com.foodchoice.repository.ForumCommentRepository;
import com.foodchoice.repository.RecipeRepository;
import com.foodchoice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserRepository uRepo;
	@Autowired
	private RecipeRepository recipeRepo;
	@Autowired
	private CommunityForumPostRepository forumPostRepo;
	
	
	
	
	@Override
	public User createUser(User user)throws UserException {
		
		Optional<User> opUser=uRepo.findById(user.getId());
		if(!opUser.isEmpty()) {
			throw new UserException("User already exists !");
		}else {
			uRepo.save(user);
		}
		
		return user;
	
	}

	
	
	@Override
	public User getUserByUsername(String username)throws UserException {
		
		User user =uRepo.findByUsername(username);
		
		return user;
	}

	
	
	@Override
	public User updateUserProfile(String username, UserUpdateDto userUpdateDto)throws UserException {
		
		User user =uRepo.findByUsername(username);
		
		if(user==null) {
			throw new UserException("Invalid UserName!");
		}
		
		
		user.setFirstName(userUpdateDto.getFirstName());
		user.setLastName(userUpdateDto.getLastName());
		user.setEmail(userUpdateDto.getEmail());
		user.setPassword(userUpdateDto.getPassword());
		
		return user;
	}

	
	
	@Override
	public void deleteUser(String username) throws UserException {
		
     User user =uRepo.findByUsername(username);
		
		if(user==null) {
			throw new UserException("Invalid UserName!");
		}
		
	 uRepo.deleteById(user.getId());
		
		
	}
	
	

	@Override
	public List<SavedRecipe> getSavedRecipes(String username) throws UserException,RecipeException {
		
      User user =uRepo.findByUsername(username);
		
		if(user==null) {
			throw new UserException("Invalid UserName!");
		}
		
		 List<SavedRecipe> list=user.getSavedRecipes();
		 
		 if(list.isEmpty()) {
			 throw new RecipeException("List is empty !");
		 }
		 
		 return list;
		
	}

	
	
	@Override
	public void saveRecipe(String username, Long recipeId) throws UserException, RecipeException {
		
		 User user = uRepo.findByUsername(username);
	        if (user == null) {
	            throw new UserException("User not found!");
	        }

	        Optional<Recipe> opRecipe = recipeRepo.findById(recipeId);
	        if (opRecipe.isEmpty()) {
	            throw new RecipeException("Recipe not found with ID: " + recipeId);
	        }

	        Recipe recipe = opRecipe.get();
	        
	        
	        SavedRecipe savedRecipe = new SavedRecipe();
	        savedRecipe.setRecipe(recipe);
	        savedRecipe.setUser(user);
	        user.getSavedRecipes().add(savedRecipe);
	        
	        uRepo.save(user);	 
			 
	}
	
	

	@Override
	public void unsaveRecipe(String username, Long recipeId) throws UserException, RecipeException {
		
		User user = uRepo.findByUsername(username);
	    if (user == null) {
	        throw new UserException("User not found!");
	    }

	    Optional<Recipe> opRecipe = recipeRepo.findById(recipeId);
	    if (opRecipe.isEmpty()) {
	        throw new RecipeException("Recipe not found with ID: " + recipeId);
	    }

	    Recipe recipe = opRecipe.get();

	    
	    List<SavedRecipe> savedRecipes = user.getSavedRecipes();
	    savedRecipes.removeIf(savedRecipe -> savedRecipe.getRecipe().getId().equals(recipeId));

	    uRepo.save(user);
		
	}
	
	

	@Override
	public List<Review> getUserReviews(String username) throws UserException {
		User user = uRepo.findByUsername(username);
	    if (user == null) {
	        throw new UserException("User not found!");
	    }

	    return user.getReviews();
	}

	
	
	
	@Override
	public void leaveReview(String username, ReviewDto reviewDto) throws UserException, RecipeException {
		 User user = uRepo.findByUsername(username);
		    if (user == null) {
		        throw new UserException("User not found!");
		    }

		    Optional<Recipe> opRecipe = recipeRepo.findById(reviewDto.getRecipeId());
		    if (opRecipe.isEmpty()) {
		        throw new RecipeException("Recipe not found with ID: " + reviewDto.getRecipeId());
		    }

		    Recipe recipe = opRecipe.get();

		    Review review = new Review();
		    review.setComment(reviewDto.getComment());
		    review.setRating(reviewDto.getRating());
		    review.setUser(user);
		    review.setRecipe(recipe);

		    recipe.getReviews().add(review);
		    user.getReviews().add(review);

		    uRepo.save(user);
		    recipeRepo.save(recipe);
		
	}

	
	
	@Override
	public List<CommunityForumPost> getUserForumPosts(String username) throws UserException {
		
		 User user = uRepo.findByUsername(username);
		    if (user == null) {
		        throw new UserException("User not found!");
		    }

		    return user.getForumPosts();
	}

	
	
	
	@Override
	public List<ForumComment> getPostComments(Long postId) throws ForumPostNotFoundException {
	    Optional<CommunityForumPost> opForumPost = forumPostRepo.findById(postId);
	    try {
			if (opForumPost.isEmpty()) {
			    throw new ForumPostNotFoundException("Forum post not found with ID: " + postId);
			}
		} catch (ForumPostNotFoundException e) {
			e.printStackTrace();
		}

	    CommunityForumPost forumPost = opForumPost.get();
	    return forumPost.getComments();
	}

	

}
