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
    CommunityForumPost createForumPost(String username, CommunityForumPostDto postDto)throws UserException;
    CommunityForumPost updateForumPost(String username, Long postId, CommunityForumPostUpdateDto postUpdateDto) throws UnauthorizedUserException, ForumPostNotFoundException, UserException;
    void deleteForumPost(String username, Long postId) throws UserException, ForumPostNotFoundException, UnauthorizedUserException;
    List<ForumComment> getPostComments(Long postId) throws ForumPostNotFoundException;
    ForumComment leaveComment(String username, Long postId, ForumCommentDto commentDto) throws UserException, ForumPostNotFoundException;
    ForumComment updateComment(String username, Long postId, Long commentId, ForumCommentUpdateDto commentUpdateDto) throws UserException, ForumCommentNotFoundException, UnauthorizedUserException;
    void deleteComment(String username, Long postId, Long commentId) throws UserException, ForumCommentNotFoundException, UnauthorizedUserException;

}
