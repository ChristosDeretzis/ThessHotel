import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./auth-slice";
import messageSlice from "./message-slice";

const store = configureStore({
    reducer: {
        message: messageSlice.reducer,
        auth: authSlice.reducer
    }
});

export default store;