import logo from './logo.svg';
import './App.css';
import Login from './components/Auth/Login/Login';
import Signup from './components/Auth/SignUp/Signup';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";

function App() {
  const user = JSON.parse(localStorage.getItem("user"));
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      </Router>
      
    </div>
  );
}

export default App;
