# FoodChoice - Personalized Recipe Recommendation Platform

FoodChoice is a revolutionary recipe recommendation platform that empowers users to discover new recipes, tailor recommendations to their tastes, and engage with a vibrant food-loving community.

## Table of Contents
- [Tech Stacks](#tech-stacks)
- [Project Goals](#project-goals)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Achievements](#achievements)
- [Highlights](#highlights)
- [Getting Started](#getting-started)
- [Contributing](#contributing)


## Tech Stacks
- Front-End: React.js
- Back-End: Spring Boot (Java)
- Database: MySQL
- Generative AI
- Deployment: Vercel (Frontend)

## Project Goals
- Provide users with personalized recipe recommendations using machine learning algorithms.
- Foster a sense of community among food enthusiasts through a community forum.
- Enable users to discover, save, and interact with recipes.
- Enhance the cooking experience by offering ingredient substitutions and meal suggestions.

## Features
- Personalized Recipe Recommendations
- Recipe Discovery & Collection
- Community Forum for Culinary Discussions
- User Reviews & Ratings
- Culinary Profiles
- Recipe Sharing
- AI-Powered Meal Suggestions

## API Endpoints
- User Authentication
  - POST /all/register
  - POST /auth/signin

- User Profile
  - GET /user/getUser/{username}
  - PUT /user/{username}
  - GET /user/{username}/saved-recipes
  - POST /user/{username}/saved-recipes
  - DELETE /users/{username}/saved-recipes/{recipeId}
  - DELETE /user/{username}

- Recipe
  - GET /api/recipes/{recipeId}
  - GET /api/recipes
  - POST /api/recipes
  - PUT /api/recipes/{recipeId}
  - DELETE /api/recipes/{recipeId}
  
- Community Forum
  - GET /api/forum/posts
  - GET /api/forum/posts/{postId}
  - POST /api/forum/posts
  - PUT /api/forum/posts/{postId}
  - DELETE /api/forum/posts/{postId}
  - POST /api/forum/posts/{postId}/comments
  - PUT /api/forum/posts/{postId}/comments/{commentId}
  - DELETE /api/forum/posts/{postId}/comments/{commentId}


## Achievements
- Personalized recipe recommendations based on user preferences.
- Active community engagement through the community forum.
- Efficient database management using Spring Data JPA and MySQL.
- Integration of TensorFlow for machine learning-driven recommendations.

## Highlights

### Home Page
![Screenshot (103)](https://github.com/mevicky9254/FoodChoice/assets/112768362/bee88bb1-7a0c-44a4-b0b0-5d1ecc1a5ab8)

### SignUp Page
![Screenshot (98)](https://github.com/mevicky9254/FoodChoice/assets/112768362/b71c667c-417e-486d-9556-0a3a70fece7a)

### SignIn Page
![Screenshot (96)](https://github.com/mevicky9254/FoodChoice/assets/112768362/e4d22bbc-0421-4258-8c26-a767eb73a5b4)

### ChatBot
![Screenshot (99)](https://github.com/mevicky9254/FoodChoice/assets/112768362/a1546ccf-9147-4159-a400-a98663e51200)

### Recipe-Creation Form
![Screenshot (101)](https://github.com/mevicky9254/FoodChoice/assets/112768362/c72e1c33-d6b5-4c66-83e3-478669d20c54)

### Account Details
![Screenshot (102)](https://github.com/mevicky9254/FoodChoice/assets/112768362/f834a5aa-43f1-4ea4-95fa-0cb8805decd7)

## Getting Started
1. Clone the repository: **`git clone <https://github.com/mevicky9254/FoodChoice.git>`**
3. Set up the front-end and back-end environments.
4. Configure database settings in `application.properties`.
5. Run the application locally for development.

## Contributing
Contributions are welcome! Fork the repository and submit a pull request.


