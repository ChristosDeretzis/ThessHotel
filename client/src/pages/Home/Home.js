import React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router";
import { logout } from "../../store/user-slice";

const Home = (props) => {
    const user = JSON.parse(localStorage.getItem("userData"));

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleOnLogoutClick = () => {
        dispatch(logout()).unwrap();
        navigate("/login");
    }

    return (
        <div>
            <h1>Hello {user.username}</h1>
        </div>
        
    );
};

export default Home;