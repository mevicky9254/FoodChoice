package com.foodchoice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ForumComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    
   
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private CommunityForumPost post;  
    
   
}
