package com.foodchoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodchoice.dto.CommunityForumPostDto;
import com.foodchoice.dto.CommunityForumPostUpdateDto;
import com.foodchoice.dto.ForumCommentDto;
import com.foodchoice.dto.ForumCommentUpdateDto;
import com.foodchoice.exception.ForumException;
import com.foodchoice.exception.UserException;
import com.foodchoice.model.CommunityForumPost;
import com.foodchoice.model.ForumComment;
import com.foodchoice.service.CommunityForumService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/forum")
public class CommunityForumController {

    @Autowired
    private CommunityForumService forumService;

    @PostMapping("/posts")
    public ResponseEntity<CommunityForumPost> createForumPost(@RequestBody CommunityForumPostDto postDto,
                                                              @RequestParam String username) {
        try {
            CommunityForumPost createdPost = forumService.createPost(username, postDto);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<CommunityForumPost> getForumPostById(@PathVariable Long id) {
        try {
            CommunityForumPost post = forumService.getPostById(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (ForumException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<CommunityForumPost>> getAllForumPosts() {
        List<CommunityForumPost> posts = forumService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<CommunityForumPost> updateForumPost(@RequestBody CommunityForumPostUpdateDto postUpdateDto,
                                                              @PathVariable Long postId,
                                                              @RequestParam String username) {
        try {
            CommunityForumPost updatedPost = forumService.updatePost(username, postId, postUpdateDto);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (UserException | ForumException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deleteForumPost(@PathVariable Long postId,
                                                @RequestParam String username) {
        try {
            forumService.deletePost(username, postId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserException | ForumException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<ForumComment>> getForumPostComments(@PathVariable Long postId) {
        try {
            List<ForumComment> comments = forumService.getPostComments(postId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (ForumException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ForumComment> leaveCommentOnForumPost(@RequestBody ForumCommentDto commentDto,
                                                                @PathVariable Long postId,
                                                                @RequestParam String username) {
        try {
            ForumComment comment = forumService.leaveComment(username, postId, commentDto);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (UserException | ForumException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ForumComment> updateForumPostComment(@RequestBody ForumCommentUpdateDto commentUpdateDto,
                                                               @PathVariable Long postId,
                                                               @PathVariable Long commentId,
                                                               @RequestParam String username) {
        try {
            ForumComment updatedComment = forumService.updateComment(username, postId, commentId, commentUpdateDto);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (UserException | ForumException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteForumPostComment(@PathVariable Long postId,
                                                       @PathVariable Long commentId,
                                                       @RequestParam String username) {
        try {
            forumService.deleteComment(username, postId, commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserException | ForumException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
