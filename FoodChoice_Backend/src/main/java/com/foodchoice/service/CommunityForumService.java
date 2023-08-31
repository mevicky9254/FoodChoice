package com.foodchoice.service;

import java.util.List;

import com.foodchoice.dto.CommunityForumPostDto;
import com.foodchoice.dto.CommunityForumPostUpdateDto;
import com.foodchoice.dto.ForumCommentDto;
import com.foodchoice.dto.ForumCommentUpdateDto;
import com.foodchoice.exception.ForumException;
import com.foodchoice.exception.UserException;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.ForumComment;

public interface CommunityForumService {
	
	CommunityForumPost createPost(String username, CommunityForumPostDto postDto) throws UserException;
    CommunityForumPost getPostById(Long id) throws ForumException;
    List<CommunityForumPost> getAllPosts();
    CommunityForumPost updatePost(String username, Long postId, CommunityForumPostUpdateDto postUpdateDto) throws UserException, ForumException;
    void deletePost(String username, Long postId) throws UserException, ForumException;
    List<ForumComment> getPostComments(Long postId) throws ForumException;
    ForumComment leaveComment(String username, Long postId, ForumCommentDto commentDto) throws UserException, ForumException;
    ForumComment updateComment(String username, Long postId, Long commentId, ForumCommentUpdateDto commentUpdateDto) throws UserException, ForumException;
    void deleteComment(String username, Long postId, Long commentId) throws UserException, ForumException;

}
