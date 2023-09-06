import './App.css';
import { Navbar } from "./Components/navbar/Navbar"
import { Profile } from './Components/profile/Profile';
import { Recipes } from './Components/recipe/Recipe';
import { Forum } from './Components/forum/Forum';
import { Home } from './Components/home/Home';
import { SignIn } from './pages/signin/SignIn';
import { SignUp } from './pages/signup/SignUp';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Footer } from './Components/footer/Footer';
import { Account } from './pages/acoount/Account';
import { RecipeForm } from './pages/createRecipe/RecipeForm';


function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/recipes" element={<Recipes />} />
          <Route path="/forum" element={<Forum />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/signin" element={<SignIn/>} />
          <Route path="/signup" element={<SignUp/>} />
          <Route path="/account" element={<Account/>}/>
          <Route path="/create-recipe" element={<RecipeForm/>}/>
        </Routes>
      </div>
      <Footer/>
    </Router>
   
  );
}

export default App;
