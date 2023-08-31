package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodchoice.model.CommunityForumPost;

@Repository
public interface CommunityForumPostRepository extends JpaRepository<CommunityForumPost, Long> {
   
}