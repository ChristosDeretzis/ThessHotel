import React from "react";
import { useDispatch } from "react-redux";
import { logout } from "../../store/auth-slice";

const Home = () => {
    const user = JSON.parse(localStorage.getItem("user"));

    const dispatch = useDispatch();

    const handleOnLogoutClick = () => {
        dispatch(logout()).unwrap();
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