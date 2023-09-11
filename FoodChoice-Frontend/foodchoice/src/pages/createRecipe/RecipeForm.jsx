import React, { useState,useEffect } from 'react';
import './recipeform.css';
import { useNavigate } from 'react-router-dom';

const RecipeForm = () => {
  const [recipe, setRecipe] = useState({
    title: '',
    type: 'Select',
    image: '',
    description: '',
    instructions: [''],
    ingredients: [''],
  });

    const [userData, setUserData] = useState({});
    const [username, setUsername] = useState('');
    const [token, setToken] = useState('');
    
    const navigate = useNavigate();

  
    useEffect(() => {
      const storedUserDetails = localStorage.getItem('userDetails');
      const storedToken = localStorage.getItem('JWTtoken');
  
      if (storedUserDetails && storedToken) {
        const parsedUserDetails = JSON.parse(storedUserDetails);
        setUserData(parsedUserDetails);
        setUsername(parsedUserDetails.email);
        setToken(storedToken);
        

      }
    }, []);



  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRecipe({
      ...recipe,
      [name]: value,
    });
  };

  const handleIngredientChange = (e, index) => {
    const { value } = e.target;
    const ingredients = [...recipe.ingredients];
    ingredients[index] = value;
    setRecipe({
      ...recipe,
      ingredients,
    });
  };

  const handleInstructionChange = (e, index) => {
    const { value } = e.target;
    const instructions = [...recipe.instructions];
    instructions[index] = value;
    setRecipe({
      ...recipe,
      instructions,
    });
  };

  const addIngredient = () => {
    const ingredients = [...recipe.ingredients, ''];
    setRecipe({
      ...recipe,
      ingredients,
    });
  };

  const addInstruction = () => {
    const instructions = [...recipe.instructions, ''];
    setRecipe({
      ...recipe,
      instructions,
    });
  };

  const handleSubmit = async(e) => {
    e.preventDefault();
    try {
        const headers = {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        };
  
        const requestOptions = {
          method: 'POST', 
          headers: headers,
          body: JSON.stringify(recipe), 
          redirect: 'follow',
        };
  
        let response = await fetch("http://localhost:8080/recipe/create-recipe", requestOptions);

        console.log(response)
  
        if (response.ok) {
          alert('Recipe added successfully');
          navigate('/profile');
        } else {
          console.error('Failed to add recipe');
        }
      } catch (error) {
        console.error('Error:', error);
      }
      console.log("Added")


    console.log('Recipe Data:', recipe);
  };

  return (
    <div className="recipe-form-container">
      <h2>Suggest Your Favourite Recipe</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Title of Recipe:</label>
          <input
            type="text"
            id="title"
            name="title"
            value={recipe.title}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="type">Type:</label>
          <select id="type" name="type" value={recipe.type} onChange={handleInputChange}>
            <option value="Select">Select</option>
            <option value="Veg">Veg</option>
            <option value="NonVeg">NonVeg</option>
            {/* Add more options as needed */}
          </select>
        </div>
        <div>
          <label htmlFor="image">Image (URL or Upload):</label>
          <input
            type="text"
            id="image"
            name="image"
            value={recipe.image}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            name="description"
            value={recipe.description}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Ingredients:</label>
          {recipe.ingredients.map((ingredient, index) => (
            <div key={index}>
              <input
                type="text"
                value={ingredient}
                onChange={(e) => handleIngredientChange(e, index)}
              />
            </div>
          ))}
          <button type="button" onClick={addIngredient}>
            Add Ingredient
          </button>
        </div>
        <div>
          <label>Instructions:</label>
          {recipe.instructions.map((instruction, index) => (
            <div key={index}>
              <input
                type="text"
                value={instruction}
                onChange={(e) => handleInstructionChange(e, index)}
              />
            </div>
          ))}
          <button type="button" onClick={addInstruction}>
            Add Instruction
          </button>
        </div>
        <button type="submit">Post</button>
      </form>
    </div>
  );
};

export { RecipeForm };
