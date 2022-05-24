import axios from 'axios';

const API_URL = "http://localhost:8080/";

const signup = async (signupBody) => {
    console.log(signupBody);
    const response = await axios.post(API_URL + "signup", signupBody);
    console.log(response);

    if(response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
    }

    return response.data;
};

const login = async (loginBody) => {
    const response = await axios.post(API_URL + "login", loginBody);

    if(response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
    }

    return response.data;
};

const logout = () => {
    localStorage.removeItem("user");
};

const AuthService = {
    signup,
    login,
    logout
}

export default AuthService;



