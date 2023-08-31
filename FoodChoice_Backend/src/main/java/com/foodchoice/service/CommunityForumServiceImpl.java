package com.foodchoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodchoice.dto.CommunityForumPostDto;
import com.foodchoice.dto.CommunityForumPostUpdateDto;
import com.foodchoice.dto.ForumCommentDto;
import com.foodchoice.dto.ForumCommentUpdateDto;
import com.foodchoice.exception.ForumException;
import com.foodchoice.exception.UserException;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.ForumComment;
import com.foodchoice.model.User;
import com.foodchoice.repository.CommunityForumPostRepository;
import com.foodchoice.repository.ForumCommentRepository;
import com.foodchoice.repository.UserRepository;
@Service
public class CommunityForumServiceImpl implements CommunityForumService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityForumPostRepository forumPostRepository;

    @Autowired
    private ForumCommentRepository forumCommentRepository;
    
    
    @Override
    public CommunityForumPost createPost(String username, CommunityForumPostDto postDto)
            throws UserException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("User not found!");
        }

        CommunityForumPost forumPost = new CommunityForumPost();
        forumPost.setUser(user);
        forumPost.setTitle(postDto.getTitle());
        forumPost.setContent(postDto.getContent());
        return forumPostRepository.save(forumPost);
    }
    
    

    @Override
    public CommunityForumPost getPostById(Long id) throws ForumException {
        Optional<CommunityForumPost> optionalForumPost = forumPostRepository.findById(id);
        return optionalForumPost.orElseThrow(() -> new ForumException("Forum post not found!"));
    }

    
    
    @Override
    public List<CommunityForumPost> getAllPosts() {
        return forumPostRepository.findAll();
    }

    
    
    @Override
    public CommunityForumPost updatePost(String username, Long postId, CommunityForumPostUpdateDto postUpdateDto)
            throws UserException, ForumException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("User not found!");
        }

        Optional<CommunityForumPost> optionalForumPost = forumPostRepository.findById(postId);
        CommunityForumPost forumPost = optionalForumPost.orElseThrow(() -> new ForumException("Forum post not found!"));

        if (!forumPost.getUser().equals(user)) {
            throw new ForumException("You do not have permission to edit this post.");
        }

        forumPost.setTitle(postUpdateDto.getTitle());
        forumPost.setContent(postUpdateDto.getContent());
        return forumPostRepository.save(forumPost);
    }
    
    
    

    @Override
    public void deletePost(String username, Long postId) throws UserException, ForumException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("User not found!");
        }

        Optional<CommunityForumPost> optionalForumPost = forumPostRepository.findById(postId);
        CommunityForumPost forumPost = optionalForumPost.orElseThrow(() -> new ForumException("Forum post not found!"));

        if (!forumPost.getUser().equals(user)) {
            throw new ForumException("You do not have permission to delete this post.");
        }

        forumPostRepository.delete(forumPost);
    }

    
    
    @Override
    public List<ForumComment> getPostComments(Long postId) throws ForumException {
        Optional<CommunityForumPost> optionalForumPost = forumPostRepository.findById(postId);
        CommunityForumPost forumPost = optionalForumPost.orElseThrow(() -> new ForumException("Forum post not found!"));

        return forumPost.getComments();
    }
    

    
    
    @Override
    public ForumComment leaveComment(String username, Long postId, ForumCommentDto commentDto)
            throws UserException, ForumException {
    	
    	 User user = userRepository.findByUsername(username);
         if (user == null) {
             throw new UserException("User not found!");
         }

         Optional<CommunityForumPost> optionalForumPost = forumPostRepository.findById(postId);
         if (optionalForumPost.isEmpty()) {
             throw new ForumException("Forum post not found!");
         }

         CommunityForumPost forumPost = optionalForumPost.get();

         ForumComment comment = new ForumComment();
         comment.setUser(user);
         comment.setContent(commentDto.getContent());
        

         comment.setPost(forumPost); 
         return forumCommentRepository.save(comment);
    }
    
    

    @Override
    public ForumComment updateComment(String username, Long postId, Long commentId,
                                      ForumCommentUpdateDto commentUpdateDto)
            throws UserException, ForumException {
        
    	User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("User not found!");
        }

        Optional<ForumComment> optionalComment = forumCommentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new ForumException("Comment not found!");
        }

        ForumComment comment = optionalComment.get();
        if (!comment.getUser().equals(user)) {
            throw new ForumException("You do not have permission to edit this comment.");
        }

        comment.setContent(commentUpdateDto.getContent());
        return forumCommentRepository.save(comment);
    }

    
    
    @Override
    public void deleteComment(String username, Long postId, Long commentId)
            throws UserException, ForumException {
       
    	User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserException("User not found!");
        }

        Optional<ForumComment> optionalComment = forumCommentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new ForumException("Comment not found!");
        }

        ForumComment comment = optionalComment.get();
        if (!comment.getUser().equals(user)) {
            throw new ForumException("You do not have permission to delete this comment.");
        }

        forumCommentRepository.delete(comment);
    }

   
}


