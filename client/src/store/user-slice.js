import { formLabelClasses } from "@mui/material";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import UserService from "../services/user.service";
import { messageActions } from "./message-slice";

const userDetails = JSON.parse(localStorage.getItem("userData"));

export const signup = createAsyncThunk(
    "user/signup", 
    async (signupBody, thunkAPI) => {
        try{
            const response = await UserService.signup(signupBody);
            console.log(response);
            thunkAPI.dispatch(messageActions.setMessage("User registered !!"));
            return { data: response };
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
    "user/login",
    async (loginBody, thunkAPI) => {
        try{
            const response = await UserService.login(loginBody);
            thunkAPI.dispatch(messageActions.setMessage("User is logged in !!"));
            return { data: response };
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

export const logout = createAsyncThunk("user/logout", async () => {
    await UserService.logout();
  });

export const updateUser = createAsyncThunk("user/updateUser", async (updateUserBody, thunkAPI) => {
  try{
    const response = await UserService.updateUser(updateUserBody);
    thunkAPI.dispatch(messageActions.setMessage("User is updated"));
    return { data: response };
  } catch(error) {
    const message = (error.response &&
        error.response.data &&
        error.response.data.message) ||
      error.message ||
      error.toString();
    thunkAPI.dispatch(messageActions.setMessage(message));
    return thunkAPI.rejectWithValue();  
  }
});

export const deleteUser = createAsyncThunk("user/deleteUser", async (userId, thunkAPI) => {
  try{
    const response = await UserService.deleteUser(userId);
    thunkAPI.dispatch(messageActions.setMessage("User is deleted"));
    return { data: response };
  } catch(error) {
    const message = (error.response &&
        error.response.data &&
        error.response.data.message) ||
      error.message ||
      error.toString();
    thunkAPI.dispatch(messageActions.setMessage(message));
    return thunkAPI.rejectWithValue();  
  }
});

const initialState = userDetails
  ? { isLoggedIn: true, userDetails }
  : { isLoggedIn: false, userDetails: null };

const userSlice = createSlice({
    name: "user",
    initialState,
    extraReducers: {
      [signup.fulfilled]: (state, action) => {
        state.isLoggedIn = true;
        state.userDetails = action.payload.user;
      },
      [signup.rejected]: (state, action) => {
        state.isLoggedIn = false;
      },
      [login.fulfilled]: (state, action) => {
        state.isLoggedIn = true;
        state.userDetails = action.payload.user;
      },
      [login.rejected]: (state, action) => {
        state.isLoggedIn = false;
        state.userDetails = null;
      },
      [logout.fulfilled]: (state, action) => {
        state.isLoggedIn = false;
        state.userDetails = null;
      },
      [updateUser.fulfilled]: (state, action) => {
        state.isLoggedIn = true;
        state.userDetails = action.payload.data;
      },
      [updateUser.rejected]: (state, action) => {
        state.isLoggedIn = true;
      },
      [deleteUser.fulfilled]: (state, action) => {
        state.isLoggedIn = formLabelClasses;
        state.userDetails = null;
      },
      [deleteUser.rejected]: (state, action) => {
        
      }
    },
  });

export const userActions = userSlice.actions;
export default userSlice;

