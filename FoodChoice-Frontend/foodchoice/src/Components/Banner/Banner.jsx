import React from "react";
import "./Banner.css";

const Banner = () => {
  return (
    <div className="banner">
      <div className="bannertext">
        <h1 className="bannertitle">FoodChoice</h1>
        <p className="bannertagline">Delicious Food at Your Fingertips</p>
      </div>
      <div className="bannerimage"></div>
    </div>
  );
};

export {Banner};
