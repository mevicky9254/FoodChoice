import React from "react";
import { Card } from "../card/Card";
import "./home.css"
import { foodList } from "../FoodList";
import { Banner } from "../Banner/Banner";
import { ActionButton } from "../chatbot/ActionButton";


const Home=()=>{


    return(
<>
      <div className="carasoul-container">
     
      <Banner/>
      
      </div>
     
        <div className="food-container">
        <ActionButton/>
        {foodList.map((element, index) => (
        <Card key={index} prop={element} />
      ))}
      
      </div>
     
</>
      
    )

}

export {Home};