import React from "react";
import "./Card.css";

const Card = ({ prop }) => {
    console.log(prop.image)
  return (
    <div className="card">
      <div className="card-image">
        <img src={prop.image} alt="" />
      </div>
      <div className="card-details">
        <h3 className="title">{prop.title}</h3>
        <div className="food-details">
          <p className="type">{prop.type}</p>
          <p className="rating">Rating {prop.rating}</p>
        </div>
      </div>
      <div>
        <button className="add-to-wishlist">Add To Wishlist</button>
      </div>
    </div>
  );
};

export { Card };
