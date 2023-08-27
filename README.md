# FoodChoice - Personalized Recipe Recommendation Platform

FoodChoice is a revolutionary recipe recommendation platform that empowers users to discover new recipes, tailor recommendations to their tastes, and engage with a vibrant food-loving community.

## Table of Contents
- [Tech Stacks](#tech-stacks)
- [Project Goals](#project-goals)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Achievements](#achievements)
- [Getting Started](#getting-started)
- [Contributing](#contributing)


## Tech Stacks
- Front-End: React.js
- Back-End: Spring Boot (Java)
- Database: MySQL
- Machine Learning: TensorFlow (for recipe recommendations)
- Deployment:

## Project Goals
- Provide users with personalized recipe recommendations using machine learning algorithms.
- Foster a sense of community among food enthusiasts through a community forum.
- Enable users to discover, save, and interact with recipes.
- Enhance the cooking experience by offering ingredient substitutions and meal suggestions.

## Features
- Personalized Recipe Recommendations
- Recipe Discovery & Collection
- Community Forum for Culinary Discussions
- Ingredient Substitutions
- User Reviews & Ratings
- Culinary Profiles
- Recipe Sharing
- AI-Powered Meal Suggestions
- Interactive Meal Planner
- Seasonal Ingredient Highlights
- Global Recipe Exchange

## API Endpoints
- User Authentication
  - POST /api/auth/signup
  - POST /api/auth/login

- User Profile
  - GET /api/users/{username}
  - PUT /api/users/{username}
  - GET /api/users/{username}/saved-recipes
  - POST /api/users/{username}/saved-recipes
  - DELETE /api/users/{username}/saved-recipes/{recipeId}

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

## Getting Started
1. Clone the repository: **`git clone <https://github.com/yourusername/explorenature.git>`**
3. Set up the front-end and back-end environments.
4. Configure database settings in `application.properties`.
5. Run the application locally for development.

## Contributing
Contributions are welcome! Fork the repository and submit a pull request.


