package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.CommunityForumPost;

public interface CommunityForumPostRepository extends JpaRepository<CommunityForumPost, Long> {
   
}