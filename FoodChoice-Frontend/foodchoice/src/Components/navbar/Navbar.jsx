import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import "./navbar.css"
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Avatar from '@mui/material/Avatar';
import { deepOrange, deepPurple } from '@mui/material/colors';


const Navbar = ({onSearch}) => {

const [searchValue, setSearchValue] = useState("");
const [firstInitial, setFirstInitial] = useState("");
const [avatarVisible, setAvatarVisible] = useState(false);
const [accountVisible, setAccountVisible] = useState(false);
const [userDetails, setUserDetails] = useState(null);


const navigate = useNavigate();

const handleInputChange=(event)=>{
  setSearchValue(event.target.value);
};


const handleSearchClick=()=>{
  if(searchValue.length<3){
    alert("Length of the movie name must be greater than two");
  }else{
   onSearch(searchValue);
  setSearchValue("");
  }
};

const handleKeyPress=(event)=>{
if(event.key==="Enter"){
  handleSearchClick();
}
}

const handleAccountIconClick=()=>{
  navigate("/signin");
}

const handleAvatarClick = async() => {

  try {
    const response = await fetch('http://localhost:8080/logout', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('JWTtoken')}`,
        },
    });

    if (response.ok) {

        localStorage.removeItem('JWTtoken');
        console.log("clicked")
        localStorage.removeItem("userDetails");
        if (userDetails==null) {
          setAvatarVisible(false); 
          setAccountVisible(true)
          navigate("/signin");
        }

    } else {
        console.error('Logout failed');
      
    }
} catch (error) {
    console.error('Error:', error);
    
}

 
};

useEffect(() => {
  let userDetails = JSON.parse(localStorage.getItem("userDetails"));
  if (userDetails != null) {
    let initial = userDetails.firstName[0].toUpperCase();
    setFirstInitial(initial);
    setAvatarVisible(true); 
    setAccountVisible(false); 
  } else {
    setAccountVisible(true); 
    setAvatarVisible(false);
    setAccountVisible(true); 
    setUserDetails(null);
  }
}, []);

  return (
    <>
      
    <div className="navbar">
      <ul className="left-menu">
      <li className="menubars">
      <i className="fa-solid fa-bars" />
      {/* Floating menu */}
      <div className="floating-menu">
        <ul>
        <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/recipes">Recipes</Link>
          </li>
          <li>
            <Link to="/forum">Forum</Link>
          </li>
          <li>
            <Link to="/profile">Profile</Link>
          </li>
        </ul>
      </div>
    </li>
        <li className="logo">
          <Link to="/">
            <h2>FoodChoice</h2>
          </Link>
        </li>
        <li className='menu'>
          <Link to="/">Home</Link>
        </li>
        <li className='menu'>
          <Link to="/recipes">Recipes</Link>
        </li>
        <li className='menu'>
          <Link to="/forum">Forum</Link>
        </li>
        <li className='menu'>
          <Link to="/profile">Profile</Link>
        </li>
      </ul>
      
      <div className="right-menu" >
  <div className="searchbox-container">
    <input
      type="text"
      className="searchbox"
      placeholder="Search"
      value={searchValue}
      onChange={handleInputChange}
      onKeyDown={handleKeyPress}
    />
    <Link to="/search/movie" className="magnifying-glass">
      <i className="fa-solid fa-magnifying-glass" onClick={handleSearchClick} />
    </Link>
  </div>
  
{/* Conditional rendering for user avatar and sign-out button */}
{avatarVisible ? (
            <>
              <div style={{ marginLeft: '20px', display: userDetails? "block":"none"}} onClick={handleAvatarClick}>
                <AccountCircleIcon style={{ fontSize: '30px', color: 'white' }} />
              </div>
              <div>
                <Avatar
                  sx={{
                    bgcolor: deepOrange[500],
                    width: '30px',
                    height: '30px',
                  }}
                  onClick={handleAvatarClick}
                >
                  {firstInitial}
                </Avatar>
              </div>
            </>
          ) : (
            <div style={{ marginLeft: '20px'}}>
              <Link to="/signin">
                <AccountCircleIcon style={{ fontSize: '30px', color: 'white' }} />
              </Link>
            </div>
          )}
        </div>

      </div>
    
    </>
  );
 
};

export { Navbar };