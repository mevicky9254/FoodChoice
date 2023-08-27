package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodchoice.model.ForumComment;

public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
   
}
