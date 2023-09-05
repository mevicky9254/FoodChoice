// ForumList.js
import {React} from 'react';
import { ForumPost } from './ForumPost';

const ForumList = ({ posts }) => {
  return (
    <div className="forum-list">
      <h2>Forum Posts</h2>
      {posts.map((post) => (
        <ForumPost key={post.id} post={post} />
      ))}
    </div>
  );
};

export default ForumList;
