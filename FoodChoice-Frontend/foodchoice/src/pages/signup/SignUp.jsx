import React, { useState } from 'react';
import './SignUp.css';

const SignUp = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSignUp = (e) => {
    e.preventDefault();
    // Implement your sign-up logic here
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
        <button type="submit" className="signup-button">
          Sign Up
        </button>
      </form>
    </div>
  );
};

export {SignUp};
