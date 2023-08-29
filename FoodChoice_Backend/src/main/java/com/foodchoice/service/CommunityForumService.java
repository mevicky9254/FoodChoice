package com.foodchoice.service;

import java.util.List;

import com.foodchoice.dto.CommunityForumPostDto;
import com.foodchoice.dto.CommunityForumPostUpdateDto;
import com.foodchoice.dto.ForumCommentDto;
import com.foodchoice.dto.ForumCommentUpdateDto;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.ForumComment;

public interface CommunityForumService {
	
	CommunityForumPost createPost(String username, CommunityForumPostDto postDto);
    CommunityForumPost getPostById(Long id);
    List<CommunityForumPost> getAllPosts();
    CommunityForumPost updatePost(String username, Long postId, CommunityForumPostUpdateDto postUpdateDto);
    void deletePost(String username, Long postId);
    List<ForumComment> getPostComments(Long postId);
    ForumComment leaveComment(String username, Long postId, ForumCommentDto commentDto);
    ForumComment updateComment(String username, Long postId, Long commentId, ForumCommentUpdateDto commentUpdateDto);
    void deleteComment(String username, Long postId, Long commentId);

}
