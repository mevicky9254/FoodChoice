import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SignUp.css';

const SignUp = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [imageURL, setImageURL] = useState('');

  const navigate = useNavigate();

  const handleSignUp = async(e) => {
    e.preventDefault();
     let id=0;
     console.log(imageURL);
     let requestBody={
      id,
      firstName,
      lastName,
      email,
      password,
      imageURL

     }

     console.log(requestBody)

     try {
      const response = await fetch('http://localhost:8080/all/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json', 
        },
        body: JSON.stringify(requestBody), 
      });

      if (response.ok) {
        console.log('Signup successful');
        navigate('/signin'); 
      } else {
        
        console.error('Signup failed');
        alert('Signup failed. Please try again.'); 
      }
    } catch (error) {
      
      console.error('Error:', error);
      alert('An error occurred. Please try again later.'); 
    }

  };

  return (
    <div className="signup-container">
      <h2>Welcome To FoodChoice</h2> 
      <form onSubmit={handleSignUp} className="signup-form">
        <div className="signup-form-group">
          <label className="signup-label">First Name:</label>
          <input
            type="text"
            placeholder="Enter your first name"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
            className="signup-input"
          />
        </div>
        <div className="signup-form-group">
          <label className="signup-label">Last Name:</label>
          <input
            type="text"
            placeholder="Enter your last name"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
            className="signup-input"
          />
        </div>
        <div className="signup-form-group">
          <label className="signup-label">Email:</label>
          <input
            type="email"
            placeholder="Enter your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="signup-input"
          />
        </div>
        <div className="signup-form-group">
          <label className="signup-label">Password:</label>
          <input
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="signup-input"
          />
        </div>
        <div className="signup-form-group">
          <label className="signup-label">Image Url:</label>
          <input
            placeholder="Upload Image url "
            value={imageURL}
            onChange={(e) => setImageURL(e.target.value)}
            className="signup-input"
          />
        </div>
        <button type="submit" className="signup-button">
          Sign Up
        </button>
      </form>
    </div>
  );
};

export {SignUp};
