import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import AuthService from "../services/auth.service";
import { messageActions } from "./message-slice";

const user = JSON.parse(localStorage.getItem("user"));

export const signup = createAsyncThunk(
    "auth/signup", 
    async (signupBody, thunkAPI) => {
        try{
            const response = await AuthService.signup(signupBody);
            console.log(response);
            thunkAPI.dispatch(messageActions.setMessage("User registered !!"));
            return { user: response };
        } catch(error) {
          console.log(error);
            const message = (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
            thunkAPI.dispatch(messageActions.setMessage(message));
            return thunkAPI.rejectWithValue();  
        }
    }
);

export const login = createAsyncThunk(
    "auth/login",
    async (loginBody, thunkAPI) => {
        try{
            const response = await AuthService.login(loginBody);
            thunkAPI.dispatch(messageActions.setMessage("User is logged in !!"));
            return { user: response };
        } catch(error) {
            const message = (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
            thunkAPI.dispatch(messageActions.setMessage(message));
            return thunkAPI.rejectWithValue();  
        }
    }
);

export const logout = createAsyncThunk("auth/logout", async () => {
    await AuthService.logout();
  });

const initialState = user
  ? { isLoggedIn: true, user }
  : { isLoggedIn: false, user: null };

const authSlice = createSlice({
    name: "auth",
    initialState,
    extraReducers: {
      [signup.fulfilled]: (state, action) => {
        state.isLoggedIn = true;
        state.user = action.payload.user;
      },
      [signup.rejected]: (state, action) => {
        state.isLoggedIn = false;
      },
      [login.fulfilled]: (state, action) => {
        state.isLoggedIn = true;
        state.user = action.payload.user;
      },
      [login.rejected]: (state, action) => {
        state.isLoggedIn = false;
        state.user = null;
      },
      [logout.fulfilled]: (state, action) => {
        state.isLoggedIn = false;
        state.user = null;
      },
    },
  });

export const authActions = authSlice.actions;
export default authSlice;

