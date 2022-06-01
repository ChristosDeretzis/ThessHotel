import React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router";
import { logout } from "../../store/auth-slice";

const Home = (props) => {
    const user = JSON.parse(localStorage.getItem("user"));

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleOnLogoutClick = () => {
        dispatch(logout()).unwrap();
        navigate("/login");
    }

    return (
        <div>
            <h1>Email: {user.email}</h1>
            <p>AccessToken: {user.accessToken}</p>
            <button onClick={handleOnLogoutClick}>Logout</button>
        </div>
        
    );
};

export default Home;