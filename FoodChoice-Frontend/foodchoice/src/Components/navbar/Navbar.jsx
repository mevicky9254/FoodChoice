import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import "./navbar.css"
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Avatar from '@mui/material/Avatar';
import { deepOrange, deepPurple } from '@mui/material/colors';
import { AccountMenu } from './AccountMenu';


const Navbar = ({onSearch}) => {

const [searchValue, setSearchValue] = useState("");
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


useEffect(() => {
  let userDetails = JSON.parse(localStorage.getItem("userDetails"));
  if (userDetails != null) {
    setAvatarVisible(true); 
    setAccountVisible(false); 
  } else {
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
            <Link to="/profile">Profile</Link>
          </li>
          <li>
            <Link to="/recipes">Recipes</Link>
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
          <Link to="/profile">Profile</Link>
        </li>
        <li className='menu'>
          <Link to="/recipes">Recipes</Link>
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
              {/* <div style={{ marginLeft: '20px', display: userDetails? "block":"none"}} >
                <AccountCircleIcon style={{ fontSize: '36px', color: 'white' }} />
              </div> */}
              <div style={{ marginLeft: '-9px', display: userDetails? "none":"block"}}>
                {/* <Avatar
                  sx={{
                    bgcolor: deepOrange[500],
                    width: '30px',
                    height: '30px',
                  }}
                  onClick={handleAvatarClick}
                >
                  {firstInitial}
                 
                </Avatar> */}
                <AccountMenu />
               
              </div>
            </>
          ) : (
            <div style={{ marginLeft: '10px'}}>
              <Link to="/signin">
                <AccountCircleIcon sx={{
                    // bgcolor: deepOrange[500],
                    color:"white",
                    width: '35px',
                    height: '35px',
                  }} />
              </Link>
            </div>
          )}
        </div>

      </div>
    
    </>
  );
 
};

export { Navbar };