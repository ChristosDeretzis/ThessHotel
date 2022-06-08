import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import { useSelector } from 'react-redux';
import ProtectedRoutes from './routes/ProtectedRoutes';
import AuthRoutes from './routes/AuthRoutes';
import Account from './pages/Account/Account';

function App() {
  const user = useSelector(state => state.user);
  console.log(user);
  return (
    <div className="App">
      {user.isLoggedIn ? <ProtectedRoutes /> : <AuthRoutes />}
      {/* <Account /> */}
    </div>
  );
}

export default App;
