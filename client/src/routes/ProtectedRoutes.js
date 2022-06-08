import React from "react";
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import NavBar from "../containers/NavBar/NavBar";
import Account from "../pages/Account/Account";
import Home from "../pages/Home/Home";

const ProtectedRoutes = () => {
    return (
        <Router>
            <NavBar />
            <Routes>
                <Route path="/" exact element={<Navigate to="/home"/>} />
                <Route path="/home" exact element={<Home />} />
                <Route path="/accountSettings" exact element={<Account />} />
            </Routes>
        </Router>
    );
}

export default ProtectedRoutes;

