import axios from 'axios';
import authHeader from './auth-header';

const API_URL = "http://localhost:8080/";

const signup = async (signupBody) => {
    console.log(signupBody);
    const response = await axios.post(API_URL + "auth/signup", signupBody);
    console.log(response);

    if(response.data.accessToken) {
        localStorage.setItem("userData", JSON.stringify(response.data.user));
        localStorage.setItem("accessToken", JSON.stringify(response.data.accessToken));
    }

    return response.data;
};

const login = async (loginBody) => {
    const response = await axios.post(API_URL + "auth/login", loginBody);

    if(response.data.accessToken) {
        localStorage.setItem("userData", JSON.stringify(response.data.user));
        localStorage.setItem("accessToken", JSON.stringify(response.data.accessToken));
    }

    return response.data;
};

const updateUser = async (updateUserBody) => {
    const response = await axios.put(API_URL + "user/" + updateUserBody.id, updateUserBody, { headers: authHeader() });
    console.log(response);
    if(response.data.userDto) {
        localStorage.setItem("userData", JSON.stringify(response.data.userDto));
        localStorage.setItem("accessToken", JSON.stringify(response.data.accessToken));
    }

    return response.data;
}

const logout = () => {
    localStorage.removeItem("userData");
    localStorage.removeItem("accessToken");
};

const UserService = {
    signup,
    login,
    logout,
    updateUser
}

export default UserService;



