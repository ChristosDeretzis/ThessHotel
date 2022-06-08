import React from "react";
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import Login from "../containers/Auth/Login";
import Signup from "../containers/Auth/Signup";

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