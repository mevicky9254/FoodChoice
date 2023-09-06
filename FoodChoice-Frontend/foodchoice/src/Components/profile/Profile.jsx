import React from 'react';
import './Profile.css';
import { Link } from 'react-router-dom';

const Profile = () => {
  return (
    <div className='profile-container'>
      <div className='profile'>
        <div className='profile-image'>
          <img
            src='https://b.zmtcdn.com/data/pictures/chains/5/110155/53f46f3c1ba67002bfc583883bc73dba_o2_featured_v2.jpg'
            alt=''
          />
          <div className='create-recipe-button'>
            <Link to="/create-recipe">
              <button>Create Recipe</button>
            </Link>
          </div>
        </div>
      </div>

      <div className='posts'>
        <h2 className='recipe-list'>Your Recipes</h2>
        <div>
          {/* Will render the list of recipes here */}
        </div>
      </div>
    </div>
  );
};

export { Profile };
