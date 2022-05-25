import logo from './logo.svg';
import './App.css';
import Login from './components/Auth/Login/Login';
import Signup from './components/Auth/SignUp/Signup';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import { useSelector } from 'react-redux';
import ProtectedRoutes from './routes/ProtectedRoutes';
import AuthRoutes from './routes/AuthRoutes';

function App() {
  const auth = useSelector(state => state.auth);
  return (
    <div className="App">
      {auth.isLoggedIn ? <ProtectedRoutes /> : <AuthRoutes />}
    </div>
  );
}

export default App;
