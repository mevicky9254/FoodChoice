// ForumPost.js
import React from 'react';
import ForumComment from './ForumComment';

const ForumPost = ({ post }) => {
  return (
    <div className="forum-post">
      <h3>{post.title}</h3>
      <p>{post.content}</p>
      <div className="comments">
        <h4>Comments</h4>
        {post.comments.map((comment) => (
          <ForumComment key={comment.id} comment={comment} />
        ))}
      </div>
    </div>
  );
};

export default ForumPost;
