import React, { useState } from 'react';
import { Link } from 'react-router-dom'; 
import { useNavigate } from 'react-router-dom';
import './SignIn.css';

const SignIn = () => {
  const [email, setEmail] = useState('');
  const [Password, setPassword] = useState('');
  const [userDetails, setUserDetails] = useState({}); 
  const navigate=useNavigate();

  const handleSignIn = async(e) => {
    e.preventDefault();
    // Implement your authentication logic here
    console.log("email - "+ email)
    console.log("password - "+Password)

    const username = email;
    const password = Password;
    
     const authHeader = 'Basic ' + btoa(username + ':' + password);
     console.log(authHeader);
    
    
     try {
      const response = await fetch('http://localhost:8080/auth/signin', {
        method: 'GET',
        headers: {
          'Authorization': authHeader
        }
      });

      if (response.ok) {
        const token = response.headers.get('Authorization');

        if (token) {
          localStorage.setItem('JWTtoken', token);
          console.log('Token stored:', token);
        } else {
          console.error('Token not found in response headers');
          throw new Error('Failed to fetch user details');
        }


        // Continue to fetch user details
        
        const headers = {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json', 
        };
        
      
        const requestOptions = {
          method: 'GET',
          headers: headers,
          redirect: 'follow'
        };
      

        const userResponse = await fetch("http://localhost:8080/user/getUser/vikash@gmail.com",requestOptions )

        if (userResponse.ok) {
          const user = await userResponse.json();
          console.log('User details:', user);

          // Store user details in state
          setUserDetails(user);
          localStorage.setItem("userDetails", JSON.stringify(user));
          // alert("Signin Successfull");
          navigate("/");
         
          setTimeout(() => {
            window.location.reload();
          }, 200);
  
        } else {
          console.error('Failed to fetch user details');
          throw new Error('Failed to fetch user details');
        }
      } else {
        throw new Error('Failed to fetch user details');
      }
    } catch (error) {
      console.error('Error:', error);
      // Handle errors and display appropriate messages
    }
   
    

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
            value={Password}
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
