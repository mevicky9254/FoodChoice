package com.foodchoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodchoice.model.ForumComment;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
   
}
