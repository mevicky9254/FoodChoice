package com.foodchoice.service;

import java.util.List;

import com.foodchoice.dto.CommunityForumPostDto;
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

public interface UserService {
	
	User createUser(User user)throws UserException;
    User getUserByUsername(String username)throws UserException;
    User updateUserProfile(String username, UserUpdateDto userUpdateDto)throws UserException;
    void deleteUser(String username)throws UserException;
    List<SavedRecipe> getSavedRecipes(String username)throws UserException, RecipeException;
    void saveRecipe(String username, Long recipeId)throws UserException, RecipeException;
    void unsaveRecipe(String username, Long recipeId)throws UserException, RecipeException;
    List<Review> getUserReviews(String username)throws UserException, ReviewException;
    void leaveReview(String username, ReviewDto reviewDto) throws UserException, RecipeException;
    List<CommunityForumPost> getUserForumPosts(String username)throws UserException;
    List<ForumComment> getPostComments(Long postId) throws ForumPostNotFoundException;
   
}
