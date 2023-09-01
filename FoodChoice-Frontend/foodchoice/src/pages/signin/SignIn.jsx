import React, { useState } from 'react';
import { Link } from 'react-router-dom'; // Import Link for navigation
import './SignIn.css';

const SignIn = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSignIn = (e) => {
    e.preventDefault();
    // Implement your authentication logic here
  };

  return (
    <div className="auth-container">
      <h2>FoodChoice</h2> 
      <h3>SignIn</h3>
      
      <form onSubmit={handleSignIn}>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            placeholder="Enter your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button className='sigin-button' type="submit">Sign In</button>
      </form>
      <p>
        Don't have an account?{' '}
        <Link to="/signup">Sign Up</Link> {/* Link to Sign Up page */}
      </p>
    </div>
  );
};

export { SignIn };
