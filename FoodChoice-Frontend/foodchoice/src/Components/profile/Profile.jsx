import React, { useState, useEffect } from 'react';
import './Profile.css';
import { useNavigate } from 'react-router-dom';

const Profile = () => {
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

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({ ...userData, [name]: value });
  };

  const handleDeleteAccount = async () => {
    try {
      const headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };

      const requestOptions = {
        method: 'DELETE', 
        headers: headers,
        redirect: 'follow',
      };

      let response = await fetch(`http://localhost:8080/user/delete/${username}`, requestOptions);

      if (response.ok) {
        alert('Account deleted successfully');
        localStorage.removeItem("userDetails");

        setTimeout(() => {
          window.location.reload();
        }, 200);

        navigate('/');
      } else {
        console.error('Failed to delete account');
      }
    } catch (error) {
      console.error('Error:', error);
    }
    console.log("deleted")
  };

  return (
    <div className="profile-container">
      <h1>Profile</h1>
      <div className="profile-image-container">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScJqQWVL09zcgcaxSHHLL8cmNMlfHroJlQMg&usqp=CAU" alt="" />
      </div>
      <div className="profile-details">
        <label htmlFor="firstName">First Name:</label>
        <input
          type="text"
          id="firstName"
          name="firstName"
          value={userData.firstName || ''}
          onChange={handleChange}
        />

        <label htmlFor="lastName">Last Name:</label>
        <input
          type="text"
          id="lastName"
          name="lastName"
          value={userData.lastName || ''}
          onChange={handleChange}
        />

        <label htmlFor="dateOfBirth">Date of Birth:</label>
        <input
          type="date"
          id="dateOfBirth"
          name="dateOfBirth"
          value={userData.dateOfBirth || ''}
          onChange={handleChange}
        />
      </div>
      <button className="delete-account" onClick={handleDeleteAccount}>
        Delete Account
      </button>
    </div>
  );
};

export {Profile};
