import React from 'react';
import { Link } from 'react-router-dom';
import { useState } from 'react';
import "./navbar.css"

const Navbar = ({onSearch}) => {

const [searchValue, setSearchValue] = useState("");

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
      <div className="right-menu">
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
            <i className="fa-solid fa-magnifying-glass"  onClick={handleSearchClick}/>
          </Link>
          <h3><Link to="./signin">SignIn</Link></h3>
          
          
        </div>
      </div>
    </div>
    </>
  );
 
};

export { Navbar };