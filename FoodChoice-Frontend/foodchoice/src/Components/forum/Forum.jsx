import React from "react";
import { Chatbot } from "../chatbot/Chatbot";
import CreatePostForm from './CreatePostForm';
import { useState } from "react";
import ForumList from './ForumList';


function Forum() {

  const [posts, setPosts] = useState([
    {
      id: 1,
      title: 'First Post',
      content: 'This is the content of the first post.',
      comments: [
        { id: 1, content: 'Comment 1', author: 'User1' },
        { id: 2, content: 'Comment 2', author: 'User2' },
      ],
    },
    // Add more posts here...
  ]);

  const createPost = (newPost) => {
    setPosts([...posts, { id: Date.now(), ...newPost, comments: [] }]);
  };

  return (
    <div>
     <h1>Forum Application</h1>
      <CreatePostForm onCreatePost={createPost} />
      <ForumList posts={posts} />
      
    </div>
  );
}

export {Forum};
