import React from "react";
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import Login from "../components/Auth/Login/Login";
import Signup from "../components/Auth/SignUp/Signup";

const AuthRoutes = () => {
    return (
        <Router>
            <Routes>
                
                <Route path="/" element={<Navigate to="/login"/>} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
            </Routes>
        </Router>
    );
}

export default AuthRoutes;