import React, { useState } from 'react';

const CreatePostForm = ({ onCreatePost }) => {
  const [newPost, setNewPost] = useState({ title: '', content: '' });

  const handleSubmit = (e) => {
    e.preventDefault();
    onCreatePost(newPost);
    setNewPost({ title: '', content: '' });
  };

  return (
    <div className="create-post-form">
      <h2>Create a New Post</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Title:
          <input
            type="text"
            value={newPost.title}
            onChange={(e) => setNewPost({ ...newPost, title: e.target.value })}
          />
        </label>
        <label>
          Content:
          <textarea
            value={newPost.content}
            onChange={(e) => setNewPost({ ...newPost, content: e.target.value })}
          />
        </label>
        <button type="submit">Create Post</button>
      </form>
    </div>
  );
};

export default CreatePostForm;