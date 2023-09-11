import React, { useEffect, useState } from 'react';
import './Profile.css';
import { Link } from 'react-router-dom';

const Profile = () => {
  const [recipes, setRecipes] = useState([]);
  const [username, setUsername] = useState('');
  const [token, setToken] = useState('');

  useEffect(() => {
    const storedUserDetails = localStorage.getItem('userDetails');
    const storedToken = localStorage.getItem('JWTtoken');

    if (storedUserDetails && storedToken) {
      const parsedUserDetails = JSON.parse(storedUserDetails);
      setUsername(parsedUserDetails.email);
      setToken(storedToken);
    }
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const headers = {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        };

        const requestOptions = {
          method: 'GET',
          headers: headers,
          redirect: 'follow'
        };

        const url = `http://localhost:8080/recipe/recipes/${username}`;
        const response = await fetch(url, requestOptions);

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const recipesData = await response.json();
        setRecipes(recipesData);
        console.log(recipesData);
      } catch (error) {
        console.error("Error:", error);
      }
    };

    if (token && username) {
      fetchData();
    }
  }, [token, username]);

  return (
    <>
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
      </div>

      <div className='posts'>
        <h1 className='recipe-list-title'>Your Recipes</h1>
        <div>
          {recipes.map((recipe) => (
            <div key={recipe.id} className='recipe-item'>
              <div className='left'>
                <img src={recipe.image} alt={recipe.title} />
                {recipe.description && (
                  <p><strong>Description:</strong> {recipe.description}</p>
                )}
                {recipe.type && (
                  <p><strong>Type:</strong> {recipe.type}</p>
                )}
              </div>

              <div className='right'>
                <h2 className='recipe-title'>{recipe.title}</h2>
                <div className='recipe-ingredients'>
                  <h4>Ingredients:</h4>
                  <ul className='ingredients-list'>
                    {recipe.ingredients.map((ingredient, index) => (
                      <li key={index} className='ingredient-item'>{ingredient}</li>
                    ))}
                  </ul>
                </div>
                <div className='recipe-instructions'>
                  <h4>Instructions:</h4>
                  <ol className='instructions-list'>
                    {recipe.instructions.map((instruction, index) => (
                      <li key={index} className='instruction-item'>{instruction}</li>
                    ))}
                  </ol>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export { Profile };
