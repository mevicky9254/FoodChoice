import React from "react";
import { Card } from "../card/Card";
import "./home.css"
import { foodList } from "../FoodList";
import { Banner } from "../Banner/Banner";

const Home=()=>{


    return(
<>
      <div className="carasoul-container">
     
      <Banner/>
      </div>
        <div className="food-container">
        
        {foodList.map((element, index) => (
        <Card key={index} prop={element} />
      ))}

      </div>
</>
      
    )

}

export {Home};